package com.demo.micro.resource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.micro.resource.dao.ResourceDao;
import com.demo.micro.resource.entity.Info;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private ResourceDao resourceDao;
	
	@Override
    public List<Info> findInfoList(Long infoType, String title, double x, double y, double range, int number) {
        List<Info> infoList = resourceDao.selectRangeInfo(infoType, title, x, y, range, number);
        return infoList;
    }
}
