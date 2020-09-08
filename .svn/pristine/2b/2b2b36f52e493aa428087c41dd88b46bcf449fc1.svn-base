package com.gdtopway.realtimedata.config;


import com.gdtopway.realtimedata.config.tag.FormValidatorDirective;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by rainbow on 2016/12/30.
 * freeMarker的附加配置,这里,主要是注入几个自定义标签
 */
@Configuration
public class FreeMarkerConfig {

    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    protected org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver resolver;
    @Autowired
    private FormValidatorDirective formValidatorDirective;

    @PostConstruct
    public void  setSharedVariable(){
        configuration.setDateFormat("yyyy/MM/dd");
        configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        configuration.setSharedVariable("tag_validateForm",formValidatorDirective);
        try {
            configuration.setSetting("template_update_delay", "1");
            configuration.setSetting("default_encoding", "UTF-8");
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        resolver.setSuffix(".ftl"); //解析后缀为html的
        resolver.setCache(false); //是否缓存模板
        resolver.setRequestContextAttribute("request"); //为模板调用时，调用request对象的变量名</span>
        resolver.setOrder(1);//次序以0开始
    }

}
