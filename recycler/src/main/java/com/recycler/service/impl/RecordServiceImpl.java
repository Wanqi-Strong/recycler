package com.recycler.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recycler.dao.RecordRepository;
import com.recycler.dao.UserRepository;
import com.recycler.entity.Record;
import com.recycler.entity.User;
import com.recycler.service.RecordService;
@Service
public class RecordServiceImpl implements RecordService {
	
	@Autowired
	RecordRepository recordRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public Record addRecord(Record record,String userId) {
		// get current user
		System.out.println("addRecord");
		System.out.println(userId);
		System.out.println(record);
		User user = userRepository.findById(userId).get();
		System.out.println("get user");
		if(user==null || user.getStatus()==0) {
			return null;
		}
		// create id
		String idString = UUID.randomUUID().toString().replaceAll("-", "");
		record.setRecordId(idString);
		record.setStatus(1);
		record.setUser(user);
		List<Record> list = new ArrayList<>();
		list.add(record);
		user.setRecords(list);
		//recordRepository.save(record);
		userRepository.save(user);
		return recordRepository.findById(idString).get();
	}

	@Override
	public Record updateRecord(Record record,String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record deleteRecord(Record record,String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> getAllRecords(String userId) {
		// TODO Auto-generated method stub
		return recordRepository.findAllByUserIdOrderByCreateTimeDesc(userId);
	}

}
