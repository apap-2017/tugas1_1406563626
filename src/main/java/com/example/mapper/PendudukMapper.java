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

    @Update("UPDATE siduk_dki.penduduk SET nama = #{nama}, golongan_darah = #{golongan_darah}, agama = #{agama}, status_perkawinan = #{status_perkawinan}, pekerjaan = #{pekerjaan}, is_wni = #{is_wni}, id_keluarga = #{id_keluarga} WHERE nik = #{nik}")
    void updatePenduduk (PendudukModel penduduk);

    @Insert("INSERT INTO siduk_dki.penduduk (id, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat) VALUES (#{id}, #{nik}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{is_wni}, #{id_keluarga}, #{agama}, #{pekerjaan}, #{status_perkawinan}, #{status_dalam_keluarga}, #{golongan_darah}, '0'")
    void addPenduduk (PendudukModel penduduk);

    @Select("SELECT * FROM siduk_dki.penduduk WHERE jenis_kelamin = '0'")
    List<PendudukModel> selectPendudukLakiLaki ();

    @Select("SELECT * FROM siduk_dki.penduduk WHERE jenis_kelamin = '1'")
    List<PendudukModel> selectPendudukPerempuan ();
}
