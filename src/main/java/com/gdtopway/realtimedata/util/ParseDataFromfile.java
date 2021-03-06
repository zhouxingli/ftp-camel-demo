package com.gdtopway.realtimedata.util;

import com.gdtopway.realtimedata.config.BaseConfig;
import com.gdtopway.realtimedata.domain.RealTimeLoad;
import com.gdtopway.realtimedata.domain.State;
import com.gdtopway.realtimedata.util.RealTimeDataOrgTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

//解析信息文件 返回信息集合
@Component
public class ParseDataFromfile {
    @Autowired
    protected BaseConfig baseConfig;
    public List<RealTimeLoad> parseRealTime(File newMovefile,State state) {
          Date stateTime=state.getStateTime();//规准时间
        Date actualTime=state.getActualTime();//文件实际发布时间
        String indicatorCode = baseConfig.getLoadCode();//指标编码
        File file = newMovefile;
        List<RealTimeLoad> list = new ArrayList<RealTimeLoad>();
        if (file.isFile() && file.exists()) {
            //考虑到编码格式
            InputStreamReader read = null;
            try {
                read = new InputStreamReader(new FileInputStream(file),"utf-8");
            }catch (UnsupportedEncodingException e1) {
               e1.printStackTrace();
           } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            //记录读取的数据文件的行数
            int count = 0;
            try {
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    count++;
                    String[] rowFields = lineTxt.split(" ");//空格分出
                    if (count >= 2) {
                        String keyString = rowFields[0];
                        String[] keyFields = keyString.split("\\.");
                        String stationTag = keyFields[0];
                        if (stationTag.equals("SysStation") ) {
                            String orgTag = keyFields[1];
                            String valueString =  rowFields[1];
                           if( RealTimeDataOrgTag.tag_org.containsKey(orgTag)){
                               RealTimeLoad realTimeLoad = new RealTimeLoad();
                               realTimeLoad.setNodeName(orgTag==null?"":orgTag);
                               if(valueString != null){
                                   realTimeLoad.setRecordValue(Double.parseDouble(valueString));
                               }
                               realTimeLoad.setRecordTime(actualTime);
                               realTimeLoad.setGaugeTime(stateTime);
                               realTimeLoad.setIndicatorCode(indicatorCode);
                               realTimeLoad.setOrgCode(RealTimeDataOrgTag.tag_org.get(orgTag));
                               //将读取的每一行的记录存入到list集合中
                               list.add(realTimeLoad);
                           }

                        }
                    }

                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                read.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            System.out.println("找不到指定的文件");
        }

        return list;
    }
}
