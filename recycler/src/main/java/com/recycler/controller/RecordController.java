package com.recycler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recycler.base.Result;
import com.recycler.dto.RecordDto;
import com.recycler.entity.Record;
import com.recycler.service.RecordService;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping("record")
public class RecordController {
	@Autowired
	RecordService recordService;
	
	
	// raw-JSON
	@PostMapping("/add")
	public Result addRecord(@RequestBody RecordDto r) {
		return  Result.success(recordService.addRecord(r.getRecord(),r.getUserId(),r.getRecordDetails()));
	}
	
	// form-data
	@PostMapping("/all")
	public Result getAllRecord(@RequestParam(name="userId") String userId) {
		return  Result.success(recordService.getAllRecords(userId));
	}
	
	// raw-JSON
	@PostMapping("/update")
	public Result updateRecord(@RequestBody RecordDto r) {
		return Result.success(recordService.updateRecord(r.getRecord(), r.getRecordDetails()));
	}
	
	// form-data
	@PostMapping("/delete")
	public Result deleteRecord(@RequestParam(name="recordId") String recordId) {
		System.out.println(recordId);
		return Result.success(recordService.deleteRecord(recordId));
	}
}
