package com.example.vote_system.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vote_option")
public class VoteOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long optionId;

	@Column(name = "option_name")
	private String optionName;

	@Column(name = "total_count")
	private Integer totalCount = 0;

	@ManyToOne
	@JoinColumn(name = "vote_id")
	private Vote vote;

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public VoteOption() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoteOption(Long optionId, String optionName, Integer totalCount, Vote vote) {
		super();
		this.optionId = optionId;
		this.optionName = optionName;
		this.totalCount = totalCount;
		this.vote = vote;
	}

}
