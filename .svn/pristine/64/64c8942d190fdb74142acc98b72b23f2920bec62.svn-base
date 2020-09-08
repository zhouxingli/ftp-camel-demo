package com.gdtopway.realtimedata.config.tag;


import com.gdtopway.realtimedata.util.LocaleMessageSourceUtil;
import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by rainbow on 2016/12/30.
 * 基于form的验证 标签（即可以生成验证相关的JS代码）
 */
@Component
public class FormValidatorDirective  implements TemplateDirectiveModel {


    /**
     * 消息资源辅助类
     */
    @Autowired
    private LocaleMessageSourceUtil messageSourceUtil;



    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String name = params.get("name").toString();
        String bookName = messageSourceUtil.getMessage("book.name.notnull");
        String validateJS = "console.log('validateJS contents "+bookName+"');$('ss').on('click',function(){console.log('validateJS')})";
        env.setVariable("validateJS", getBeansWrapper().wrap(validateJS));
        body.render(env.getOut());
    }
    public static BeansWrapper getBeansWrapper(){
        BeansWrapper beansWrapper =
                new BeansWrapperBuilder(Configuration.VERSION_2_3_21).build();
        return beansWrapper;
    }



}
