package com.cloud.oss.beans.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.common.entity.BaseEntity;
import com.cloud.common.entity.TenantEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 文件统一管理
 * 最终地址
 *      belongName/belongType/prePath/yyyyMMdd/HHmmss/上传文件名
 * @author Aijm
 * @date 2019-09-11 17:24:43
 */
@Data
@TableName("sys_file")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "文件统一管理")
public class SysFile extends TenantEntity<SysFile> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件外网访问路径")
    private String fileUrl;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "文件类型(对一个文件大致的分类 参考FileUtil)")
    private Integer type;

    @ApiModelProperty(value = "文件后缀")
    private String fileSuffix;

    @ApiModelProperty(value = "文件大小")
    private Long fileSize;
    @ApiModelProperty(value = "文件属性:比如图片长*宽")
    private String props;

    @ApiModelProperty(value = "归属应用")
    private String belongName;

    @ApiModelProperty(value = "归属应用类别")
    private String belongType;

    @ApiModelProperty(value = "文件地址前缀")
    private String prePath;

    @ApiModelProperty(value = "归属应用状态")
    private Integer belongStatus;



}
