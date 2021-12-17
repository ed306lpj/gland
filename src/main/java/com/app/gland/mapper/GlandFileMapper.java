package com.app.gland.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.app.gland.entity.GlandFileEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 
 * @Title：GlandFileMapper
 * @Description: [类描述]
 * @author pjliu
 * @date 2021/12/10
 */
@Mapper
public interface GlandFileMapper extends BaseMapper<GlandFileEntity> {
	List<GlandFileEntity> queryListByCheckDate(String startDate,String endDate,String fileType);
}
