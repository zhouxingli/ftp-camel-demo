package com.gdtopway.realtimedata.controller;

import com.gdtopway.realtimedata.config.BaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

/**
 * Created by rainbow on 16/8/20.
 */

public class BaseController {

    @Autowired
    protected BaseConfig baseConfig;


    public ModelAndView getBaseMV(String viewPath){

        ModelAndView mv = new ModelAndView("themes/"+ baseConfig.getDefaultThemes()+viewPath);
        mv.addObject("staticRescorceDir", baseConfig.getStaticRescorceDir());
        mv.addObject("themesName", baseConfig.getDefaultThemes());
        return mv;
    }

    public ModelAndView getBaseMV(String viewPath, Map<String, Object> model){

        ModelAndView mv = new ModelAndView("themes/"+ baseConfig.getDefaultThemes()+viewPath);
        model.put("staticRescorceDir", baseConfig.getStaticRescorceDir());
        model.put("themesName", baseConfig.getDefaultThemes());
        mv.addAllObjects(model);
        return mv;
    }

    @RequestMapping("error")
    public ModelAndView test(HttpServletRequest request) {

        ModelAndView mv = getBaseMV("/testmodel");
        String message = "error page";
        mv.addObject("message" ,message);

        return mv;
    }

    @Autowired
    private Validator validator;

    public List<Map<String, String>> getValidatorMessage(Object o, Class<?>... groups) {
        List<Map<String, String>> messageList = new ArrayList<Map<String, String>>();
        Set<ConstraintViolation<Object>> set = validator.validate(o, groups);
        for (ConstraintViolation<Object> constraintViolation : set) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", constraintViolation.getPropertyPath()+"");
            map.put("value", constraintViolation.getMessage());
            messageList.add(map);
        }
        return messageList;
    }

}