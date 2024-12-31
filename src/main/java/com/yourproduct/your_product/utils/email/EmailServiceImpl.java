package com.yourproduct.your_product.utils.email;

import com.yourproduct.your_product.exception.CustomException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${email.sender}")
    private String emailSender;

//    @Async
    @Override
    public void send(String to, String templateName, String subject, Map<String, Object> variables) throws
            CustomException {
        if (to == null || to.isEmpty()) {
            throw new CustomException("Recipient email address is missing", HttpStatus.BAD_REQUEST);
        }

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");

            Context context = new Context();
            context.setVariables(variables);
            String content = templateEngine.process(templateName, context);

            helper.setText(content, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(emailSender);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("Failed to send email to: {}", to, e);
            throw new CustomException("Failed to send email: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
