package com.recycler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recycler.base.Result;
import com.recycler.dto.RecordDto;
import com.recycler.service.RecordService;


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
}
