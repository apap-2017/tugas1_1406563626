package com.example.mapper;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import org.apache.ibatis.annotations.*;

import com.example.model.KelurahanModel;

@Mapper
public interface KelurahanMapper
{
    @Select("SELECT * FROM siduk_dki.kelurahan WHERE id = #{id}")
    KelurahanModel selectKelurahanId (@Param("id") int id);

    @Select("SELECT id_kecamatan FROM siduk_dki.kelurahan WHERE id = #{id}")
    int selectKelurahanKecamatan (@Param("id") int id);
}
