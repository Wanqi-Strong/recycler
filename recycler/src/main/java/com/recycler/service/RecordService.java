package com.recycler.service;

import java.util.List;

import com.recycler.entity.Record;
import com.recycler.entity.RecordDetail;

public interface RecordService {
	
	public Record addRecord(Record record,String userId,List<RecordDetail> recordDetails);
	
	public Record updateRecord(Record record,List<RecordDetail> recordDetails);
	
	public Record deleteRecord(String recordId);
	
	public List<Record> getAllRecords(String userId);
}
