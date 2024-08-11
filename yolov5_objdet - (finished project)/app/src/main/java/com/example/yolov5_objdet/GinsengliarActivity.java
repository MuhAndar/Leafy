package com.example.yolov5_objdet;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class GinsengliarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ginsengliar);

        WebView ginsengliarWebView = findViewById(R.id.ginsengliarWebView);

        String content = "<html><body style=\"text-align:justify;\">" +
                "<h4><b>Ginseng Jawa</b> (Talinum paniculatum), juga dikenal sebagai Java ginseng atau Som Jawa, adalah tanaman yang bisa dikonsumsi dan memiliki beberapa manfaat kesehatan. Di berbagai daerah, terutama di Asia Tenggara dan Amerika Latin, bagian dari tanaman ini sering digunakan dalam masakan dan pengobatan tradisional.</h4>" +
                "<h4><b>Manfaat Ginseng Jawa</b></h4>" +
                "<p><b>1. Kaya Nutrisi</b>: Daun dan akar Ginseng Jawa kaya akan vitamin A, vitamin C, dan mineral seperti kalsium dan zat besi. Nutrisi ini penting untuk kesehatan mata, sistem kekebalan tubuh, dan pembentukan darah.<br>" +
                "<b>2. Antioksidan</b>: Mengandung antioksidan yang membantu melawan radikal bebas dan dapat mengurangi risiko beberapa penyakit kronis.<br>" +
                "<b>3. Anti-inflamasi</b>: Memiliki sifat anti-inflamasi yang dapat membantu mengurangi peradangan dan nyeri pada tubuh.<br>" +
                "<b>4. Sumber Energi</b>: Akar Ginseng Jawa sering digunakan sebagai tonik untuk meningkatkan energi dan vitalitas.</p>" +
                "<h4><b>Cara Mengolah Ginseng Jawa</b></h4>" +
                "<p><b>1. Pencucian</b>: Cuci bersih daun dan akar Ginseng Jawa untuk menghilangkan kotoran atau sisa-sisa pestisida yang mungkin melekat. Gunakan air mengalir dan mungkin sedikit garam untuk membantu menghilangkan kotoran lebih efektif.<br>" +
                "<b>2. Rebusan</b>: Daun dan akar Ginseng Jawa sering direbus terlebih dahulu untuk menghilangkan rasa pahit. Rebus dalam air mendidih selama beberapa menit, lalu buang airnya.<br>" +
                "<b>3. Tumisan</b>: Setelah direbus, daun Ginseng Jawa bisa ditumis dengan bumbu seperti bawang putih, bawang merah, dan cabai untuk menambah rasa. Tumis hingga matang dan tambahkan sedikit garam atau penyedap rasa sesuai selera.<br>" +
                "<b>4. Campuran dalam Sayur</b>: Daun Ginseng Jawa juga bisa dicampur dengan sayur lain dalam membuat sup atau sayur bening. Ini menambahkan rasa dan nutrisi pada masakan.<br>" +
                "<b>5. Pelengkap dalam Salad</b>: Daun muda Ginseng Jawa yang masih segar bisa juga digunakan sebagai pelengkap dalam salad, mirip dengan penggunaan daun selada atau arugula.</p>" +
                "<h4><b>Kiat Memilih dan Menyimpan</b></h4>" +
                "<p>Pilih daun dan akar Ginseng Jawa yang segar dan hijau, tanpa tanda kekuningan atau kerusakan. Simpan di kulkas dalam wadah tertutup atau plastik agar tetap segar lebih lama.</p>" +
                "<p><b>Catatan Penting</b>: Meskipun Ginseng Jawa memiliki banyak manfaat kesehatan, pastikan untuk memasaknya dengan benar sebelum dikonsumsi. Hindari konsumsi dalam jumlah besar tanpa konsultasi dengan ahli kesehatan, terutama jika digunakan untuk tujuan pengobatan.</p>" +
                "<p>Secara keseluruhan, Ginseng Jawa adalah tanaman yang sehat dan bergizi, yang dapat diolah menjadi berbagai hidangan lezat dan bermanfaat untuk kesehatan.</p>" +
                "</body></html>";

        ginsengliarWebView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
    }

    public void goBack(View view) {
        finish(); // Close the current activity and return to the previous one
    }
}
