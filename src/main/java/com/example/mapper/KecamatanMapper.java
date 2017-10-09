package com.example.mapper;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import org.apache.ibatis.annotations.*;

import com.example.model.KecamatanModel;

@Mapper
public interface KecamatanMapper
{
    @Select("SELECT * FROM siduk_dki.kecamatan WHERE id = #{id}")
    KecamatanModel selectKecamatanId (@Param("id") int id);

    @Select("SELECT id_kota FROM siduk_dki.kecamatan WHERE id = #{id}")
    int selectKecamatanKota (@Param("id") int id);
}
