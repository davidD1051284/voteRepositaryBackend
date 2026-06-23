package com.example.vote_system.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vote")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "vote_name")
	private String voteName;

	@Column(name = "create_time")
	private LocalDateTime createTime;

	@OneToMany(mappedBy = "vote", fetch = FetchType.EAGER)
	private java.util.List<VoteOption> options;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public java.util.List<VoteOption> getOptions() {
		return options;
	}

	public void setOptions(java.util.List<VoteOption> options) {
		this.options = options;
	}

	public Vote() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vote(Long voteId, String voteName, LocalDateTime createTime, List<VoteOption> options) {
		super();
		this.id = voteId;
		this.voteName = voteName;
		this.createTime = createTime;
		this.options = options;
	}

}
