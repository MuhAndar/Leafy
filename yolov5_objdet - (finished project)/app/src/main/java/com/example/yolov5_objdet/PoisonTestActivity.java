package com.example.yolov5_objdet;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class PoisonTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poison_test);

        WebView poisonTestWebView = findViewById(R.id.poisonTestWebView);

        String content = "<html><body style=\"text-align:justify;\">" +
                "<p>Tanaman beracun adalah tanaman yang mengandung zat kimia yang dapat menyebabkan efek negatif pada manusia atau hewan jika terjadi kontak langsung, terhirup, atau tertelan. Zat kimia ini bisa berada di berbagai bagian tanaman, termasuk daun, batang, buah, biji, atau akar. Efek dari keracunan tanaman dapat bervariasi dari ringan hingga sangat serius, bahkan bisa menyebabkan kematian. Berikut Tips untuk mengetahui apakah sebuah tanaman beracun atau tidak:</p>"+
                "<h4>1. <b>Belajar dari Sumber yang Terpercaya</b></h4>" +
                "<p>Belajar dari Buku dan Aplikasi: Gunakan panduan lapangan dan aplikasi mobile yang terpercaya untuk identifikasi tanaman. Buku seperti \"Edible Wild Plants\" oleh John Kallas dan aplikasi seperti PlantNet dapat memberikan informasi berharga.<br>" +
                "Mengikuti Workshop: Ikuti workshop atau tur foraging yang dipandu oleh ahli. Pengalaman langsung ini sangat berharga.</p>" +

                "<h4>2. <b>Identifikasi Tanaman dengan Seksama</b></h4>" +
                "<p>&gt; <b>Perhatikan Ciri-ciri</b><br>" +
                "Catat dengan cermat ciri-ciri daun, bunga, buah, batang, dan akar tanaman. Setiap karakteristik dapat membantu dalam identifikasi yang akurat.<br>" +
                "&gt; <b>Gunakan Beberapa Sumber</b><br>" +
                "Pastikan identitas tanaman menggunakan beberapa sumber atau metode identifikasi untuk memastikan keakuratan. Salah identifikasi bisa berbahaya.</p>" +

                "<h4>3. <b>Periksa Tanaman Beracun yang Diketahui</b></h4>" +
                "<p>&gt; <b>Ketahui Tanaman Beracun Lokal</b><br>" +
                "Biasakan diri dengan tanaman beracun yang umum di daerah tersebut. Hindari tanaman yang sangat mirip dengan spesies beracun tersebut.<br>" +
                "&gt; <b>Konsultasi Pedoman Lokal</b><br>" +
                "Beberapa daerah memiliki pedoman spesifik tentang tanaman mana yang aman untuk dikonsumsi.</p>" +

                "<h4>4. <b>Lakukan Tes Kelayakan (Sebagai Upaya Terakhir)</b></h4>" +
                "<p>Jika Anda tidak yakin dan dalam situasi bertahan hidup, gunakan Universal Edibility Test untuk menentukan apakah tanaman aman dimakan. Tes ini harus digunakan dengan hati-hati dan hanya saat benar-benar diperlukan:<br>" +
                "&gt; <b>Kontak Kulit</b><br>" +
                "Hancurkan bagian dari tanaman dan aplikasikan pada kulit Anda (bagian dalam siku atau pergelangan tangan) selama beberapa jam untuk memeriksa reaksi alergi.<br>" +
                "&gt; <b>Tes Bibir</b><br>" +
                "Letakkan sepotong tanaman di bibir Anda untuk memeriksa rasa terbakar atau gatal.<br>" +
                "&gt; <b>Tes Lidah</b><br>" +
                "Letakkan sepotong tanaman di lidah Anda selama 15 menit dan tunggu reaksi.<br>" +
                "&gt; <b>Kunyah dan Ludah</b><br>" +
                "Jika tidak ada reaksi, kunyah sedikit dan ludahkan, tunggu satu jam lagi.<br>" +
                "&gt; <b>Makan Sedikit</b><br>" +
                "Jika tidak ada reaksi buruk terjadi, makan sedikit dan tunggu beberapa jam. Pantau respons tubuh Anda.<br>" +
                "&gt; <b>Lanjutkan Secara Bertahap</b><br>" +
                "Jika tidak terjadi efek samping, Anda dapat mengonsumsi lebih banyak.</p>" +

                "<h4>5. <b>Pertimbangkan Metode Persiapan</b></h4>" +
                "<p>Beberapa tanaman memerlukan metode persiapan khusus untuk dapat dimakan atau lebih mudah dicerna, seperti memasak, mencuci, atau fermentasi.</p>" +
                "</body></html>";

        poisonTestWebView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
    }

    public void goBack(View view) {
        finish(); // Close the current activity and return to the previous one
    }
}
