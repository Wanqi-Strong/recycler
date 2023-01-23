package com.recycler.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.recycler.entity.Record;

public interface RecordRepository extends CrudRepository<Record, String> {
	List<Record> findAllByUserIdOrderByCreateTimeDesc(String userId);
}
