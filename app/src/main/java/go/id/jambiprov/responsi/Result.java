package go.id.jambiprov.responsi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result {
    @SerializedName("result")
    ArrayList<DataBerita>result;
    public ArrayList<DataBerita>getResult(){
        return result;
    }
}
