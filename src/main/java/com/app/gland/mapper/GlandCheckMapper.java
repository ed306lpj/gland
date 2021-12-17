package com.app.gland.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.app.gland.entity.GlandCheckEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 设备Mapper
 *
 * @author: gangyang2
 * @create: 2021/3/15
 **/
@Mapper
public interface GlandCheckMapper extends BaseMapper<GlandCheckEntity> {
	
	
	List<GlandCheckEntity> queryListByCheckDate(String startDate,String endDate);

  
}
