package com.example.service;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import com.example.model.KecamatanModel;

public interface KecamatanService {
    KecamatanModel selectKecamatanId(int id);
    int selectKecamatanKota(int id);
}
