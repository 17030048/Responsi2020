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

public class RegisterActivity extends AppCompatActivity{
    TextView text_username,text_password,text_login;
    Button btn_daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ProgressDialog dialog = new ProgressDialog(RegisterActivity.this);
        text_username = findViewById(R.id.text_username);
        text_password = findViewById(R.id.text_password);
        btn_daftar = findViewById(R.id.btn_daftar);
        text_login = findViewById(R.id.text_login);

        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text_username.getText().toString().matches("") || text_password.getText().toString().matches("")){
                    Toast.makeText(RegisterActivity.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }else{
                    dialog.setMessage("Tunggu..");
                    dialog.show();
                    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<DataBerita> cekReg = apiInterface.cekReg(text_username.getText().toString(),text_password.getText().toString());
                    cekReg.enqueue(new Callback<DataBerita>() {
                        @Override
                        public void onResponse(Call<DataBerita> call, Response<DataBerita> response) {
                            if(response.body().getKode()==1){
                                dialog.dismiss();
                                Toast.makeText(RegisterActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                startActivity(intent);
                                finish();
                            }else{
                                dialog.dismiss();
                                Toast.makeText(RegisterActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DataBerita> call, Throwable t) {
                            dialog.dismiss();
                            Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
