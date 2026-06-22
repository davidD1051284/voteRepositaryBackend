package com.example.vote_system.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vote_record")
public class VoteRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recordId;

	private Long userId;

	private LocalDateTime voteTime;

	@ManyToOne
	@JoinColumn(name = "vote_id")
	private Vote vote;

	@ManyToOne
	@JoinColumn(name = "option_id")
	private VoteOption option;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDateTime getVoteTime() {
		return voteTime;
	}

	public void setVoteTime(LocalDateTime voteTime) {
		this.voteTime = voteTime;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public VoteOption getOption() {
		return option;
	}

	public void setOption(VoteOption option) {
		this.option = option;
	}

	public VoteRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoteRecord(Long recordId, Long userId, LocalDateTime voteTime, Vote vote, VoteOption option) {
		super();
		this.recordId = recordId;
		this.userId = userId;
		this.voteTime = voteTime;
		this.vote = vote;
		this.option = option;
	}

}
