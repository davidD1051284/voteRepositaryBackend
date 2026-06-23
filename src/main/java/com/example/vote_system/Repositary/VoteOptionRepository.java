package com.example.vote_system.Repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vote_system.Entity.Vote;
import com.example.vote_system.Entity.VoteOption;

@Repository
public interface VoteOptionRepository extends JpaRepository<VoteOption, Long> {

	List<VoteOption> findByVote(Vote vote);
}
