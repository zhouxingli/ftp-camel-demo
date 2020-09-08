package com.gdtopway.realtimedata.config;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;
import org.springframework.stereotype.Component;

/**
 * Created by rainbow on 2018/11/6.
 */

@Component
public class TxtFileFilter implements GenericFileFilter {
    @Override
    public boolean accept(GenericFile file) {
        System.out.println("原文件:"+file.getAbsoluteFilePath());
        return file.getFileName().endsWith(".txt") || file.isDirectory();
    }

}
