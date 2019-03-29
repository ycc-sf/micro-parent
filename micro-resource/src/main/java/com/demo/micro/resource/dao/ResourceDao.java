package com.demo.micro.resource.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.demo.micro.resource.entity.Info;
import com.demo.micro.resource.entity.Role;
import com.demo.micro.resource.entity.UserInfo;

public interface ResourceDao {
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

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    List<UserInfo> findUserByUsername(@Param("username") String username);
    
    /**
     * 通过角色id查找角色
     * @param id
     * @return
     */
    Role findRoleById(@Param("id") Long id);
}
