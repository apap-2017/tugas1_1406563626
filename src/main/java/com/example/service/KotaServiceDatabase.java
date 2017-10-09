package com.example.service;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.KotaMapper;
import com.example.model.KotaModel;

@Slf4j
@Service
public class KotaServiceDatabase implements KotaService
{
    @Autowired
    private KotaMapper kotaMapper;

    @Override
    public KotaModel selectKotaId (int id){
        return kotaMapper.selectKotaId (id);
    }
}