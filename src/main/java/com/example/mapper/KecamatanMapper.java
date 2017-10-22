package com.example.mapper;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import org.apache.ibatis.annotations.*;

import com.example.model.KecamatanModel;

import java.util.List;

@Mapper
public interface KecamatanMapper
{
    @Select("SELECT * FROM siduk_dki.kecamatan")
    List<KecamatanModel> selectAllKecamatan ();

    @Select("SELECT * FROM siduk_dki.kecamatan WHERE id = #{id}")
    KecamatanModel selectKecamatanId (@Param("id") int id);

    @Select("SELECT * FROM siduk_dki.kecamatan WHERE id_kota = #{id_kota}")
    List<KecamatanModel> selectKecamatanKota (@Param("id_kota") int id_kota);
}
