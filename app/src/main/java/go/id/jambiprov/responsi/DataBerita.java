package go.id.jambiprov.responsi;

import com.google.gson.annotations.SerializedName;

public class DataBerita {
    @SerializedName("kode")
    private Integer kode;
    @SerializedName("id_berita")
    private String idberita;
    @SerializedName("judul")
    private String judul;
    @SerializedName("isi")
    private String isi;
    @SerializedName("gambar")
    private String gambar;
    @SerializedName("pesan")
    private String pesan;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public Integer getKode() {
        return kode;
    }

    public String getGambar(){ return gambar; }
    public String getIdberita() { return idberita; }
    public String getJudul() { return judul; }
    public String getIsi() { return isi; }

    public String getPesan() {
        return pesan;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
