package com.example.model;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanModel {
    private int id;
    private int id_kecamatan;
    private String kode_kelurahan;
    private String nama_kelurahan;
    private String kode_pos;
}
