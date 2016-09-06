package com.mt.mapper;

import org.springframework.stereotype.Repository;

import com.mt.vo.SmsSendlogHis;

@Repository
public interface SmsSendlogHisDAO {
    int deleteByPrimaryKey(Long id);

    int insert(SmsSendlogHis record);

    int insertSelective(SmsSendlogHis record);

    SmsSendlogHis selectByPrimaryKey(Long id);

    int selectCountTodayByCriteria(String mobile);

    /**
     * update By SmsId Selective
     *
     * @Description: update By SmsId Selective
     * @param record
     * @return int
     * @author caike@xiaoniu66.com
     */
    int updateBySmsIdSelective(SmsSendlogHis record);

    int updateByPrimaryKeySelective(SmsSendlogHis record);

    int updateByPrimaryKey(SmsSendlogHis record);

}