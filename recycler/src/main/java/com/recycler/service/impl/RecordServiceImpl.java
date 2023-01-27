package com.recycler.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recycler.base.RecyclerException;
import com.recycler.dao.RecordRepository;
import com.recycler.dao.UserRepository;
import com.recycler.entity.Record;
import com.recycler.entity.RecordDetail;
import com.recycler.entity.User;
import com.recycler.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService {
	
	@Autowired
	RecordRepository recordRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public Record addRecord(Record record,String userId,List<RecordDetail> recordDetails) {
		// find user
		User user = userRepository.findById(userId).get();
		if(user==null || user.getStatus()==0) {
			throw new RecyclerException("user does not exist");
		}
		// build record
		String idString = UUID.randomUUID().toString().replaceAll("-", "");
		record.setRecordId(idString);
		record.setStatus(1);
		// bind details
		for(RecordDetail recordDetail : recordDetails) {
			recordDetail.setRecord(record);
		}
		// bind user
		record.setUser(user);
		// bind recordDetails
		record.setRecordDetails(recordDetails);
		recordRepository.save(record);
		return recordRepository.findById(idString).get();
	}

	@Override
	public Record updateRecord(Record record,String userId,List<RecordDetail> recordDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record deleteRecord(Record record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getAllRecords(String userId) {
		return recordRepository.findAllByUserIdOrderByCreateTimeDesc(userId);
	}

}
