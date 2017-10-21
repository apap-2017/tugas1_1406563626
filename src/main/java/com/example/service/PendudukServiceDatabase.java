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
    public List<PendudukModel> selectSimilarPenduduk (String tanggal_lahir, int id_kelurahan, int id_kecamatan, int id_kota)
    {
        log.info ("select penduduk with tanggal_lahir {} id kelurahan {} id kecamatan {} id kota {}", tanggal_lahir, id_kelurahan, id_kecamatan, id_kota);
        return pendudukMapper.selectSimilarPenduduk(tanggal_lahir, id_kelurahan, id_kecamatan, id_kota);
    }

    @Override
    public void setWafatPenduduk (String nik)
    {
        log.info ("set penduduk with nik {} wafat", nik);
        pendudukMapper.setWafatPenduduk (nik);
    }

    @Override
    public void updatePenduduk (PendudukModel penduduk)
    {
        log.info ("update penduduk {}", penduduk);
        pendudukMapper.updatePenduduk (penduduk);
    }

    @Override
    public void addPenduduk (PendudukModel penduduk)
    {
        log.info ("add penduduk {}", penduduk);
        pendudukMapper.addPenduduk(penduduk);
    }

    @Override
    public List<PendudukModel> selectPendudukLakiLaki ()
    {
        log.info ("select penduduk laki-laki");
        return pendudukMapper.selectPendudukLakiLaki ();
    }

    @Override
    public List<PendudukModel> selectPendudukPerempuan ()
    {
        log.info ("select penduduk perempuan");
        return pendudukMapper.selectPendudukPerempuan ();
    }
}
