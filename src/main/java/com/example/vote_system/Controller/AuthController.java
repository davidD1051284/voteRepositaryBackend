package com.example.vote_system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vote_system.Entity.User;
import com.example.vote_system.Repositary.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User req) {

        User user = userRepository.findByUsername(req.getUsername());

        if (user == null) {
            return ResponseEntity.status(401).body("帳號不存在");
        }

        if (!user.getPassword().equals(req.getPassword())) {
            return ResponseEntity.status(401).body("密碼錯誤");
        }

        return ResponseEntity.ok(user);
    }
}