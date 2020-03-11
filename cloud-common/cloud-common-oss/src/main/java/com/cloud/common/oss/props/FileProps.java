package com.cloud.common.oss.props;

import com.cloud.common.oss.entity.FilePath;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Aijm
 * @Description oss 配置项
 * @Date 2019/8/19
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class FileProps {

    /**
     * 文件类型
     */
    private List<FilePath> filePaths;


}
