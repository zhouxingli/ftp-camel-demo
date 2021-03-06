package com.gdtopway.realtimedata.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by rainbow on 16/8/20.
 * 与生成相关的平台控制
 */
@Component
@ConfigurationProperties(prefix ="sftpDemon")
public class FtpDemonConfig {


    /**
     * 本平台默认风格名称
     */
    private String defaultThemes;
    /**
     * 生成时使用的风格名称
     */
    private String generateDefaultThemes;
    /**
     * 静态资源目录
     */
    private String staticRescorceDir;

    public String getStaticRescorceDir() {
        return staticRescorceDir;
    }

    public void setStaticRescorceDir(String staticRescorceDir) {
        this.staticRescorceDir = staticRescorceDir;
    }

    public String getGenerateDefaultThemes() {
        return generateDefaultThemes;
    }

    public void setGenerateDefaultThemes(String generateDefaultThemes) {
        this.generateDefaultThemes = generateDefaultThemes;
    }

    public String getDefaultThemes() {
        return defaultThemes;
    }

    public void setDefaultThemes(String defaultThemes) {
        this.defaultThemes = defaultThemes;
    }
}
