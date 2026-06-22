package com.example.vote_system.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vote_system.Entity.Vote;
import com.example.vote_system.Request.VoteRequest;
import com.example.vote_system.Service.VoteService;

@RestController
@RequestMapping("/api/admin/votes")
public class AdminVoteController {

    private VoteService voteService;

    @PostMapping
    public Vote create(
            @RequestBody VoteRequest request) {

        return voteService.createVote(request);
    }

    @GetMapping
    public List<Vote> getAll() {

        return voteService.getAllVotes();
    }
}
