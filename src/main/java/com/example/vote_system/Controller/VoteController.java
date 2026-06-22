package com.example.vote_system.Controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vote_system.Entity.Vote;
import com.example.vote_system.Request.UserVoteRequest;
import com.example.vote_system.Service.VoteService;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

	private VoteService voteService;

	@GetMapping
	public List<Vote> getVotes() {

		return voteService.getAllVotes();
	}

	@PostMapping("/{voteId}/vote")
	public ResponseEntity<?> vote(@PathVariable Long voteId, @RequestBody UserVoteRequest request) {

		voteService.vote(voteId, request);

		return ResponseEntity.ok().build();
	}
}
