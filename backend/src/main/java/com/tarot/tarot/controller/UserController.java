package com.tarot.tarot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.tarot.tarot.model.User;
import com.tarot.tarot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;


//    @PostMapping("/login")
//    public User login(@RequestParam String nameOrEmail, @RequestParam String password) throws Exception {
//        return userService.loginUser(nameOrEmail,password); // 调用登录方法
//    }


    // Registration step 1: Send email with verification code
    @PostMapping("/register/send-code")
    public ResponseEntity<String> sendRegistrationCode(@RequestParam String email) {
        try {
            userService.sendEmail(email);
            return ResponseEntity.ok("Verification code sent.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send code.");
        }
    }

    // Registration step 2: Verify code and register
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String email,
                                           @RequestParam String username,
                                           @RequestParam String password,
                                           @RequestParam String code) {
        try {
            if (userService.verifyCode(email, code)) {
                userService.registerUser(email, username, password);
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
    public ResponseEntity<String> sendPasswordResetCode(@RequestParam String email) {
        try {
            userService.sendEmail(email);
            return ResponseEntity.ok("Verification code sent.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send code.");
        }
    }
    // Password reset step 2: Verify code and reset password
    @PostMapping("/password/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String email,
                                                @RequestParam String newPassword,
                                                @RequestParam String code) {
        try {
            if (userService.verifyCode(email, code)) {
                userService.forgetPassword(email, newPassword);
                return ResponseEntity.ok("Password reset successful.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Password reset failed.");
        }
    }

}
