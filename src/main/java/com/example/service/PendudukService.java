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

    void setWafatPenduduk(String nik);
}
