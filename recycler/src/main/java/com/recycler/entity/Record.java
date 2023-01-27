package com.recycler.entity;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="record")
public class Record {
	@Id
	private String recordId;
	
	private int status;
	
	private Timestamp createTime;
	
	@NotBlank
	@Size(min = 2,max = 45)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	@JsonBackReference
	@NotEmpty
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "record")
	@NotEmpty
	private List<RecordDetail> recordDetails;
	
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

	public List<RecordDetail> getRecordDetails() {
		return recordDetails;
	}

	public void setRecordDetails(List<RecordDetail> recordDetails) {
		this.recordDetails = recordDetails;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Record(String id, int status, String description, String userId) {
		super();
		this.recordId = id;
		this.status = status;
		this.description = description;
	}

	public Record(String recordId, int status, String description, List<RecordDetail> recordDetails) {
		super();
		this.recordId = recordId;
		this.status = status;
		this.description = description;
		this.recordDetails = recordDetails;
	}

	@Override
	public String toString() {
		return "Record [recordId=" + recordId + ", status=" + status + ", description=" + description + ", user=" + user
				+ ", recordDetails=" + recordDetails + "]";
	}
	
}
