package go.id.jambiprov.responsi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity{
    TextView text_username,text_password,text_reg;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ProgressDialog dialog = new ProgressDialog(Dashboard.this);
        text_username = findViewById(R.id.text_username);
        text_password = findViewById(R.id.text_password);
        text_reg = findViewById(R.id.text_register);
        btn_login = findViewById(R.id.btn_login);

        text_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("Tunggu..");
                dialog.show();
                ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                Call<DataBerita> cekLogin = apiInterface.cekLogin(text_username.getText().toString(),text_password.getText().toString());
                cekLogin.enqueue(new Callback<DataBerita>() {
                    @Override
                    public void onResponse(Call<DataBerita> call, Response<DataBerita> response) {
                        if(response.body().getKode()==1){
                            dialog.dismiss();
                            Toast.makeText(Dashboard.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), BeritaActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            dialog.dismiss();
                            Toast.makeText(Dashboard.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DataBerita> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(Dashboard.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
