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

    @Insert("INSERT INTO siduk_dki.keluarga (id, nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku) VALUES (#{id}, #{nomor_kk}, #{alamat}, #{rt}, #{rw}, #{id_kelurahan}, '0')")
    void addKeluarga (KeluargaModel keluarga);
}
