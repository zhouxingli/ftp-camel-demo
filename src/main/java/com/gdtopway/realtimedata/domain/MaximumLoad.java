/*
package com.gdtopway.realtimedata.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

//年最大负荷表
@Entity
@Table(name = "mc_maximumLoad")
public class MaximumLoad implements Serializable {
    */
/**
     * 主键
     *//*

    @Id
    @GeneratedValue
    private Long id;

    */
/**
     * 记录时间
     *//*

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date yearTime;

    */
/**
     * 最大负荷
     *//*

    private Double maxLoad;


    public Double getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(Double maxLoad) {
        this.maxLoad = maxLoad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getYearTime() {
        return yearTime;
    }

    public void setYearTime(Date yearTime) {
        this.yearTime = yearTime;
    }
}
*/
