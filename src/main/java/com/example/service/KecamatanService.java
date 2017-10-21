package com.example.service;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import com.example.model.KecamatanModel;

import java.util.List;

public interface KecamatanService {
    List<KecamatanModel> selectAllKecamatan();
    KecamatanModel selectKecamatanId(int id);
    int selectKecamatanKota(int id);
}
