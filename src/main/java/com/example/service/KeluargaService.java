package com.example.service;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import com.example.model.KeluargaModel;

import java.util.List;

public interface KeluargaService
{
    KeluargaModel selectKeluarga(String nkk);
    List<KeluargaModel> selectAllKeluarga();
    List<KeluargaModel> selectKeluargaKecamatan(int id_kecamatan);
    KeluargaModel selectKeluargaId(int id);
    void addKeluarga(KeluargaModel keluarga);
}
