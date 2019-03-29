package com.demo.micro.resource.service;

import java.util.List;

import com.demo.micro.resource.entity.Info;

/**
 * 资源服务
 */
public interface AdminService {
    /**
     * 根据位置获取指定范围内，指定数量的信息
     * @param x
     * @param y
     * @param range
     * @param number
     * @return
     */
    public List<Info> findInfoList(Long infoType, String title, double x, double y, double range, int number);
}
