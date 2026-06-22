package com.example.vote_system.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vote_system.Entity.Vote;
import com.example.vote_system.Entity.VoteOption;
import com.example.vote_system.Entity.VoteRecord;
import com.example.vote_system.Repositary.VoteOptionRepository;
import com.example.vote_system.Repositary.VoteRecordRepository;
import com.example.vote_system.Repositary.VoteRepository;
import com.example.vote_system.Request.UserVoteRequest;
import com.example.vote_system.Request.VoteRequest;

import jakarta.transaction.Transactional;

@Service
public class VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private VoteOptionRepository optionRepository;

	@Autowired
	private VoteRecordRepository recordRepository;

	public List<Vote> getAllVotes() {
		return voteRepository.findAll();
	}

	@Transactional
	public Vote createVote(VoteRequest request) {

		Vote vote = new Vote();
		vote.setVoteName(request.getVoteName());
		vote.setCreateTime(LocalDateTime.now());

		voteRepository.save(vote);

		for (String optionName : request.getOptions()) {

			VoteOption option = new VoteOption();

			option.setOptionName(optionName);

			option.setVote(vote);

			optionRepository.save(option);
		}

		return vote;
	}

	@Transactional
	public void vote(Long voteId, UserVoteRequest request) {

		Vote vote = voteRepository.findById(voteId).orElseThrow();

		for (Long optionId : request.getOptionIds()) {

			VoteOption option = optionRepository.findById(optionId).orElseThrow();

			VoteRecord record = new VoteRecord();

			record.setUserId(request.getUserId());

			record.setVote(vote);

			record.setOption(option);

			record.setVoteTime(LocalDateTime.now());

			recordRepository.save(record);

			option.setTotalCount(option.getTotalCount() + 1);

			optionRepository.save(option);
		}
	}
}
