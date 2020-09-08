package com.gdtopway.realtimedata.util;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.gdtopway.realtimedata.config.BaseConfig;
import com.gdtopway.realtimedata.domain.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class CopyFile {
//复制文件到年月日文件夹下
@Autowired
    protected BaseConfig baseConfig;
    @Value("${sftp.local-dir}")
    private String getNewFilePaths;
    public File copyFileTo(File reNameFile,State state) {
        //2、判断年月日,文件夹下,是否存在该快照(精确到分钟)文件。如果不存在,则移动快照至相应的文件夹,年、月、日,在日文件夹下,以时间命名。
        String newfilePath = getNewFilePaths;//年月日下文件指定位置
        InputStream input=null;
        FileOutputStream output=null;
        String  reNameFileName=reNameFile.getName();//根据重命名文件得到文件名2019-01-11 13-46.txt
        String reNameFilePath= reNameFile.getPath();//根据重命名文件得到文件路径
        String[] s1= reNameFileName.split(" ");//将新命名文件按空格分开
        String strFileName=s1[0].replace("-","/");//将2019-01-11改为2019/01/11
        File moveNewFiles=new File( newfilePath+File.separator+strFileName);//创建年月日的文件夹
        if(!moveNewFiles.exists()){//对应年月日文件夹不存在，则建立
            moveNewFiles.mkdirs();
            System.out.println("create success!!！");
        }
        System.out.println("file existed...");

        //将修改名字后的文件复制到对应年月日文件夹中
        String newMovePath = moveNewFiles.getPath()+ File.separator +reNameFileName;
        File newMovefile=new File(newMovePath);
        try {
            if(!newMovefile.exists()){//不存在就复制到该文件夹目录下
                input = new FileInputStream(reNameFilePath);//绝对原文件路径
                output = new FileOutputStream(newMovePath);//要拷贝到的文件路径
                FileCopyUtils.copy(input, output);//将改名文件复制到对应年月日文件下*****
                state.setCopyFileState("success");
                String originallyFileState=state.getOriginallyFileState();
                if ("success".equals(originallyFileState)) {//改名成功和复制成功，把改名后文件删除
                    reNameFile.delete();
                }
            }else if(newMovefile.exists()){//如果已经存在 ，则删除该文件
                reNameFile.delete();
                state.setCopyFileState("fail");
                System.out.println("文件已存在，将重命名文件删除");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            state.setCopyFileState("fail");
            System.out.println("文件复制失败！");

        }catch (IOException e) {
            e.printStackTrace();
        }
        state.setCopyFilePath(newMovePath);
        return newMovefile;

    }



}
