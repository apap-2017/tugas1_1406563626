package com.example.service;

/**
 * Created by hasyapasaribu on 10/4/17.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.PendudukMapper;
import com.example.model.PendudukModel;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService
{
    @Autowired
    private PendudukMapper pendudukMapper;

    @Override
    public PendudukModel selectPenduduk (String nik)
    {
        log.info ("select penduduk with nik {}", nik);
        return pendudukMapper.selectPenduduk (nik);
    }

    @Override
    public List<PendudukModel> selectPendudukKeluarga (int id_keluarga)
    {
        log.info ("select penduduk with id keluarga {}", id_keluarga);
        return pendudukMapper.selectPendudukKeluarga(id_keluarga);
    }

    @Override
    public void setWafatPenduduk (String nik)
    {
        log.info ("set penduduk with nik {} wafat", nik);
        pendudukMapper.setWafatPenduduk (nik);
    }
}
