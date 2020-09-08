package com.gdtopway.realtimedata.controller;

import com.gdtopway.realtimedata.config.BaseConfig;
import com.gdtopway.realtimedata.domain.RealTimeLoad;
import com.gdtopway.realtimedata.repository.RealTimeLoadRepository;
import com.gdtopway.realtimedata.util.RealTimeDataOrgTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Random;
/**
 * Created by rainbow on 2018/11/6.
 */
@RequestMapping("/")
@RestController
public class RealtimeDataController extends BaseController{

    @Autowired
    private RealTimeLoadRepository realTimeLoadRepository;
    @Autowired
    private BaseConfig baseConfig;

    @RequestMapping(value="info")
    @ResponseBody
    @Transactional
    public RealTimeLoad info(){

        RealTimeLoad entity = new RealTimeLoad();

        entity.setNodeName("FS-ALLP");
        entity.setOrgCode(RealTimeDataOrgTag.tag_org.get("FS-ALLP"));
        entity.setIndicatorCode(baseConfig.getLoadCode());
        entity.setRecordTime(new Date());
        entity.setRecordValue(new Double(Math.random()*100)+new Double(Math.random()*10)+new Double(Math.random()));

        this.realTimeLoadRepository.save(entity);
        return entity;
    }
    @RequestMapping("index")
    public ModelAndView manage(){
        ModelAndView  mv = new ModelAndView("index");

        return mv;
    }

    @RequestMapping("test")
    public ModelAndView test(){
        ModelAndView  mv = getBaseMV("/test");
        return mv;
    }




}
