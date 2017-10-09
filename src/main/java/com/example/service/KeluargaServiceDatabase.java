package com.example.service;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.KeluargaMapper;
import com.example.model.KeluargaModel;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService
{
    @Autowired
    private KeluargaMapper keluargaMapper;

    @Override
    public KeluargaModel selectKeluarga (String nomor_kk)
    {
        return keluargaMapper.selectKeluarga (nomor_kk);
    }

    @Override
    public KeluargaModel selectKeluargaId (int id)
    {
        return keluargaMapper.selectKeluargaId (id);
    }

    @Override
    public int selectKeluargaKelurahan (int id)
    {
        return keluargaMapper.selectKeluargaKelurahan (id);
    }
}
