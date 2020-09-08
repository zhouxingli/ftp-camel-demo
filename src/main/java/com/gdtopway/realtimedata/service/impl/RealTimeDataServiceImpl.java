package com.gdtopway.realtimedata.service.impl;

import com.gdtopway.realtimedata.config.BaseConfig;
import com.gdtopway.realtimedata.controller.BaseController;
import com.gdtopway.realtimedata.domain.RealTimeLoad;
import com.gdtopway.realtimedata.domain.State;
import com.gdtopway.realtimedata.processor.DataProcessor;
import com.gdtopway.realtimedata.repository.RealTimeLoadRepository;
import com.gdtopway.realtimedata.repository.StateRepository;
import com.gdtopway.realtimedata.service.IRealTimeDataService;
import com.gdtopway.realtimedata.util.RealTimeDataOrgTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by rainbow on 2018/11/8.
 * 主要用于 实行入库操作
 */
@Service
public class RealTimeDataServiceImpl  implements IRealTimeDataService {
    @Autowired
    private RealTimeLoadRepository realTimeLoadRepository;
    @Override
    public List<RealTimeLoad> saveList(List<RealTimeLoad> entitys) {

        Calendar zeroCalendar = Calendar.getInstance();
        zeroCalendar.set(Calendar.HOUR_OF_DAY, 0);
        zeroCalendar.set(Calendar.MINUTE, 0);
        zeroCalendar.set(Calendar.SECOND, 0);
        zeroCalendar.set(Calendar.MILLISECOND, 0);
        Calendar yesterdayZeroCalendar = Calendar.getInstance();//前一天0点
       yesterdayZeroCalendar.add(Calendar.DATE,-1);
        yesterdayZeroCalendar.set(Calendar.HOUR_OF_DAY, 0);
        yesterdayZeroCalendar.set(Calendar.MINUTE,0);
        yesterdayZeroCalendar.set(Calendar.SECOND, 0);
        yesterdayZeroCalendar.set(Calendar.MILLISECOND, 0);
        Calendar yesterdayLastCalendar = Calendar.getInstance();//前一天23:59点
       yesterdayLastCalendar.add(Calendar.DATE,-1);
        yesterdayLastCalendar.set(Calendar.HOUR_OF_DAY, 23);
        yesterdayLastCalendar.set(Calendar.MINUTE,59);
        yesterdayLastCalendar.set(Calendar.SECOND, 59);
        yesterdayLastCalendar.set(Calendar.MILLISECOND, 59);
        //yesterdayZeroCalendar.set(Calendar.MILLISECOND, 59);
        for(RealTimeLoad entity : entitys){
            List<RealTimeLoad> preRecords = this.realTimeLoadRepository.findByOrgCodeAndGaugeTimeBefore(entity.getOrgCode(),zeroCalendar.getTime(),entity.getGaugeTime());
            if(preRecords!=null && preRecords.size()>0){
                if( preRecords.size()==1){//当天第二个文件，则令第一次的intervalPower为0 ，当天重新累加
                    RealTimeLoad preRecord = preRecords.get(0);
                    Double intervalNums = new Double(entity.getGaugeTime().getTime() - preRecord.getGaugeTime().getTime()) /3600000D;
                    Double intervalPower = intervalNums * (entity.getRecordValue() + preRecord.getRecordValue())*1/2 ;
                    entity.setPowerValue(0 +intervalPower );
                }else{
                    RealTimeLoad preRecord = preRecords.get(0);
                    Double intervalNums = new Double(entity.getGaugeTime().getTime() - preRecord.getGaugeTime().getTime()) /3600000D;
                    Double intervalPower = intervalNums * (entity.getRecordValue() + preRecord.getRecordValue())*1/2 ;
                    entity.setPowerValue((preRecord.getPowerValue() == null? 0:preRecord.getPowerValue()) +intervalPower );
                }

            }else{
                //如果是当天不存在值时,以此规准时间为电量起点。
                //TODO此处将会存在BUG(即如果当天第一个点不是以00:00开始,就会缺失从0点0分到当前时间的电量数据),但因缺数问题无法解决或说不用解决。
                /*entity.setPowerValue(new Double(0));*/
                //当preRecords=0，即为当天第一次拿到的文件规准时间。1.取出昨天最后一个规准时间的电量 2.用当天第一次累加 为昨天总电量
                List<RealTimeLoad> otherPreRecords = this.realTimeLoadRepository.findByOrgCodeAndGaugeTimeBefore(entity.getOrgCode(),yesterdayZeroCalendar.getTime(),yesterdayLastCalendar.getTime());
                if(otherPreRecords!=null && otherPreRecords.size()>0){
                    RealTimeLoad preRecord = otherPreRecords.get(0);//取出昨天最后一个规准时间的电量
                    Double intervalNums = new Double(entity.getGaugeTime().getTime() - preRecord.getGaugeTime().getTime()) /3600000D;
                    Double intervalPower = intervalNums * (entity.getRecordValue() + preRecord.getRecordValue())*1/2 ;
                    entity.setPowerValue((preRecord.getPowerValue() == null? 0:preRecord.getPowerValue()) +intervalPower );
                }else{
                    entity.setPowerValue(new Double(0));
                }
        }
        }
        Iterable<RealTimeLoad> real=realTimeLoadRepository.save(entitys);
        Iterator<RealTimeLoad>  nreal=real.iterator();
        List<RealTimeLoad> list = new ArrayList<RealTimeLoad>();
        while (nreal.hasNext()) {
            list.add(nreal.next());
        }
        return list;
    }
     }




