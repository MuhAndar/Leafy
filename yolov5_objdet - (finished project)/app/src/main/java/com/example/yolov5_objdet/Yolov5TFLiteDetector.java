package com.example.yolov5_objdet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Build;
import android.util.Log;
import android.util.Size;
import android.widget.Toast;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.gpu.CompatibilityList;
import org.tensorflow.lite.gpu.GpuDelegate;
import org.tensorflow.lite.nnapi.NnApiDelegate;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.common.TensorProcessor;
import org.tensorflow.lite.support.common.ops.CastOp;
import org.tensorflow.lite.support.common.ops.DequantizeOp;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.support.common.ops.QuantizeOp;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.metadata.MetadataExtractor;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Yolov5TFLiteDetector {

    private final Size INPUT_SIZE = new Size(640, 640);
    private final int[] OUTPUT_SIZE = new int[]{1, 25200, 13};
    private Boolean IS_INT8 = false;
    private final float DETECT_THRESHOLD = 0.60f;
    private final float IOU_THRESHOLD = 0.45f;
    private final float IOU_CLASS_DUPLICATED_THRESHOLD = 0.7f;

    private final String LABEL_FILE = "coco_label.txt";

    private int BITMAP_HEIGHT;
    private int BITMAP_WIDTH;
    MetadataExtractor.QuantizationParams input5SINT8QuantParams = new MetadataExtractor.QuantizationParams(0.003921568859368563f, 0);
    MetadataExtractor.QuantizationParams output5SINT8QuantParams = new MetadataExtractor.QuantizationParams(0.006305381190031767f, 5);
    private String MODEL_FILE;

    private Interpreter tflite;
    private List<String> associatedAxisLabels;
    Interpreter.Options options = new Interpreter.Options();

    public String getModelFile() {
        return this.MODEL_FILE;
    }

    public void setModelFile(String modelFile){
        MODEL_FILE = modelFile;
        Log.d(">>> ", "MODEL NAME SET --- "+ MODEL_FILE + ", "+modelFile);
    }

    public String getLabelFile() {
        return this.LABEL_FILE;
    }

    public Size getInputSize(){return this.INPUT_SIZE;}
    public int[] getOutputSize(){return this.OUTPUT_SIZE;}

    public void initialModel(Context context) {
        try {
            Log.d(">>> ", "loading model --- "+ MODEL_FILE);
            ByteBuffer tfliteModel = FileUtil.loadMappedFile(context, MODEL_FILE);
            tflite = new Interpreter(tfliteModel, options);
            Log.i("tfliteSupport", "Success reading model: " + MODEL_FILE);

            associatedAxisLabels = FileUtil.loadLabels(context, LABEL_FILE);
            Log.i("tfliteSupport", "Success reading label: " + LABEL_FILE);

        } catch (IOException e) {
            Log.e("tfliteSupport", "Error reading model or label: ", e);
            Toast.makeText(context, "load model error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList<Recognition> detect(Bitmap bitmap, Map<String, PlantInfo> plantInfoMap, MainActivity mainActivity) {
        BITMAP_HEIGHT = bitmap.getHeight();
        BITMAP_WIDTH = bitmap.getWidth();

        TensorImage yolov5sTfliteInput;
        ImageProcessor imageProcessor;
        if (IS_INT8) {
            imageProcessor = new ImageProcessor.Builder()
                    .add(new ResizeOp(INPUT_SIZE.getHeight(), INPUT_SIZE.getWidth(), ResizeOp.ResizeMethod.BILINEAR))
                    .add(new NormalizeOp(0, 255))
                    .add(new QuantizeOp(input5SINT8QuantParams.getZeroPoint(), input5SINT8QuantParams.getScale()))
                    .add(new CastOp(DataType.UINT8))
                    .build();
            yolov5sTfliteInput = new TensorImage(DataType.UINT8);
        } else {
            imageProcessor = new ImageProcessor.Builder()
                    .add(new ResizeOp(INPUT_SIZE.getHeight(), INPUT_SIZE.getWidth(), ResizeOp.ResizeMethod.BILINEAR))
                    .add(new NormalizeOp(0, 255))
                    .build();
            yolov5sTfliteInput = new TensorImage(DataType.FLOAT32);
        }

        yolov5sTfliteInput.load(bitmap);
        yolov5sTfliteInput = imageProcessor.process(yolov5sTfliteInput);

        TensorBuffer probabilityBuffer;
        if (IS_INT8) {
            probabilityBuffer = TensorBuffer.createFixedSize(OUTPUT_SIZE, DataType.UINT8);
        } else {
            probabilityBuffer = TensorBuffer.createFixedSize(OUTPUT_SIZE, DataType.FLOAT32);
        }

        // Measure inference time
        long startTime = System.nanoTime(); // Start time

        if (tflite != null) {
            tflite.run(yolov5sTfliteInput.getBuffer(), probabilityBuffer.getBuffer());
        }

        long endTime = System.nanoTime(); // End time
        long inferenceTime = endTime - startTime; // Calculate inference time
        Log.d("Yolov5TFLiteDetector", "Inference time: " + inferenceTime + " ns");

        // Process the model output
        float[] recognitionArray = probabilityBuffer.getFloatArray();
        ArrayList<Recognition> allRecognitions = new ArrayList<>();
        for (int i = 0; i < OUTPUT_SIZE[1]; i++) {
            int gridStride = i * OUTPUT_SIZE[2];
            float x = recognitionArray[0 + gridStride] * BITMAP_WIDTH;
            float y = recognitionArray[1 + gridStride] * BITMAP_HEIGHT;
            float w = recognitionArray[2 + gridStride] * BITMAP_WIDTH;
            float h = recognitionArray[3 + gridStride] * BITMAP_HEIGHT;
            int xmin = (int) Math.max(0, x - w / 2.);
            int ymin = (int) Math.max(0, y - h / 2.);
            int xmax = (int) Math.min(BITMAP_WIDTH, x + w / 2.);
            int ymax = (int) Math.min(BITMAP_HEIGHT, y + h / 2.);
            float confidence = recognitionArray[4 + gridStride];
            float[] classScores = Arrays.copyOfRange(recognitionArray, 5 + gridStride, this.OUTPUT_SIZE[2] + gridStride);

            int labelId = 0;
            float maxLabelScores = 0.f;
            for (int j = 0; j < classScores.length; j++) {
                if (classScores[j] > maxLabelScores) {
                    maxLabelScores = classScores[j];
                    labelId = j;
                }
            }

            Recognition r = new Recognition(
                    labelId,
                    "",
                    maxLabelScores,
                    confidence,
                    new RectF(xmin, ymin, xmax, ymax));
            allRecognitions.add(r);
        }

        ArrayList<Recognition> nmsRecognitions = nms(allRecognitions);
        ArrayList<Recognition> nmsFilterBoxDuplicationRecognitions = nmsAllClass(nmsRecognitions);

        for (Recognition recognition : nmsFilterBoxDuplicationRecognitions) {
            int labelId = recognition.getLabelId();
            String labelName = associatedAxisLabels.get(labelId);
            recognition.setLabelName(labelName);

            if (plantInfoMap.containsKey(labelName)) {
                PlantInfo plantInfo = plantInfoMap.get(labelName);
                mainActivity.showPlantInfoDialog(plantInfo);
            }
        }

        return nmsFilterBoxDuplicationRecognitions;
    }


    protected ArrayList<Recognition> nms(ArrayList<Recognition> allRecognitions) {
        ArrayList<Recognition> nmsRecognitions = new ArrayList<>();

        for (int i = 0; i < OUTPUT_SIZE[2] - 5; i++) {
            PriorityQueue<Recognition> pq = new PriorityQueue<>(
                    6300,
                    new Comparator<Recognition>() {
                        @Override
                        public int compare(final Recognition l, final Recognition r) {
                            return Float.compare(r.getConfidence(), l.getConfidence());
                        }
                    });

            for (int j = 0; j < allRecognitions.size(); ++j) {
                if (allRecognitions.get(j).getLabelId() == i && allRecognitions.get(j).getConfidence() > DETECT_THRESHOLD) {
                    pq.add(allRecognitions.get(j));
                }
            }

            while (pq.size() > 0) {
                Recognition[] a = new Recognition[pq.size()];
                Recognition[] detections = pq.toArray(a);
                Recognition max = detections[0];
                nmsRecognitions.add(max);
                pq.clear();

                for (int k = 1; k < detections.length; k++) {
                    Recognition detection = detections[k];
                    if (boxIou(max.getLocation(), detection.getLocation()) < IOU_THRESHOLD) {
                        pq.add(detection);
                    }
                }
            }
        }
        return nmsRecognitions;
    }

    protected ArrayList<Recognition> nmsAllClass(ArrayList<Recognition> allRecognitions) {
        ArrayList<Recognition> nmsRecognitions = new ArrayList<>();

        PriorityQueue<Recognition> pq = new PriorityQueue<>(
                100,
                new Comparator<Recognition>() {
                    @Override
                    public int compare(final Recognition l, final Recognition r) {
                        return Float.compare(r.getConfidence(), l.getConfidence());
                    }
                });

        for (int j = 0; j < allRecognitions.size(); ++j) {
            if (allRecognitions.get(j).getConfidence() > DETECT_THRESHOLD) {
                pq.add(allRecognitions.get(j));
            }
        }

        while (pq.size() > 0) {
            Recognition[] a = new Recognition[pq.size()];
            Recognition[] detections = pq.toArray(a);
            Recognition max = detections[0];
            nmsRecognitions.add(max);
            pq.clear();

            for (int k = 1; k < detections.length; k++) {
                Recognition detection = detections[k];
                if (boxIou(max.getLocation(), detection.getLocation()) < IOU_CLASS_DUPLICATED_THRESHOLD) {
                    pq.add(detection);
                }
            }
        }
        return nmsRecognitions;
    }

    protected float boxIou(RectF a, RectF b) {
        float intersection = boxIntersection(a, b);
        float union = boxUnion(a, b);
        if (union <= 0) return 1;
        return intersection / union;
    }

    protected float boxIntersection(RectF a, RectF b) {
        float maxLeft = a.left > b.left ? a.left : b.left;
        float maxTop = a.top > b.top ? a.top : b.top;
        float minRight = a.right < b.right ? a.right : b.right;
        float minBottom = a.bottom < b.bottom ? a.bottom : b.bottom;
        float w = minRight -  maxLeft;
        float h = minBottom - maxTop;

        if (w < 0 || h < 0) return 0;
        float area = w * h;
        return area;
    }

    protected float boxUnion(RectF a, RectF b) {
        float i = boxIntersection(a, b);
        float u = (a.right - a.left) * (a.bottom - a.top) + (b.right - b.left) * (b.bottom - b.top) - i;
        return u;
    }

    public void addNNApiDelegate() {
        NnApiDelegate nnApiDelegate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            nnApiDelegate = new NnApiDelegate();
            options.addDelegate(nnApiDelegate);
            Log.i("tfliteSupport", "using nnapi delegate.");
        }
    }

    public void addGPUDelegate() {
        CompatibilityList compatibilityList = new CompatibilityList();
        if(compatibilityList.isDelegateSupportedOnThisDevice()){
            GpuDelegate.Options delegateOptions = compatibilityList.getBestOptionsForThisDevice();
            GpuDelegate gpuDelegate = new GpuDelegate();
            options.addDelegate(gpuDelegate);
            Log.i("tfliteSupport", "using gpu delegate.");
        } else {
            addThread(4);
        }
    }

    public void addThread(int thread) {
        options.setNumThreads(thread);
    }
}
