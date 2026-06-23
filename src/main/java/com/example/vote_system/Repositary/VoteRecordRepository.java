package com.example.vote_system.Repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vote_system.Entity.VoteOption;
import com.example.vote_system.Entity.VoteRecord;

@Repository
public interface VoteRecordRepository extends JpaRepository<VoteRecord, Long> {

    List<VoteRecord> findAllByVote_Id(Long voteId);

    void deleteAllByVote_Id(Long voteId);

    boolean existsByUser_IdAndVote_Id(Long userId, Long voteId);
}