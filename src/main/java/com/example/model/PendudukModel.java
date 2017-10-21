package com.example.model;

/**
 * Created by hasyapasaribu on 10/3/17.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukModel {
    private int id;
    private String nik;
    private String nama;
    private String tempat_lahir;
    private Date tanggal_lahir;
    private int jenis_kelamin;
    private boolean is_wni;
    private int id_keluarga;
    private String agama;
    private String pekerjaan;
    private String status_perkawinan;
    private String status_dalam_keluarga;
    private String golongan_darah;
    private boolean is_wafat;

    public boolean isIs_wni() {
        return is_wni;
    }

    public void setIs_wni(boolean is_wni) {
        this.is_wni = is_wni;
    }

    public boolean isIs_wafat() {
        return is_wafat;
    }

    public void setIs_wafat(boolean is_wafat) {
        this.is_wafat = is_wafat;
    }
}
