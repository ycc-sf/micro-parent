package com.demo.micro.resource.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.micro.resource.entity.Info;

public interface AdminDao {
    /**
     * 根据位置获取指定范围内，指定数量的信息
     * @param x
     * @param y
     * @param range
     * @param number
     * @return
     */
    List<Info> selectRangeInfo(@Param("infoType") Long infoType, @Param("title") String title,
                               @Param("x") double x, @Param("y") double y,
                               @Param("range") double range, @Param("number") int number);

}
