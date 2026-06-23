package com.example.vote_system.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vote_record_option", indexes = { @Index(columnList = "vote_record_id"),
		@Index(columnList = "option_id") })
public class VoteRecordOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "vote_record_id", nullable = false)
	private VoteRecord voteRecord;

	@ManyToOne
	@JoinColumn(name = "option_id", nullable = false)
	private VoteOption option;

	private LocalDateTime voteTime;

	@PrePersist
	public void prePersist() {
		this.voteTime = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VoteRecord getVoteRecord() {
		return voteRecord;
	}

	public void setVoteRecord(VoteRecord voteRecord) {
		this.voteRecord = voteRecord;
	}

	public VoteOption getOption() {
		return option;
	}

	public void setOption(VoteOption option) {
		this.option = option;
	}

	public LocalDateTime getVoteTime() {
		return voteTime;
	}

	public void setVoteTime(LocalDateTime voteTime) {
		this.voteTime = voteTime;
	}

	public VoteRecordOption() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoteRecordOption(Long id, VoteRecord voteRecord, VoteOption option, LocalDateTime voteTime) {
		super();
		this.id = id;
		this.voteRecord = voteRecord;
		this.option = option;
		this.voteTime = voteTime;
	}

}