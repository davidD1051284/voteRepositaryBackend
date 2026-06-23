package com.example.vote_system.Repositary;

import com.example.vote_system.Entity.VoteRecordOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRecordOptionRepository extends JpaRepository<VoteRecordOption, Long> {

	// 🔥 查某一筆投票（vote_record）選了哪些 option
	long countByOptionOptionId(Long optionId);

	// 🔥 查某個 option 被投幾次（統計用）
	boolean existsByVoteRecordRecordIdAndOptionOptionId(Long voteRecordId, Long optionId);

	// 🔥 刪除某個投票紀錄的所有選項
	List<VoteRecordOption> findAllByVoteRecordRecordId(Long voteRecordId);

	// 🔥 檢查是否已經選過某個 option（防重複）
	void deleteAllByVoteRecordRecordId(Long voteRecordId);
}