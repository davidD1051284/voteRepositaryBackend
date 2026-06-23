package com.example.vote_system.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vote_system.Entity.User;
import com.example.vote_system.Entity.Vote;
import com.example.vote_system.Entity.VoteOption;
import com.example.vote_system.Entity.VoteRecord;
import com.example.vote_system.Entity.VoteRecordOption;
import com.example.vote_system.Repositary.UserRepository;
import com.example.vote_system.Repositary.VoteOptionRepository;
import com.example.vote_system.Repositary.VoteRecordOptionRepository;
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

	@Autowired
	private VoteRecordOptionRepository voteRecordOptionRepository;

	@Autowired
	private UserRepository userRepositary;

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

		// 1️⃣ 查 vote
		Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new RuntimeException("Vote not found"));

		// 1️⃣ 防止重複投票（重要）
		boolean exists = recordRepository.existsByUser_IdAndVote_Id(request.getUserId(), voteId);

		if (exists) {
			throw new RuntimeException("你已經投過票了");
		}

		// 2️⃣ 建立一筆投票紀錄（ONLY ONCE）
		VoteRecord record = new VoteRecord();
		User user = userRepositary.findById(request.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		record.setUser(user);
		record.setVote(vote);

		recordRepository.save(record);

		// 3️⃣ 多選 option 存到 VoteRecordOption
		for (Long optionId : request.getOptionIds()) {

			VoteOption option = optionRepository.findById(optionId)
					.orElseThrow(() -> new RuntimeException("Option not found"));

			option.setTotalCount(Optional.ofNullable(option.getTotalCount()).orElse(0) + 1);

			VoteRecordOption recordOption = new VoteRecordOption();

			recordOption.setVoteRecord(record);
			recordOption.setOption(option);

			voteRecordOptionRepository.save(recordOption);
		}
	}

	@Transactional
	public void delete(Long voteId) {

		// 1️⃣ 找 vote
		Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new RuntimeException("Vote not found"));

		// 2️⃣ 找所有 voteRecord
		List<VoteRecord> records = recordRepository.findAllByVote_Id(voteId);

		for (VoteRecord record : records) {

			// 3️⃣ 刪 record_option
			voteRecordOptionRepository.deleteAllByVoteRecordRecordId(record.getRecordId());

			// 4️⃣ 刪 record
			recordRepository.delete(record);
		}

		// 5️⃣ 刪 vote_option
		List<VoteOption> options = optionRepository.findByVote(vote);

		optionRepository.deleteAll(options);

		// 6️⃣ 最後刪 vote
		voteRepository.delete(vote);
	}

	public Vote getVoteById(Long voteId) {
		return voteRepository.findById(voteId).orElseThrow(() -> new RuntimeException("Vote not found"));
	}
}
