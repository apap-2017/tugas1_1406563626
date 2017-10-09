package com.example.service;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import com.example.model.KelurahanModel;

public interface KelurahanService {
    KelurahanModel selectKelurahanId (int id);
    int selectKelurahanKecamatan (int id);
}
