package com.example.vote_system.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vote_system.Entity.Vote;
import com.example.vote_system.Repositary.VoteProcedureRepository;
import com.example.vote_system.Request.UserVoteRequest;
import com.example.vote_system.Request.VoteRequest;

import jakarta.transaction.Transactional;

@Service
public class VoteService {


	@Autowired
	private VoteProcedureRepository voteProcedureRepository;

	public List<Vote> getAllVotes() {
		return voteProcedureRepository.getAllVotes();
	}

	@Transactional
	public void createVote(VoteRequest request) {

		Long voteId = voteProcedureRepository.createVote(request.getVoteName());

		for (String option : request.getOptions()) {

			voteProcedureRepository.createVoteOption(voteId, option);
		}
	}

	@Transactional
	public void vote(Long voteId, UserVoteRequest request) {

		if (voteProcedureRepository.hasVoted(request.getUserId(), voteId)) {

			throw new RuntimeException("你已經投過票了");
		}

		Long recordId = voteProcedureRepository.createVoteRecord(request.getUserId(), voteId, request.getUserName());

		for (Long optionId : request.getOptionIds()) {

			voteProcedureRepository.voteOption(recordId, optionId);
		}
	}

	@Transactional
	public void delete(Long voteId) {

		voteProcedureRepository.deleteVote(voteId);
	}

	public Vote getVoteById(Long voteId) {
		return voteProcedureRepository.getVoteById(voteId);
	}
}
