package com.example.service;

/**
 * Created by hasyapasaribu on 10/4/17.
 */

import com.example.model.PendudukModel;

import java.util.List;

public interface PendudukService
{
    PendudukModel selectPenduduk(String nik);

    List<PendudukModel> selectPendudukKeluarga(int id_keluarga);

    List<PendudukModel> selectPendudukKelurahan(int id_kelurahan);

    List<PendudukModel> selectSimilarPenduduk(String tanggal_lahir, int id_kelurahan, int id_kecamatan, int id_kota);

    void setWafatPenduduk(String nik);

    void updatePenduduk(PendudukModel penduduk, String nik_lama);

    void addPenduduk(PendudukModel penduduk);

    List<PendudukModel> selectPendudukLakiLaki();

    List<PendudukModel> selectPendudukPerempuan();
}
