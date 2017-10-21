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
    KeluargaModel selectKeluargaId(int id);
    int selectKeluargaKelurahan(int id);
}
