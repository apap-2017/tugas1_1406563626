package com.example.mapper;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import org.apache.ibatis.annotations.*;

import com.example.model.KelurahanModel;

import java.util.List;

@Mapper
public interface KelurahanMapper
{
    @Select("SELECT * FROM siduk_dki.kelurahan")
    List<KelurahanModel> selectAllKelurahan ();

    @Select("SELECT * FROM siduk_dki.kelurahan WHERE id_kecamatan = #{id_kecamatan}")
    List<KelurahanModel> selectKelurahanKecamatan (@Param("id_kecamatan") int id_kecamatan);

    @Select("SELECT * FROM siduk_dki.kelurahan WHERE id = #{id}")
    KelurahanModel selectKelurahanId (@Param("id") int id);
}
