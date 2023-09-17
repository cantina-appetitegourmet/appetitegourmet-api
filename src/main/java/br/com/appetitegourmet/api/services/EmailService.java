package br.com.appetitegourmet.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.core.io.FileSystemResource;
import java.io.File;
import java.io.IOException;
import java.util.List;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender mailSender;

    public void sendHtmlEmail(String from, 
    						  String to, 
    						  String subject, 
    						  String body, 
    						  List<String> listFileName) throws MessagingException, IOException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body);

		if(listFileName != null) {
			for(String fileName : listFileName) {
				FileSystemResource file = new FileSystemResource(new File(fileName));
				helper.addAttachment(fileName, file);
			}
		}

		mailSender.send(message);
        // "sender@example.com"
        // "recipient@example.com"
        //String htmlContent = "<h1>This is a test Spring Boot email</h1>" +
        //                     "<p>It can contain <strong>HTML</strong> content.</p>";
    }

}
