package com.gdtopway.realtimedata.controller;


import com.gdtopway.realtimedata.domain.State;
import com.gdtopway.realtimedata.service.IRealTimeDataService;
import com.gdtopway.realtimedata.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequestMapping("/info")
@RestController
public class StateController extends  BaseController {
  @Autowired
    private StateService stateService;

    @RequestMapping("showState")
    public ModelAndView manage(){
        ModelAndView  mv = getBaseMV("/showinfo");
        return mv;
    }
    @RequestMapping(value="/realTimeLoadinfo")
    @ResponseBody
    @Transactional
    public List<State> info(){
        Iterator<State> stateIterator= stateService.findStateInfo();
        List<State> copy = new ArrayList<State>();
        while (stateIterator.hasNext()) {
            copy.add(stateIterator.next());
        }
        return copy;
    }

}

