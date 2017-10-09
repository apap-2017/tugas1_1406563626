package com.example.mapper;

/**
 * Created by hasyapasaribu on 10/4/17.
 */

import org.apache.ibatis.annotations.*;

import com.example.model.PendudukModel;

import java.util.List;

@Mapper
public interface PendudukMapper
{
    @Select("SELECT * FROM siduk_dki.penduduk WHERE nik = #{nik}")
    PendudukModel selectPenduduk (@Param("nik") String nik);

    @Select("SELECT * FROM siduk_dki.penduduk WHERE id_keluarga = #{id_keluarga}")
    List<PendudukModel> selectPendudukKeluarga (@Param("id_keluarga") int id_keluarga);

    @Update("UPDATE siduk_dki.penduduk SET is_wafat = '1' WHERE nik = #{nik}")
    void setWafatPenduduk (@Param("nik") String nik);
}
