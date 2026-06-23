package com.example.vote_system.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vote_system.Entity.VoteOption;
import com.example.vote_system.Entity.VoteRecord;

@Repository
public interface VoteRecordRepository extends JpaRepository<VoteRecord, Long> {
	void deleteAllByOption(VoteOption option);
}
