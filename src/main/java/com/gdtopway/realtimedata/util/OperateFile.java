package com.gdtopway.realtimedata.util;


import com.gdtopway.realtimedata.domain.RealTimeLoad;
import com.gdtopway.realtimedata.domain.State;
import com.gdtopway.realtimedata.repository.RealTimeLoadRepository;
import com.gdtopway.realtimedata.service.IRealTimeDataService;
import com.gdtopway.realtimedata.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class OperateFile {
    @Autowired
    private StateService stateService;
    @Autowired
    private IRealTimeDataService iRealTimeDataService;
    @Autowired
    private CopyFile copyFile;
    @Autowired
    private ParseDataFromfile parseDataFromfile;
    @Autowired
    private CompareTime compareTime;

    public void doFile(File renamefile, State state) {

    //调用新重命名文件规准时间与数据库规准时间比较方法
        boolean flag = compareTime.compare(renamefile,state);
        if (flag) {//重命名文件规准时间在数据库不存在，说明改时间点的文件不存在，则进行一下操作
            //进行复制文件
            File newMovefile = copyFile.copyFileTo(renamefile,state);
            //解析文件
            List<RealTimeLoad> list = parseDataFromfile.parseRealTime(newMovefile,state);
            //保存文件（入库）
            List<RealTimeLoad> realList = iRealTimeDataService.saveList(list);
            if (realList.size() > 0) {
                state.setPutInStorageState("success");
            } else {
                state.setPutInStorageState("fail");
            }
            //状态入库
            stateService.saveState(state);
        } else {
            stateService.saveState(state);

        }
    }

}



