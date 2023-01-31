package com.recycler.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recycler.entity.Record;

@Repository
public interface RecordRepository extends CrudRepository<Record, String> {
	List<Record> findAllByUserIdOrderByCreateTimeDesc(String userId);
}
