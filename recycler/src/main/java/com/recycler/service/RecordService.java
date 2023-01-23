package com.recycler.service;

import java.util.List;

import com.recycler.entity.Record;

public interface RecordService {
	
	public Record addRecord(Record record,String userId);
	
	public Record updateRecord(Record record,String userId);
	
	public Record deleteRecord(Record record,String userId);
	
	public List<Record> getAllRecords(String userId);
}
