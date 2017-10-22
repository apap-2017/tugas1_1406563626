package com.example.mapper;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import org.apache.ibatis.annotations.*;

import com.example.model.KeluargaModel;

import java.util.List;

@Mapper
public interface KeluargaMapper
{
    @Select("SELECT * FROM siduk_dki.keluarga WHERE nomor_kk = #{nomor_kk}")
    KeluargaModel selectKeluarga (@Param("nomor_kk") String nomor_kk);

    @Select("SELECT * FROM siduk_dki.keluarga")
    List<KeluargaModel> selectAllKeluarga ();

    @Select("SELECT * FROM siduk_dki.keluarga k, siduk_dki.kelurahan kel WHERE k.id_kelurahan = kel.id AND kel.id_kecamatan = #{id_kecamatan}")
    List<KeluargaModel> selectKeluargaKecamatan (@Param("id_kecamatan") int id_kecamatan);

    @Select("SELECT * FROM siduk_dki.keluarga WHERE id = #{id}")
    KeluargaModel selectKeluargaId (@Param("id") int id);

    @Insert("INSERT INTO siduk_dki.keluarga (nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku) VALUES (#{nomor_kk}, #{alamat}, #{rt}, #{rw}, #{id_kelurahan}, '0')")
    void addKeluarga (KeluargaModel keluarga);

    @Select("SELECT DISTINCT COUNT(p.nama) FROM siduk_dki.penduduk p, siduk_dki.keluarga k WHERE p.id_keluarga = k.id AND p.is_wafat = '0' AND k.nomor_kk = #{nomor_kk}")
    int keluargaSize (KeluargaModel keluarga);

    @Update("UPDATE siduk_dki.keluarga SET is_tidak_berlaku = '1' WHERE nomor_kk = #{nomor_kk}")
    void setKeluargaTidakBerlaku (KeluargaModel keluarga);

    @Insert("UPDATE siduk_dki.keluarga SET nomor_kk = #{keluarga.nomor_kk}, alamat = #{keluarga.alamat}, rt = #{keluarga.rt}, rw = #{keluarga.rw}, id_kelurahan = #{keluarga.id_kelurahan} WHERE nomor_kk = #{nkk_lama}")
    void updateKeluarga (@Param("keluarga") KeluargaModel keluarga, @Param("nkk_lama") String nkk_lama);
}
