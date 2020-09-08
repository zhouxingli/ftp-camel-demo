package com.gdtopway.realtimedata.repository;

import com.gdtopway.realtimedata.domain.RealTimeLoad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;


/**
 * Created by rainbow on 2018/11/6.
 */
public interface RealTimeLoadRepository extends CrudRepository<RealTimeLoad,Long> {

    /*void update(RealTimeLoad entity);*/
    @Query("select o from RealTimeLoad o where o.orgCode = ?1 and o.gaugeTime >=?2  and o.gaugeTime < ?3 order by o.gaugeTime desc ")
    List<RealTimeLoad> findByOrgCodeAndGaugeTimeBefore(String orgCode, Date zeroPointTime, Date gaugeTime);
}
