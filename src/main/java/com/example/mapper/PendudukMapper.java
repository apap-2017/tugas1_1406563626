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

    @Select("SELECT DISTINCT p.nama FROM siduk_dki.penduduk p, siduk_dki.keluarga k, siduk_dki.kelurahan kel, siduk_dki.kecamatan kec, siduk_dki.kota ko WHERE p.tanggal_lahir = #{tanggal_lahir} AND p.id_keluarga = k.id AND k.id_kelurahan = #{id_kelurahan} AND kel.id_kecamatan = #{id_kecamatan} AND kec.id_kota = #{id_kota}")
    List<PendudukModel> selectSimilarPenduduk (@Param("tanggal_lahir") String tanggal_lahir, @Param("id_kelurahan") int id_kelurahan, @Param("id_kecamatan") int id_kecamatan, @Param("id_kota") int id_kota);

    @Update("UPDATE siduk_dki.penduduk SET is_wafat = '1' WHERE nik = #{nik}")
    void setWafatPenduduk (@Param("nik") String nik);

    @Update("UPDATE siduk_dki.penduduk SET nama = #{nama}, jenis_kelamin = #{jenis_kelamin}, golongan_darah = #{golongan_darah}, agama = #{agama}, status_perkawinan = #{status_perkawinan}, pekerjaan = #{pekerjaan}, is_wni = #{is_wni}, id_keluarga = #{id_keluarga} WHERE nik = #{nik}")
    void updatePenduduk (PendudukModel penduduk);

    @Insert("INSERT INTO siduk_dki.penduduk (id, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat) VALUES (#{id}, #{nik}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{is_wni}, #{id_keluarga}, #{agama}, #{pekerjaan}, #{status_perkawinan}, #{status_dalam_keluarga}, #{golongan_darah}, '0')")
    void addPenduduk (PendudukModel penduduk);

    @Select("SELECT * FROM siduk_dki.penduduk WHERE jenis_kelamin = '0'")
    List<PendudukModel> selectPendudukLakiLaki ();

    @Select("SELECT * FROM siduk_dki.penduduk WHERE jenis_kelamin = '1'")
    List<PendudukModel> selectPendudukPerempuan ();
}
