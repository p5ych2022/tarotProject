package com.tarot.tarot.service;

import com.tarot.tarot.model.User;
public interface  UserService {
    // login
    String loginUser(String nameOrEmail,String password) throws Exception;
    // register
    User registerUser(String email, String password, String username) throws Exception;
    // forget password
    void forgetPassword(String email, String newpassword) throws Exception;
    // send email
    String sendEmail(String email) throws Exception;
    // verify code
    boolean verifyCode(String email, String code) throws Exception;
}
