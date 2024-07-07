package mg.lahatra3.isendmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import mg.lahatra3.isendmail.utils.HtmlTemplateReaderUtils;

@Slf4j
@Service
public class IsendmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    @Async
    public void sendSimpleMail(String recipient, String subject, String body) {

        try {
            log.info("Start sending simple mail...");

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(recipient);
            message.setSubject(subject);
            message.setText(body);
            
            javaMailSender.send(message);

            log.info("Simple mail sent successfully ...");
        } catch(Exception exception) {
            log.error("Failed sending mail ...", exception);
        }
    }

    @Async
    public void sendEmailFromHtmlTemplate(String recipient, String subject, String body) throws MessagingException {
        
        try {
            log.info("Start sending mail from HTML template ...");
            MimeMessage message = javaMailSender.createMimeMessage();
            message.setRecipients(MimeMessage.RecipientType.TO, recipient);
            message.setSubject(subject);
            
            HtmlTemplateReaderUtils htmlTemplateReaderUtils = HtmlTemplateReaderUtils.builder().build();
            String htmlTemplateString = htmlTemplateReaderUtils.read();
            htmlTemplateString = htmlTemplateString.replace("<message>", body);
            message.setContent(htmlTemplateString, "text/html; charset=utf-8");

            javaMailSender.send(message);
            log.info("Mail from HTML template sent successfully ...");
        } catch(Exception exception) {
            log.error("Failed sending mail ...", exception);
        }
    }
}
