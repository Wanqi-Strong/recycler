package com.recycler.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "record_details")
public class RecordDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int detailId;
	
	@NotBlank
	private String detailName;
	
	@Min(1)
	@Max(99)
	private int detailQty;
	
	private Timestamp createTime;
	
	@ManyToOne
	@JoinColumn(name = "record_id",referencedColumnName = "recordId")
	@JsonBackReference
	@NotEmpty
	private Record record;
	
	public RecordDetail() {
		
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public int getDetailQty() {
		return detailQty;
	}

	public void setDetailQty(int detailQty) {
		this.detailQty = detailQty;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public RecordDetail(String detailName, int detailQty, Record record) {
		super();
		this.detailName = detailName;
		this.detailQty = detailQty;
		this.record = record;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	@Override
	public String toString() {
		return "RecordDetail [detailId=" + detailId + ", detailName=" + detailName
				+ ", detailQty=" + detailQty + ", createTime=" + createTime + "]";
	}
	
}
