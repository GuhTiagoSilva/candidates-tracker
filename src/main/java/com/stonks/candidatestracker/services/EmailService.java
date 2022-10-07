package com.stonks.candidatestracker.services;

import com.stonks.candidatestracker.dto.EmailDetailsDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@Log4j2
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public String sendMail(EmailDetailsDto details, String fileName) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, false);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody(), true);
            mimeMessageHelper.setSubject(details.getSubject());
            if (details.getAttachment() != null)
                mimeMessageHelper.addAttachment(fileName,new FileSystemResource(new File(details.getAttachment())));
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";

        } catch (MessagingException e) {
            log.error("ERROR: {}", e.getMessage());
            return "Error while sending mail!!!";
        }
    }
}
