package com.example.vote_system.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vote")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long voteId;

	@Column(name = "vote_name")
	private String voteName;

	@Column(name = "create_time")
	private LocalDateTime createTime;

	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public String getVoteName() {
		return voteName;
	}

	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Vote() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vote(Long voteId, String voteName, LocalDateTime createTime) {
		super();
		this.voteId = voteId;
		this.voteName = voteName;
		this.createTime = createTime;
	}

}
