package go.id.jambiprov.responsi;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    TextView keterangan,judul;
    ImageView gambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        keterangan = findViewById(R.id.ket);
        judul = findViewById(R.id.judul);
        gambar = findViewById(R.id.gambar);
        String BaseGambar = "http://corona.jambiprov.go.id/api/responsi/gambar/";
        String id_berita = getIntent().getStringExtra("IDBERITA");
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<DataBerita> detail = apiInterface.cekDetail(id_berita);
        detail.enqueue(new Callback<DataBerita>() {
            @Override
            public void onResponse(Call<DataBerita> call, Response<DataBerita> response) {
                judul.setTypeface(judul.getTypeface(), Typeface.BOLD);
                judul.setText("\n"+response.body().getJudul());
                keterangan.setText("\n"+response.body().getIsi());
                Picasso.get().load(BaseGambar+response.body().getGambar()).into(gambar);
            }

            @Override
            public void onFailure(Call<DataBerita> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Gagal Menampilkan Berita, Periksa Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
