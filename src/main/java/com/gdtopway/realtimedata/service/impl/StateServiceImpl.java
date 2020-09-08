package com.gdtopway.realtimedata.service.impl;

import com.gdtopway.realtimedata.domain.RealTimeLoad;
import com.gdtopway.realtimedata.domain.State;
import com.gdtopway.realtimedata.repository.StateRepository;
import com.gdtopway.realtimedata.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
//获取状态表所有信息 展示到页面
@Service
public class StateServiceImpl  implements StateService {
    @Autowired
    private StateRepository stateRepository;
    @Override
    public Iterator<State> findStateInfo() {
        return  stateRepository.findAll().iterator();
    }

    @Override
    public State saveState(State state) {
        return stateRepository.save(state);
    }
}
