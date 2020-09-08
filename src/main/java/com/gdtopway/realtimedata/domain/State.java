package com.gdtopway.realtimedata.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mc_states")
//@SequenceGenerator(name = "instance_sequence_state", sequenceName = "seq_ecom_istme_state", allocationSize = 1)
public class State implements Serializable {

    @Id
    @GeneratedValue
            //(strategy = GenerationType.SEQUENCE, generator="instance_sequence_state")
    private Long id;
    private String originallyFilePath;//原始文件路径
    private String originallyFileState;//原始文件是否存在(原始地址中改名后文件)(1.存在 0.不存在)
    private String copyFilePath;//年月日下文件路径
    private String copyFileState;//判断复制到年月日下面的文件是否存在(1.存在 0.不存在)
    private String putInStorageState;//判断是否入库(1.存在 2.不存在)


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date stateTime;//规准时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    /*@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date actualTime;//实际时间

    public Date getActualTime() {
        return actualTime;
    }

    public void setActualTime(Date actualTime) {
        this.actualTime = actualTime;
    }

    public Date getStateTime() {
        return stateTime;
    }

    public void setStateTime(Date stateTime) {
        this.stateTime = stateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginallyFilePath() {
        return originallyFilePath;
    }

    public void setOriginallyFilePath(String originallyFilePath) {
        this.originallyFilePath = originallyFilePath;
    }

    public String getOriginallyFileState() {
        return originallyFileState;
    }

    public void setOriginallyFileState(String originallyFileState) {
        this.originallyFileState = originallyFileState;
    }

    public String getCopyFilePath() {
        return copyFilePath;
    }

    public void setCopyFilePath(String copyFilePath) {
        this.copyFilePath = copyFilePath;
    }

    public String getCopyFileState() {
        return copyFileState;
    }

    public void setCopyFileState(String copyFileState) {
        this.copyFileState = copyFileState;
    }

    public String getPutInStorageState() {
        return putInStorageState;
    }

    public void setPutInStorageState(String putInStorageState) {
        this.putInStorageState = putInStorageState;
    }

}
