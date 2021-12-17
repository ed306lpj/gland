package com.app.gland.inteface;

import com.app.gland.entity.GlandCheckEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
public interface GlandCheckService extends IService<GlandCheckEntity>{
    List<GlandCheckEntity> queryCheckDataList(String start, String end);
    
    boolean insertEntity(GlandCheckEntity glandCheckEntity);
    
    boolean deleteById(Long id);
}
