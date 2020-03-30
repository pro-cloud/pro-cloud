package com.cloud.oss.beans.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author Aijm
 * @Description  接收文件查询
 * @Date 2019/9/27
 */
@Data
public class SysFileDTO implements Serializable {

    @ApiModelProperty(value = "文件名称")
    private String fileName;


    @ApiModelProperty(value = "文件类型(对一个文件大致的分类 参考FileUtil)")
    private String type;


    @ApiModelProperty(value = "归属应用")
    private String belongName;

    @ApiModelProperty(value = "归属应用类别")
    private String belongType;


    @ApiModelProperty(value = "归属应用状态")
    private Integer belongStatus;

    @ApiModelProperty(value = "创建开始时间")
    protected LocalDateTime createBegDate;

    @ApiModelProperty(value = "创建结束时间")
    protected LocalDateTime createEndDate;


    /**
     * 开启状态
     */
    public static final Integer STATUS_ON = 0;

    /**
     * 禁用状态
     */
    public static final Integer STATUS_OFF = 1;
}
