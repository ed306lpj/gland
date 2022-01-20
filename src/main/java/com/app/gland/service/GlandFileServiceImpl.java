package com.app.gland.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.gland.entity.GlandFileEntity;
import com.app.gland.inteface.GlandFileService;
import com.app.gland.mapper.GlandFileMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Title：GlandFileServiceImpl
 * @Description: [类描述]
 * @author pjliu
 * @date 2021/12/10
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class GlandFileServiceImpl extends ServiceImpl<GlandFileMapper, GlandFileEntity> implements GlandFileService {
    @Autowired
    private GlandFileMapper glandFileMapper;

	@Override
	public List<GlandFileEntity> queryCheckDataList(String start, String end,String type) {
		return  glandFileMapper.queryListByCheckDate(start, end, type);
	}

	
   

    
}
