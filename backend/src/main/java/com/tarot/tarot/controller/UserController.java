package com.tarot.tarot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.tarot.tarot.DTO.*;
import com.tarot.tarot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto request) {
        try {
            return ResponseEntity.ok(userService.loginUser(request.getNameOrEmail(), request.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    // Registration step 1: Send email with verification code
    @PostMapping("/register/send-code")
    public ResponseEntity<String> sendRegistrationCode(@RequestBody RegisterRequestDto request) {
        // System.out.println("Request received for: " + request.getEmail());
        try {
            userService.sendEmail(request.getEmail());
            return ResponseEntity.ok("Verification code sent.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send code.");
        }
    }

    // Registration step 2: Verify code and register
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto request) {
        try {
            if (userService.verifyCode(request.getEmail(), request.getCode())) {
                userService.registerUser(request.getEmail(), request.getUsername(), request.getPassword());
                return ResponseEntity.ok("Registration successful.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed.");
        }
    }


    // Password reset step 1: Send email with verification code
    @PostMapping("/password/send-code")
    public ResponseEntity<String> sendPasswordResetCode(@RequestBody PasswordResetRequestDto request) {
        try {
            userService.sendEmail(request.getEmail());
            return ResponseEntity.ok("Verification code sent.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send code.");
        }
    }
    // Password reset step 2: Verify code and reset password
    @PostMapping("/password/reset")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequestDto request) {
        try {
            if (userService.verifyCode(request.getEmail(), request.getCode())) {
                userService.forgetPassword(request.getEmail(), request.getNewPassword());
                return ResponseEntity.ok("Password reset successful.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Password reset failed.");
        }
    }

}
