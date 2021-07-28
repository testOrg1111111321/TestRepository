package com.kozluck.EmployeesApp.domain.services;

import com.kozluck.EmployeesApp.domain.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    UserService userService;

    @Async
    public void sendMail(Integer userId, String subject, String text){
        String to = userService.findOneById(userId).getEmail();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("testEmployeesApp@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
