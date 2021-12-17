package com.app.gland.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.gland.entity.GlandCheckEntity;
import com.app.gland.inteface.GlandCheckService;
import com.app.gland.unit.GlobalResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 通用接口层
 *
 * @author: ztan
 * @create: 2021/3/25
 **/
@RestController
@Slf4j
public class CommonRest {

 
	@Autowired
	private GlandCheckService glandCheckService;
	
	
	
    @GetMapping("/index")
    public GlobalResponse getData(@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<GlandCheckEntity> list =  glandCheckService.queryCheckDataList(startDate, endDate);
        List<String> checkDates = list.stream().map(GlandCheckEntity::getCheckDate).collect(Collectors.toList());
        List<Double> dosages = list.stream().map(GlandCheckEntity::getDosage).collect(Collectors.toList());
        List<Double> checkOpt1 = list.stream().map(GlandCheckEntity::getCheckOpt1).collect(Collectors.toList());
        List<Double> checkOpt2 = list.stream().map(GlandCheckEntity::getCheckOpt2).collect(Collectors.toList());
        List<Double> checkOpt3 = list.stream().map(GlandCheckEntity::getCheckOpt3).collect(Collectors.toList());
        List<Double> checkOpt4 = list.stream().map(GlandCheckEntity::getCheckOpt4).collect(Collectors.toList());
        List<Double> checkOpt5 = list.stream().map(GlandCheckEntity::getCheckOpt5).collect(Collectors.toList());
        List<Double> checkOpt6 = list.stream().map(GlandCheckEntity::getCheckOpt6).collect(Collectors.toList());
        List<Double> checkOpt7 = list.stream().map(GlandCheckEntity::getCheckOpt7).collect(Collectors.toList());
        map.put("checkDates", checkDates);
        map.put("dosages", dosages);
        map.put("checkOpt1", checkOpt1);
        map.put("checkOpt2", checkOpt2);
        map.put("checkOpt3", checkOpt3);
        map.put("checkOpt4", checkOpt4);
        map.put("checkOpt5", checkOpt5);
        map.put("checkOpt6", checkOpt6);
        map.put("checkOpt7", checkOpt7);
        map.put("checkOpt8", checkOpt7);
        return GlobalResponse.success(map);
    }

  //  public GlobalResponse insert(@RequestBody @Valid GlandCheckEntity glandCheckEntity) {
    @PostMapping("/add")
    public GlobalResponse insert(@RequestBody GlandCheckEntity glandCheckEntity) {
    	boolean flag = glandCheckService.insertEntity(glandCheckEntity);
        return GlobalResponse.success(flag);
    }
    
    @GetMapping("/delete")
    public GlobalResponse delete(@RequestParam(name = "id") Long id) {
    	boolean flag = glandCheckService.deleteById(id);
        return GlobalResponse.success(flag);
    }
    
}
