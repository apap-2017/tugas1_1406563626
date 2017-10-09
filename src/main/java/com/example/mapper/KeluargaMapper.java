package com.example.mapper;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import org.apache.ibatis.annotations.*;

import com.example.model.KeluargaModel;

@Mapper
public interface KeluargaMapper
{
    @Select("SELECT * FROM siduk_dki.keluarga WHERE nomor_kk = #{nomor_kk}")
    KeluargaModel selectKeluarga (@Param("nomor_kk") String nomor_kk);

    @Select("SELECT * FROM siduk_dki.keluarga WHERE id = #{id}")
    KeluargaModel selectKeluargaId (@Param("id") int id);

    @Select("SELECT id_kelurahan FROM siduk_dki.keluarga WHERE id = #{id}")
    int selectKeluargaKelurahan (@Param("id") int id);


}
