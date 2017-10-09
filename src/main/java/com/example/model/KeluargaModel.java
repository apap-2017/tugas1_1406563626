package com.example.model;

/**
 * Created by hasyapasaribu on 10/4/17.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeluargaModel {
    private int id;
    private String nomor_kk;
    private String alamat;
    private String rt;
    private String rw;
    private int id_kelurahan;
    private int is_tidak_berlaku;
}
