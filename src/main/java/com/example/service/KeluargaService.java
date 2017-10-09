package com.example.service;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import com.example.model.KeluargaModel;

public interface KeluargaService
{
    KeluargaModel selectKeluarga(String nkk);
    KeluargaModel selectKeluargaId(int id);
    int selectKeluargaKelurahan(int id);
}
