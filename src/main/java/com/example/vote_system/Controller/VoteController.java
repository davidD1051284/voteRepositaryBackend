package com.example.vote_system.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vote_system.Entity.Vote;
import com.example.vote_system.Repositary.VoteProcedureRepository;
import com.example.vote_system.Request.UserVoteRequest;
import com.example.vote_system.Service.VoteService;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

	@Autowired
	private VoteService voteService;

	@Autowired
	private VoteProcedureRepository voteProcedureRepository;

	@GetMapping
	public ResponseEntity<List<Vote>> getVotes() {
		return ResponseEntity.ok(voteService.getAllVotes());
	}

	@GetMapping("/{voteId}")
	public ResponseEntity<Vote> getVote(@PathVariable("voteId") Long voteId) {
		return ResponseEntity.ok(voteService.getVoteById(voteId));
	}

	@PostMapping("/{voteId}/vote")
	public ResponseEntity<?> vote(@PathVariable Long voteId, @RequestBody UserVoteRequest request) {

		voteService.vote(voteId, request);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{voteId}/has-voted")
	public ResponseEntity<Boolean> hasVoted(@PathVariable("voteId") Long voteId, @RequestParam("userId") Long userId) {

		return ResponseEntity.ok(voteProcedureRepository.hasVoted(userId, voteId));
	}

}
