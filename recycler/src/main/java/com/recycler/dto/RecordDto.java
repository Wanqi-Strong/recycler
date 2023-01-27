package com.recycler.dto;

import java.util.List;

import com.recycler.entity.Record;
import com.recycler.entity.RecordDetail;
/*
 * parameter structure for record service
 * */
public class RecordDto {
	
	private Record record;
	
	private String userId;
	
	private List<RecordDetail> recordDetails;
	
	public Record getRecord() {
		return record;
	}
	
	public void setRecord(Record record) {
		this.record = record;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public List<RecordDetail> getRecordDetails() {
		return recordDetails;
	}
	
	public void setRecordDetails(List<RecordDetail> recordDetails) {
		this.recordDetails = recordDetails;
	}
	
	public RecordDto() {
	}
}
