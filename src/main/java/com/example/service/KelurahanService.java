package com.example.service;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import com.example.model.KelurahanModel;

import java.util.List;

public interface KelurahanService {
    List<KelurahanModel> selectAllKelurahan ();
    KelurahanModel selectKelurahanId (int id);
    List<KelurahanModel> selectKelurahanKecamatan (int id_kecamatan);
}
