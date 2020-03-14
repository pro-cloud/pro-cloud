package com.cloud.common.oss.props;

import com.cloud.common.oss.entity.FilePath;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author Aijm
 * @Description oss 配置项
 * @Date 2019/8/19
 */
@Data
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "aliyun.oss-file")
public class FileProps {

    /**
     * 文件类型
     */
    private List<FilePath> filePaths;


}
