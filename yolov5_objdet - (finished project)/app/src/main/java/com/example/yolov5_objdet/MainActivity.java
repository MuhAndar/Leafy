package com.example.yolov5_objdet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private final int IMAGE_PICK = 100;
    private final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    Bitmap bitmap;
    Yolov5TFLiteDetector yolov5TFLiteDetector;
    Paint boxPaint = new Paint();
    Paint textPaint = new Paint();
    private Map<String, PlantInfo> plantInfoMap;
    private Uri photoURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        yolov5TFLiteDetector = new Yolov5TFLiteDetector();
        yolov5TFLiteDetector.setModelFile("best-fp16.tflite");
        yolov5TFLiteDetector.initialModel(this);

        plantInfoMap = new HashMap<>();
        plantInfoMap.put("cassava", new PlantInfo("Cassava", "Diproses terlebih dahulu", true));
        plantInfoMap.put("celosia", new PlantInfo("Celosia", "Diproses terlebih dahulu", true));
        plantInfoMap.put("bandotan", new PlantInfo("Bandotan", "Tidak disarankan untuk dikonsumsi", false));
        plantInfoMap.put("sembung_kuwuk", new PlantInfo("Sembung Kuwuk", "Tidak disarankan untuk dikonsumsi", false));
        plantInfoMap.put("kirinyuh", new PlantInfo("Kirinyuh", "Tidak disarankan untuk dikonsumsi", false));
        plantInfoMap.put("getih_getihan", new PlantInfo("Getih-getihan", "Tidak disarankan untuk dikonsumsi", false));
        plantInfoMap.put("sintrong", new PlantInfo("Sintrong", "Daunnya dapat dikonsumsi secara langsung", true));
        plantInfoMap.put("ginseng_liar", new PlantInfo("Ginseng Jawa", "Diproses terlebih dahulu", true));
        boxPaint.setStrokeWidth(5);
        boxPaint.setStyle(Paint.Style.STROKE);
        boxPaint.setColor(Color.RED);

        textPaint.setTextSize(100);
        textPaint.setColor(Color.GREEN);
        textPaint.setStyle(Paint.Style.FILL);
    }

    public void openCamera(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                photoURI = FileProvider.getUriForFile(this, "com.example.yolov5_objdet.fileprovider", createImageFile());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private File createImageFile() throws IOException {
        String imageFileName = "JPEG_" + System.currentTimeMillis() + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    public void selectImage(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK);
    }

    public void predict(View view) {
        TextView noDetectionMessage = findViewById(R.id.noDetectionMessage);
        noDetectionMessage.setVisibility(View.GONE);

        if (bitmap == null) {
            return;
        }

        ArrayList<Recognition> recognitions = yolov5TFLiteDetector.detect(bitmap, plantInfoMap, this);
        if (recognitions.isEmpty()) {
            noDetectionMessage.setVisibility(View.VISIBLE);
            return;
        }

        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(mutableBitmap);

        for (Recognition recognition : recognitions) {
            if (recognition.getConfidence() > 0.4) {
                RectF location = recognition.getLocation();
                canvas.drawRect(location, boxPaint);
                canvas.drawText(recognition.getLabelName() + ":" + recognition.getConfidence(), location.left, location.top, textPaint);
            }
        }

        imageView.setImageBitmap(mutableBitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
                findViewById(R.id.predictButton).setVisibility(View.VISIBLE);
                findViewById(R.id.backButton).setVisibility(View.VISIBLE);
                findViewById(R.id.cameraButton).setVisibility(View.GONE);
                findViewById(R.id.galleryButton).setVisibility(View.GONE);
                findViewById(R.id.logoImageView).setVisibility(View.GONE); // Hide the logo when an image is picked
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoURI);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
                findViewById(R.id.predictButton).setVisibility(View.VISIBLE);
                findViewById(R.id.backButton).setVisibility(View.VISIBLE);
                findViewById(R.id.cameraButton).setVisibility(View.GONE);
                findViewById(R.id.galleryButton).setVisibility(View.GONE);
                findViewById(R.id.logoImageView).setVisibility(View.GONE); // Hide the logo when an image is captured
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void goBack(View view) {
        // Reset the view to the initial state
        imageView.setImageBitmap(null);
        imageView.setVisibility(View.GONE);
        findViewById(R.id.predictButton).setVisibility(View.GONE);
        findViewById(R.id.backButton).setVisibility(View.GONE);
        findViewById(R.id.cameraButton).setVisibility(View.VISIBLE);
        findViewById(R.id.galleryButton).setVisibility(View.VISIBLE);
        findViewById(R.id.noDetectionMessage).setVisibility(View.GONE); // Hide the no detection message when going back
        findViewById(R.id.logoImageView).setVisibility(View.VISIBLE); // Show the logo when going back to the initial state
    }

    public void showPlantInfoDialog(PlantInfo plantInfo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(plantInfo.getName());

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_plant_info, null);
        builder.setView(dialogView);

        TextView edibilityTextView = dialogView.findViewById(R.id.edibility);
        TextView descriptionTextView = dialogView.findViewById(R.id.description);

        if (plantInfo.isEdible()) {
            edibilityTextView.setText("Dapat dikonsumsi");
            edibilityTextView.setTextColor(Color.GREEN);
        } else {
            edibilityTextView.setText("Tidak dapat dikonsumsi/Beracun");
            edibilityTextView.setTextColor(Color.RED);
        }

        descriptionTextView.setText(plantInfo.getDescription());

        builder.setNegativeButton("Tutup", (dialog, which) -> dialog.dismiss());

        if (plantInfo.isEdible()) {
            builder.setPositiveButton("Info", (dialog, which) -> {
                Class<?> activityClass = null;
                String plantName = plantInfo.getName().toLowerCase();
                Log.d("MainActivity", "Clicked Info button for plant: " + plantName);

                switch (plantName) {
                    case "sintrong":
                        activityClass = SintrongActivity.class;
                        break;
                    case "cassava":
                        activityClass = CassavaActivity.class;
                        break;
                    case "celosia":
                        activityClass = CelosiaActivity.class;
                        break;
                    case "ginseng jawa":
                        Log.d("MainActivity", "Redirecting to GinsengliarActivity");
                        activityClass = GinsengliarActivity.class;
                        break;
                    // Uncomment and add these if you have these activities
                    // case "bandotan":
                    //     activityClass = BandotanActivity.class;
                    //     break;
                    // case "sembung_kuwuk":
                    //     activityClass = SembungKuwukActivity.class;
                    //     break;
                    // case "getih_getihan":
                    //     activityClass = GetihGetihanActivity.class;
                    //     break;
                    // Add cases for other plants as needed
                    default:
                        Log.d("MainActivity", "No matching activity class found for plant: " + plantName);
                        break;
                }

                if (activityClass != null) {
                    Log.d("MainActivity", "Starting activity: " + activityClass.getSimpleName());
                    Intent intent = new Intent(MainActivity.this, activityClass);
                    startActivity(intent);
                } else {
                    Log.d("MainActivity", "Activity class is null");
                }
            });
        }

        builder.show();
    }




    public void openEncyclopedia(View view) {
        Intent intent = new Intent(MainActivity.this, EncyclopediaActivity.class);
        startActivity(intent);
    }
}
