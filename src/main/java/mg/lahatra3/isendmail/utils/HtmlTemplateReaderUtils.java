package mg.lahatra3.isendmail.utils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
public class HtmlTemplateReaderUtils {

    public String read() {
        
        log.info("Start reading mail template ...");
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("template/mail.html");
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            return scanner.useDelimiter("\\A").next();
        }
    }
}
