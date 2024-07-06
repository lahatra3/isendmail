package mg.lahatra3.isendmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IsendmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendSimpleMail(String recipient, String subject, String body) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(recipient);
            message.setSubject(subject);
            message.setText(body);

            log.info("Starting sending mail...");
            javaMailSender.send(message);
            log.info("Mail sending successfully...");
        } catch(Exception exception) {
            log.error("Failed sending mail...", exception);
        }
    }
}
