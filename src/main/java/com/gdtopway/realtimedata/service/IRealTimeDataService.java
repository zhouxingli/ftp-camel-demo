package com.gdtopway.realtimedata.service;

import com.gdtopway.realtimedata.domain.RealTimeLoad;
import com.gdtopway.realtimedata.domain.State;

import java.util.List;

/**
 * Created by rainbow on 2018/11/8.
 */
public interface IRealTimeDataService {

    public List<RealTimeLoad> saveList(List<RealTimeLoad> entity);

}
