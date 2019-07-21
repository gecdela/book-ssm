package com.mybatis.dao;

import com.mybatis.beans.Record;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecordMapper {
	public List<Record> getRecordsByPage(@Param("pageIndex") Integer pageIndex, @Param("size") Integer size);
	public int getLength();
	public int deleteRecordById(Integer recordId);

}
