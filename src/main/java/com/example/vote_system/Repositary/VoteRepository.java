package com.example.vote_system.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vote_system.Entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}