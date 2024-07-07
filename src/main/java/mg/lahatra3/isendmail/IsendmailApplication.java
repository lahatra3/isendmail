package mg.lahatra3.isendmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IsendmailApplication implements CommandLineRunner {

	@Autowired
	private IsendmailService isendmailService;
	

	public static void main(String[] args) {
		SpringApplication.run(IsendmailApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		isendmailService.sendSimpleMail(
			"lahatrap20.aps2a@gmail.com", 
			"Sending mail [Spring boot]", 
			"Hello lahatra3 ..."
		);

		isendmailService.sendEmailFromHtmlTemplate(
			"lahatrap20.aps2a@gmail.com", 
			"Sending mail [Spring boot]",
			"Hello lahatra3 ..."
		);
	}
}
