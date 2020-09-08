package com.gdtopway.realtimedata.util;

import com.gdtopway.realtimedata.domain.RealTimeLoad;
import com.gdtopway.realtimedata.domain.State;
import com.gdtopway.realtimedata.repository.RealTimeLoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
//将改名后文件规准时间与数据库规准时间比较，相同则删除改名后文件，不同则进行复制、入库
@Component
public class CompareTime {
    @Autowired
    private RealTimeLoadRepository realTimeLoadRepository;

    public boolean compare(File reNameFilePath,State state){
        //改名后，取规准时间与数据库比较
        boolean flag=false;
        Iterator<RealTimeLoad> nreal1 = realTimeLoadRepository.findAll().iterator();//拿出数据库已有的数据
        List<RealTimeLoad> list = new ArrayList<RealTimeLoad>();
        while (nreal1.hasNext()) {
            list.add(nreal1.next());
        }
        if(list.size()>0){
            for (RealTimeLoad r : list) {
                Date dates = r.getGaugeTime();
                Date  stateTime=state.getStateTime();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = formatter.format(dates);
                String string=formatter.format(stateTime);
                if (str.equals(string)) {//如果数据库存在相同规准时间，不进行复制及入库，删除该重命名文件
                    //File file = new File(reNameFilePath);
                    reNameFilePath.delete();
                    //与数据库有相同规准时间，则直接删除该改名文件返回以下状态
                    state.setCopyFilePath("已存在");
                    state.setCopyFileState("fail");
                    state.setPutInStorageState("fail");
                    flag = false;
                    break;
                }else{
                    flag = true;
                }
            }
        }else{
            flag=true;
        }
        return flag;
    }
}
