/*
package com.gdtopway.realtimedata.util;


import com.gdtopway.realtimedata.domain.MaximumLoad;
import com.gdtopway.realtimedata.service.MaximumLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.ParseException;
import java.util.Date;

//年最大负荷--读取文件--入库
@Component

public class YearMaxLoad {
    @Autowired
    private MaximumLoadService maximumLoadService;
    public void  yearMaxFileLoad(File file) {
        String strLoad=null;
        Date date =new Date();
        if (file.isFile() && file.exists()) {
            try {
                InputStreamReader read = null;
                try {
                    read = new InputStreamReader(new FileInputStream(file), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                BufferedReader bufferedReader = new BufferedReader(read);//读取文件
                String lineTxt = null;
                String[] str=null;
                MaximumLoad maximumLoad=new MaximumLoad();
                if ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(lineTxt);
                    str=lineTxt.split(" ");//读取第一行数据，以空格分割成数组
                    strLoad=str[1];
                   Double dLoad= Double.valueOf(strLoad);
                    maximumLoad.setMaxLoad(dLoad);
                    maximumLoad.setYearTime(date);
                }
                maximumLoadService.saveMaximumLoad(maximumLoad);

            } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }





}
*/
