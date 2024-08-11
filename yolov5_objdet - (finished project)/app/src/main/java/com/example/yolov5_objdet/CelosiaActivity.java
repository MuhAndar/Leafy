package com.example.yolov5_objdet;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class CelosiaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celosia);

        WebView celosiaWebView = findViewById(R.id.celosiaWebView);

        String content = "<html><body style=\"text-align:justify;\">" +
                "<p><b>Celosia</b>, yang sering dikenal sebagai bunga jengger ayam atau bunga wuluh, adalah tanaman yang termasuk dalam keluarga Amaranthaceae. Tanaman ini dikenal dengan bunga-bunganya yang mencolok dan sering digunakan dalam dekorasi taman. Selain itu, beberapa spesies Celosia juga digunakan sebagai sayuran dan obat herbal.</p>" +
                "<h4><b>Manfaat Tanaman Celosia</b></h4>" +
                "<p><b>1. Kaya Nutrisi</b>: Daun dan bunga Celosia kaya akan vitamin A, vitamin C, dan zat besi, yang baik untuk kesehatan mata, sistem kekebalan tubuh, dan pembentukan darah.<br>" +
                "<b>2. Antioksidan</b>: Mengandung antioksidan yang dapat membantu melawan radikal bebas dan mengurangi risiko beberapa penyakit kronis.<br>" +
                "<b>3. Anti-inflamasi</b>: Memiliki sifat anti-inflamasi yang dapat membantu mengurangi peradangan dan nyeri pada tubuh.<br>" +
                "<b>4. Sumber Protein</b>: Di beberapa daerah, terutama di Afrika, daun Celosia digunakan sebagai sumber protein nabati yang penting.</p>" +
                "<h4><b>Cara Mengolah Tanaman Celosia</b></h4>" +
                "<p><b>1. Pencucian</b>: Cuci bersih daun dan bunga Celosia untuk menghilangkan kotoran atau sisa-sisa pestisida yang mungkin melekat. Gunakan air mengalir dan mungkin sedikit garam untuk membantu menghilangkan kotoran lebih efektif.<br>" +
                "<b>2. Rebusan</b>: Daun Celosia sering direbus terlebih dahulu untuk menghilangkan rasa pahit. Rebus dalam air mendidih selama beberapa menit, lalu buang airnya.<br>" +
                "<b>3. Tumisan</b>: Setelah direbus, daun Celosia bisa ditumis dengan bumbu seperti bawang putih, bawang merah, dan cabai untuk menambah rasa. Tumis hingga matang dan tambahkan sedikit garam atau penyedap rasa sesuai selera.<br>" +
                "<b>4. Campuran dalam Sayur</b>: Daun Celosia juga bisa dicampur dengan sayur lain dalam membuat sup atau sayur bening. Ini menambahkan rasa dan nutrisi pada masakan.<br>" +
                "<b>5. Pelengkap dalam Salad</b>: Daun muda Celosia yang masih segar bisa juga digunakan sebagai pelengkap dalam salad, mirip dengan penggunaan daun selada atau arugula.</p>" +
                "<h4><b>Kiat Memilih dan Menyimpan</b></h4>" +
                "<p>Pilih daun dan bunga Celosia yang segar dan hijau, tanpa tanda kekuningan atau kerusakan. Simpan di kulkas dalam wadah tertutup atau plastik agar tetap segar lebih lama.</p>" +
                "<p>Secara keseluruhan, tanaman Celosia adalah tambahan yang sehat dan lezat untuk berbagai hidangan, dan pengolahannya relatif mudah. Pastikan untuk memasaknya dengan benar untuk menikmati manfaatnya secara maksimal.</p>" +
                "</body></html>";

        celosiaWebView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
    }

    public void goBack(View view) {
        finish(); // Close the current activity and return to the previous one
    }
}
