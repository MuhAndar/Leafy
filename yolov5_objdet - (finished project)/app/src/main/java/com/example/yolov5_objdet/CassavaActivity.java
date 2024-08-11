package com.example.yolov5_objdet;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class CassavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cassava);

        WebView cassavaWebView = findViewById(R.id.cassavaWebView);

        String content = "<html><body style=\"text-align:justify;\">" +
                "<h4><b>Cassava</b> (ketela pohon atau singkong) adalah tanaman yang dapat dikonsumsi dan merupakan sumber makanan pokok di banyak negara tropis. Namun, singkong bukanlah tanaman liar; ia biasanya ditanam dan dibudidayakan oleh petani. Berikut adalah beberapa informasi lebih lanjut tentang singkong:</h4>" +
                "<h4><b>Manfaat Singkong</b></h4>" +
                "<p><b>1. Kaya Karbohidrat</b>: Umbi singkong adalah sumber karbohidrat yang sangat baik, memberikan energi yang diperlukan untuk aktivitas sehari-hari.<br>" +
                "<b>2. Bebas Gluten</b>: Singkong bebas gluten, sehingga merupakan alternatif yang baik bagi orang-orang yang memiliki intoleransi gluten atau penyakit celiac.<br>" +
                "<b>3. Sumber Serat</b>: Umbi singkong mengandung serat yang baik untuk pencernaan.<br>" +
                "<b>4. Kaya Vitamin dan Mineral</b>: Daun singkong mengandung vitamin A, vitamin C, kalsium, dan zat besi.</p>" +
                "<h4><b>Cara Mengolah Singkong</b></h4>" +
                "<p><b>1. Mengupas dan Memotong</b>: Kupas kulit singkong dan potong umbi menjadi bagian-bagian kecil.<br>" +
                "<b>2. Merendam</b>: Merendam potongan singkong dalam air selama beberapa jam dapat membantu menghilangkan beberapa senyawa beracun.<br>" +
                "<b>3. Merebus atau Menggoreng</b>: Singkong dapat direbus hingga empuk, kemudian digoreng untuk mendapatkan tekstur yang renyah.<br>" +
                "<b>4. Dibuat Tepung</b>: Singkong dapat dikeringkan dan digiling menjadi tepung yang digunakan dalam berbagai resep, termasuk roti dan kue.</p>" +
                "<h4><b>Catatan Penting</b></h4>" +
                "<p>Singkong mentah mengandung senyawa sianogenik glikosida yang dapat menghasilkan sianida jika tidak diolah dengan benar. Oleh karena itu, penting untuk selalu memasak singkong dengan baik sebelum dikonsumsi untuk menghilangkan racun ini. Pastikan untuk menghindari konsumsi singkong mentah.</p>" +
                "<p>Singkong adalah tanaman yang ditanam dan bukan tanaman liar. Ini adalah tanaman yang sangat serbaguna dan penting di banyak daerah tropis, menyediakan sumber makanan yang berharga bagi jutaan orang di seluruh dunia.</p>" +
                "</body></html>";

        cassavaWebView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
    }

    public void goBack(View view) {
        finish(); // Close the current activity and return to the previous one
    }
}
