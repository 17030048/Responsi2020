package go.id.jambiprov.responsi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("berita")
    Call<Result> cekBerita();

    @FormUrlEncoded
    @POST("berita")
    Call<DataBerita> cekDetail(@Field("id_berita") String id_berita);

    @FormUrlEncoded
    @POST("login")
    Call<DataBerita> cekLogin(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<DataBerita> cekReg(@Field("username") String username,@Field("password") String password);
}
