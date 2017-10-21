package com.example.service;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import com.example.model.KotaModel;

import java.util.List;

public interface KotaService {
    List<KotaModel> selectAllKota();
    KotaModel selectKotaId(int id);
}
