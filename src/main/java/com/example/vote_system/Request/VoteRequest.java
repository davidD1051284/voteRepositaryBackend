package com.example.vote_system.Request;

import java.util.List;

public class VoteRequest {

	private String voteName;

	private List<String> options;

	public String getVoteName() {
		return voteName;
	}

	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

}
