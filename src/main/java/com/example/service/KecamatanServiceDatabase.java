package com.example.service;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.KecamatanMapper;
import com.example.model.KecamatanModel;

@Slf4j
@Service
public class KecamatanServiceDatabase implements KecamatanService {
    @Autowired
    private KecamatanMapper kecamatanMapper;

    @Override
    public KecamatanModel selectKecamatanId(int id) {
        return kecamatanMapper.selectKecamatanId(id);
    }

    @Override
    public int selectKecamatanKota(int id) {
        return kecamatanMapper.selectKecamatanKota(id);

    }
}