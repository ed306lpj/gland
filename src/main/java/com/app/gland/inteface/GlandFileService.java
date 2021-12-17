package com.app.gland.inteface;

import java.util.List;

import com.app.gland.entity.GlandFileEntity;
import com.baomidou.mybatisplus.extension.service.IService;
public interface GlandFileService extends IService<GlandFileEntity>{
    List<GlandFileEntity> queryCheckDataList(String start, String end,String type);
}
