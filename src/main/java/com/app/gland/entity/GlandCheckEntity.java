package com.app.gland.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 设备实体
 *
 * @author: gangyang2
 * @create: 2021/3/12
 **/
@Api("检查实体")
@Data
@TableName("gland_check")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlandCheckEntity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "id")
    private Long id;

   
    @ApiModelProperty(value = "检查日期")
    private String checkDate;

  
    @ApiModelProperty(value = "药量")
    private double dosage;

  
    @ApiModelProperty(value = "checkOpt1")
    private double checkOpt1;

    @ApiModelProperty(value = "checkOpt2")
    private double checkOpt2;
    
    @ApiModelProperty(value = "checkOpt3")
    private double checkOpt3;
    
    @ApiModelProperty(value = "checkOpt4")
    private double checkOpt4;
    
    @ApiModelProperty(value = "checkOpt5")
    private double checkOpt5;
    
    @ApiModelProperty(value = "checkOpt6")
    private double checkOpt6;
    
    @ApiModelProperty(value = "checkOpt7")
    private double checkOpt7;

    @ApiModelProperty(value = "checkOpt7")
    private double checkOpt8;

    private static final long serialVersionUID = 1L;
}
