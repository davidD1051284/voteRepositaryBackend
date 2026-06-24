package com.example.vote_system.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vote_system.Entity.Vote;
import com.example.vote_system.Repositary.VoteProcedureRepository;
import com.example.vote_system.Request.VoteRequest;

@RestController
@RequestMapping("/api/admin/votes")
public class AdminVoteController {

	@Autowired
	private VoteProcedureRepository voteProcedureRepository;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody VoteRequest request) {

		Long voteId = voteProcedureRepository.createVote(request.getVoteName());

		for (String option : request.getOptions()) {

			voteProcedureRepository.createVoteOption(voteId, option);
		}

		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<List<Vote>> getAll() {

		return ResponseEntity.ok(voteProcedureRepository.getAllVotes());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {

		voteProcedureRepository.deleteVote(id);
		return ResponseEntity.ok().build();
	}
}