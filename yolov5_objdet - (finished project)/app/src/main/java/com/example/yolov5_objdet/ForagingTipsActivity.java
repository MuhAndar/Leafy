package com.example.yolov5_objdet;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class ForagingTipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foraging_tips);

        WebView foragingTipsWebView = findViewById(R.id.foragingTipsWebView);

        String content = "<html><body style=\"text-align:justify;\">" +
                "<p>Kegiatan <b>Meramban</b> atau <b>Foraging</b> adalah aktivitas mengumpulkan makanan liar seperti buah, sayur, dan jamur yang tumbuh secara alami di alam. " +
                "Aktivitas ini tidak hanya bisa menjadi hobi yang menyenangkan tetapi juga cara untuk terhubung dengan alam dan memahami lebih dalam tentang sumber makanan lokal. " +
                "Berikut adalah beberapa informasi penting dan tips yang perlu Anda ketahui saat melakukan kegiatan meramban:</p>" +

                "<h4>Informasi Penting:</h4>" +
                "<p>&gt; <b>Pengetahuan tentang Tanaman:</b><br>" +
                "Penting untuk memiliki pengetahuan yang baik tentang tanaman yang aman untuk dikonsumsi dan yang beracun. " +
                "Beberapa tanaman yang tampak menarik bisa sangat berbahaya jika dikonsumsi.<br>" +
                "&gt; <b>Peraturan Lokal:</b><br>" +
                "Pastikan Anda memahami dan mengikuti peraturan lokal tentang foraging. " +
                "Beberapa area mungkin memiliki aturan ketat mengenai mengumpulkan tanaman atau jamur tertentu.<br>" +
                "&gt; <b>Respek terhadap Alam:</b><br>" +
                "Selalu foraging dengan cara yang berkelanjutan. " +
                "Ambil hanya sebagian kecil dari apa yang Anda temukan untuk memastikan bahwa tanaman tersebut bisa terus tumbuh dan dinikmati oleh orang lain.</p>" +

                "<h4>Tips Meramban:</h4>" +
                "<h4>1. <b>Identifikasi dengan Akurat:</b></h4>" +
                "<p>&gt; Gunakan aplikasi identifikasi tanaman, buku panduan, atau workshop lokal untuk belajar mengenali tanaman dan jamur.<br>" +
                "&gt; Pertimbangkan untuk bergabung dengan kelompok foraging lokal atau mengikuti panduan berpengalaman.</p>" +

                "<h4>2. <b>Alat yang Tepat:</b></h4>" +
                "<p>&gt; Bawa kantong atau keranjang untuk mengumpulkan hasil foraging Anda.<br>" +
                "&gt; Gunakan pisau atau gunting taman untuk memotong tanaman atau jamur, yang bisa membantu mengurangi kerusakan pada organisme tersebut.</p>" +

                "<h4>3. <b>Pakaian yang Sesuai:</b></h4>" +
                "<p>&gt; Pakailah pakaian yang nyaman dan melindungi tubuh dari serangga, duri, dan tanaman beracun.<br>" +
                "&gt; Kenakan sepatu hiking yang kuat dan nyaman untuk berjalan di alam.</p>" +

                "<h4>4. <b>Sikap Berhati-hati:</b></h4>" +
                "<p>&gt; Jangan mengonsumsi tanaman atau jamur kecuali Anda 100% yakin akan identitas dan keamanannya.<br>" +
                "&gt; Kenali tanaman beracun yang tumbuh di area Anda dan pelajari cara mengidentifikasinya.</p>" +

                "<h4>5. <b>Bijak dalam Memanen:</b></h4>" +
                "<p>&gt; Panen dengan cara yang tidak mengganggu pertumbuhan tanaman. Misalnya, jangan mencabut akar tanaman kecuali sangat diperlukan.<br>" +
                "&gt; Tinggalkan cukup banyak tanaman atau buah untuk reproduksi dan sebagai makanan bagi satwa liar.</p>" +

                "<h4>6. <b>Periksa Cuaca dan Kondisi Alam:</b></h4>" +
                "<p>&gt; Periksa prakiraan cuaca sebelum berangkat untuk menghindari kondisi yang buruk.<br>" +
                "&gt; Pahami dan persiapkan diri terhadap potensi bahaya alam di area foraging, seperti sungai yang deras atau area yang mungkin ada binatang buas.</p>" +

                "<p>Dengan mengikuti informasi dan tips ini, Anda bisa menikmati kegiatan meramban yang aman dan menyenangkan sambil memberi manfaat pada kesehatan dan keberlanjutan lingkungan. " +
                "Selalu ingat bahwa keselamatan dan keberlanjutan adalah prioritas utama dalam kegiatan meramban.</p>" +
                "</body></html>";

        foragingTipsWebView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
    }

    public void goBack(View view) {
        finish(); // Close the current activity and return to the previous one
    }
}
