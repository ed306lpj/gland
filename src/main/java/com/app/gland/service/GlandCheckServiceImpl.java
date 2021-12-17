package com.app.gland.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.gland.entity.GlandCheckEntity;
import com.app.gland.inteface.GlandCheckService;
import com.app.gland.mapper.GlandCheckMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zqwang18
 * @Description 类描述
 * @create 2021-03-21 22:43:35
 */

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class GlandCheckServiceImpl extends ServiceImpl<GlandCheckMapper, GlandCheckEntity> implements GlandCheckService {
    @Autowired
    private GlandCheckMapper glandCheckMapper;

	@Override
	public List<GlandCheckEntity> queryCheckDataList(String startDate, String endDate) {
		return glandCheckMapper.queryListByCheckDate(startDate, endDate);
	}

	@Override
	public boolean insertEntity(GlandCheckEntity glandCheckEntity) {
		Integer count = glandCheckMapper.insert(glandCheckEntity);
		
		return count>0?true:false;
	}

	@Override
	public boolean deleteById(Long id) {
		Integer count = glandCheckMapper.deleteById(id);
		return count>0?true:false;
	}
   

    
}
