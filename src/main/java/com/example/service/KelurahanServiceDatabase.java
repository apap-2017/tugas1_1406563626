package com.example.service;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.KelurahanMapper;
import com.example.model.KelurahanModel;

@Slf4j
@Service
public class KelurahanServiceDatabase implements KelurahanService
{
    @Autowired
    private KelurahanMapper kelurahanMapper;

    @Override
    public List<KelurahanModel> selectAllKelurahan (){
        return kelurahanMapper.selectAllKelurahan ();
    }

    @Override
    public KelurahanModel selectKelurahanId (int id){
        return kelurahanMapper.selectKelurahanId (id);
    }

    @Override
    public List<KelurahanModel> selectKelurahanKecamatan (int id_kecamatan) {
        log.info("select all kelurahan with id kecamatan {}", id_kecamatan);
        return kelurahanMapper.selectKelurahanKecamatan(id_kecamatan);
    }
}
