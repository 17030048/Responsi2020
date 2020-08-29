package go.id.jambiprov.responsi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BaseUrl = "http://corona.jambiprov.go.id/api/responsi/api/";
    private static Retrofit retrofit;
    public  static Retrofit getApiClient(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120,TimeUnit.SECONDS).build();
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BaseUrl).client(client).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
