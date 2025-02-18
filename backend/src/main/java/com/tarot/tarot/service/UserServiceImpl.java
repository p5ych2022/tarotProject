package com.tarot.tarot.service;

import com.tarot.tarot.Security.JwtTokenUtil;
import com.tarot.tarot.model.User;
import com.tarot.tarot.mapper.UserMapper;
import com.tarot.tarot.Security.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    // private Map<String, Integer> emailSendCount = new HashMap<>();
    private Map<String, Long> emailCodeTimeMap = new HashMap<>();
    private static final long CODE_EXPIRATION_TIME = 60 * 1000; // 60 seconds
    private Map<String, String> verificationCodes = new HashMap<>(); // Store codes temporarily

    @Autowired
    private UserMapper userMapper;

//    @Resource
//    private JavaMailSender mailSender;


    public static boolean isEmail(String input){
        String reg = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9_-]+\\.com$";
        return input.matches(reg);
    }

    @Override
    public boolean verifyCode(String email, String inputCode)  throws Exception{
        long sentTime = emailCodeTimeMap.get(email);
        if (System.currentTimeMillis() - sentTime > CODE_EXPIRATION_TIME) {
            throw new Exception("验证码已过期");
        }
        if (!verificationCodes.get(email).equals(inputCode)) {
            throw new Exception("验证码错误");
        }
        return true;
    }

    @Override
    public String loginUser(String nameOrEmail, String password) {
        User user;
        // 判断输入的是 email 还是 username
        if (isEmail(nameOrEmail)) {
            // email
            user = userMapper.getUserByEmail(nameOrEmail);
            if (user == null) {
                throw new RuntimeException("邮箱未注册");
//                return "邮箱未注册";
            }
        } else {
            // username
            user = userMapper.getUserByName(nameOrEmail);
            if (user == null) {
                throw new RuntimeException("用户名不存在");
//                return "用户名不存在";
            }
        }

//        if (user == null) {
//            throw new RuntimeException("用户不存在");
//        }

        // 验证密码
        String encodedPassword = Md5Util.md5(password);
        if (encodedPassword != null && !encodedPassword.equals(user.getPassword())) {
            throw new RuntimeException("密码错误");
//            return "密码错误";
        }
        String token = JwtTokenUtil.generateToken(user.getUsername());

        // 返回 Token 作为登录成功的标识
        return token;

    }

    @Override
    public User registerUser(String email, String username, String password) throws Exception {
        if (userMapper.existsByEmail(email)) {
            throw new Exception("邮箱已被注册");
        }
        if (userMapper.existsByUsername(username)) {
            throw new Exception("用户名已被注册");
        }

        // 密码加密
        String encodedPassword = Md5Util.md5(password);

        // 创建用户对象
        User newUser = new User();



        // 添加到数据库
        try {
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setPassword(encodedPassword);
            userMapper.addUser(newUser);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("注册失败");
        }


        // 返回创建成功的用户
        return newUser;
    }

    @Override
    public void forgetPassword(String email, String newPassword) throws Exception {
        if (!userMapper.existsByEmail(email)) {
            throw new Exception("邮箱未注册");
        }

        // 加密新密码
        // String encodedPassword = passwordEncoder.encode(newPassword);


        // 更新密码
        try{
            System.out.println(newPassword);
            String encodedPassword = Md5Util.md5(newPassword);
            boolean updatedRows = userMapper.changePassword(email, encodedPassword);
            if (!updatedRows) {
                throw new Exception("更新密码失败");
            }
            System.out.println("更新密码成功");
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("更新密码失败");
        }
    }

    @Override
    public String  sendEmail(String email)  {

////        if (emailSendCount.getOrDefault(email, 0) >= 3) {
////            throw new Exception("同一邮箱每天只能发送3次验证码");
////        }
//        String verificationCode = String.valueOf(new Random().nextInt(10000,99999));
//     //   String verificationCode = String.valueOf(new Random().nextInt(10000,99999));
//
//        // Send the email
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("1836969156@qq.com");
//        message.setTo(email);
//        message.setSubject("Psych's tarot master");
//        message.setText("Your verification code is: " + verificationCode);
//
//        //test
//        mailSender.send(message);
//        verificationCodes.put(email, verificationCode);
//        emailCodeTimeMap.put(email, System.currentTimeMillis());
//        return verificationCode;
//
////        verificationCodes.put("1836969156@qq.com", "12345");
////        emailCodeTimeMap.put("1836969156@qq.com", System.currentTimeMillis());
////
////        return "12345";
        return "12345";
    }



}
