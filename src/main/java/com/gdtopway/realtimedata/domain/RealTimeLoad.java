package com.gdtopway.realtimedata.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by rainbow on 2018/11/6.
 */


@Entity
@Table(name="mc_realtimeloads")
//@SequenceGenerator(name = "instance_sequence", sequenceName = "seq_ecom_istme", allocationSize = 1)
public class RealTimeLoad implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue
            //(strategy = GenerationType.SEQUENCE, generator="instance_sequence")
    private Long id;
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 记录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;

    /**
     * 记录值
     */
    private Double recordValue;
    /**
     * 部门编码
     */
    private String orgCode;
    /**
     * 指标编码
     */
    private String indicatorCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gaugeTime;//规准时间
    /*当日累积电量*/
    private Double powerValue;

    public Double getPowerValue() {
        return powerValue;
    }

    public void setPowerValue(Double powerValue) {
        this.powerValue = powerValue;
    }

    public Date getGaugeTime() {
        return gaugeTime;
    }

    public void setGaugeTime(Date gaugeTime) {
        this.gaugeTime = gaugeTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Double getRecordValue() {
        return recordValue;
    }

    public void setRecordValue(Double recordValue) {
        this.recordValue = recordValue;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getIndicatorCode() {
        return indicatorCode;
    }

    public void setIndicatorCode(String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }
}
