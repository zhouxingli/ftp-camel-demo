package com.gdtopway.realtimedata.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * Created by rainbow on 2017/1/3.
 * 消息资源辅助类
 */
@Component
public class LocaleMessageSourceUtil {
    /**
     * 注入MessageSource,目前配置指向validator-messages
     */
    @Resource
    private MessageSource messageSource;

    public String getMessage(String code) {
        return getMessage(code, null);
    }
    public String getMessage(String code, Object[] args){
        return getMessage(code, args, "");
    }
    public String getMessage(String code, Object[] args, String defaultMessage){
                //这里使用比较方便的方法，不依赖request.
               Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

}
