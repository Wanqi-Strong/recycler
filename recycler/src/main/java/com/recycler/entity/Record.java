package com.recycler.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="record")
public class Record {
	@Id
	private String recordId;
	
	private int status;
	
	private Timestamp createTime;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	@JsonBackReference
	private User user;
	
	public Record () {
		
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String id) {
		this.recordId = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreatedTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Record(String id, int status, String description, String userId) {
		super();
		this.recordId = id;
		this.status = status;
		this.description = description;
		//this.userId = userId;
	}

	@Override
	public String toString() {
		return "Record [id=" + recordId + ", status=" + status + ", createTime=" + createTime + ", description="
				+ description + "]";
	}
}
