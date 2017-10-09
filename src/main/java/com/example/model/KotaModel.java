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
public class KotaModel {
    private int id;
    private String kode_kota;
    private String nama_kota;
}
