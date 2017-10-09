package com.example.mapper;

/**
 * Created by hasyapasaribu on 10/8/17.
 */

import org.apache.ibatis.annotations.*;

import com.example.model.KotaModel;

@Mapper
public interface KotaMapper {
    @Select("SELECT * FROM siduk_dki.kota WHERE id = #{id}")
    KotaModel selectKotaId (@Param("id") int id);
}
