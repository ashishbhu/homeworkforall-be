package com.wb.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceManager {

	@Autowired
    private JavaMailSender javaMailSender;
	
	public void sendEmail(String emailIdTo, String subject, String message) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailIdTo);

        msg.setSubject(subject);
        msg.setText(message);

        javaMailSender.send(msg);
	}
	
	//@Async("processExecutor")
	public void sendEmailAsync(String emailIdTo, String subject, String message) {
		try {
			sendEmail(emailIdTo, subject, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
