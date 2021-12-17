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
@Api("附件实体")
@Data
@TableName("gland_file")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlandFileEntity implements Serializable {
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
    private String name;

  
    @ApiModelProperty(value = "checkOpt1")
    private String path;

    @ApiModelProperty(value = "fileType")
    private String fileType;
   
    private static final long serialVersionUID = 1L;
}
