package com.example.yolov5_objdet;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class SintrongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintrong);

        TextView sintrongTextView = findViewById(R.id.sintrongTextView);
        String htmlText = "<p style='text-align:justify;'>" +
                "<b>Sintrong</b> (dikenal juga sebagai daun sentrong atau <i>Crassocephalum crepidioides</i>) bisa dikonsumsi dan cukup populer di beberapa daerah di Indonesia serta beberapa negara lainnya. Daun sintrong merupakan bagian dari keluarga <i>Asteraceae</i> dan sering digunakan dalam masakan tradisional karena kaya akan nutrisi.<br><br>" +
                "<b>Manfaat Daun Sintrong</b><br>" +
                "Kaya Nutrisi: Daun sintrong kaya akan vitamin C, vitamin A, dan mineral seperti zat besi, yang sangat baik untuk kesehatan mata dan sistem imun.<br>" +
                "Antioksidan: Mengandung antioksidan yang membantu melawan radikal bebas dan dapat mengurangi risiko beberapa penyakit kronis.<br>" +
                "Anti-inflamasi: Memiliki sifat anti-inflamasi yang dapat membantu mengurangi peradangan dan nyeri pada tubuh.<br><br>" +
                "<b>Cara Mengolah Daun Sintrong</b><br>" +
                "Daun sintrong bisa dimasak dengan berbagai cara, namun berikut adalah beberapa cara umum untuk mengolahnya agar aman dan enak untuk dikonsumsi:<br>" +
                "Pencucian: Cuci bersih daun sintrong untuk menghilangkan kotoran atau sisa-sisa pestisida yang mungkin melekat. Gunakan air mengalir dan mungkin sedikit garam untuk membantu menghilangkan kotoran lebih efektif.<br>" +
                "Rebusan: Daun sintrong sering direbus terlebih dahulu untuk menghilangkan rasa pahit. Rebus daun dalam air mendidih selama beberapa menit, lalu buang airnya.<br>" +
                "Tumisan: Setelah direbus, daun sintrong bisa ditumis dengan bumbu seperti bawang putih, bawang merah, dan cabai untuk menambah rasa. Tumis hingga matang dan tambahkan sedikit garam atau penyedap rasa sesuai selera.<br>" +
                "Campuran dalam Sayur: Daun sintrong juga bisa dicampur dengan sayur lain dalam membuat sup atau sayur bening. Ini menambahkan rasa dan nutrisi pada masakan.<br>" +
                "Pelengkap dalam Salad: Daun muda dari sintrong yang masih segar bisa juga digunakan sebagai pelengkap dalam salad, mirip dengan penggunaan daun selada atau arugula.<br><br>" +
                "Pilih daun sintrong yang segar dan hijau, tanpa tanda kekuningan atau kerusakan. Simpan di kulkas dalam wadah tertutup atau plastik agar tetap segar lebih lama. Secara keseluruhan, daun sintrong adalah tambahan yang sehat dan lezat untuk berbagai hidangan, dan pengolahannya relatif mudah. Pastikan untuk memasaknya dengan benar untuk menikmati manfaatnya secara maksimal." +
                "</p>";

        sintrongTextView.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT));
    }

    public void goBack(View view) {
        finish(); // Close the current activity and return to the previous one
    }
}