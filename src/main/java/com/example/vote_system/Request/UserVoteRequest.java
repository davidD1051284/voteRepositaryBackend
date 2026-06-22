package com.example.vote_system.Request;

import java.util.List;

public class UserVoteRequest {

	private Long userId;

	private List<Long> optionIds;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getOptionIds() {
		return optionIds;
	}

	public void setOptionIds(List<Long> optionIds) {
		this.optionIds = optionIds;
	}

}
