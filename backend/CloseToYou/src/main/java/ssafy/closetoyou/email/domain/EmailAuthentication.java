package ssafy.closetoyou.email.domain;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;
import jakarta.mail.internet.MimeUtility;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import ssafy.closetoyou.global.common.util.RandomHolder;
import ssafy.closetoyou.global.error.errorcode.UserErrorCode;
import ssafy.closetoyou.global.error.exception.CloseToYouException;
import ssafy.closetoyou.global.error.errorcode.CommonErrorCode;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

@Getter
public class EmailAuthentication {

    @Value("${GOOGLE.MAIL.ADDRESS}")
    private final String EMAIL_SENDER;
    private Long emailAuthenticationId;
    private String email;
    private int authenticationCode;
    private boolean isVerified;
    private LocalDateTime createdDateTime;

    @Builder
    public EmailAuthentication(String emailSender, Long emailAuthenticationId, String email, int authenticationCode, boolean isVerified, LocalDateTime createdDateTime) {
        EMAIL_SENDER = emailSender;
        this.emailAuthenticationId = emailAuthenticationId;
        this.email = email;
        this.authenticationCode = authenticationCode;
        this.isVerified = isVerified;
        this.createdDateTime = createdDateTime;
    }

    public static EmailAuthentication create(String email) {
        return EmailAuthentication.builder()
                .email(email)
                .build();
    }

    public void send(RandomHolder randomHolder, JavaMailSender javaMailSender) {
        setAuthenticationCode(randomHolder);
        MimeMessage emailMessage = createEmail(javaMailSender, authenticationCode);
        javaMailSender.send(emailMessage);
    }

    public void verifyCode(int code) {
        if(authenticationCode != code) {
            throw new CloseToYouException(UserErrorCode.MAIL_CHECK_FAIL);
        }
        this.isVerified = true;
    }

    private void setAuthenticationCode(RandomHolder randomHolder) {
        this.authenticationCode = randomHolder.getRandomEmailAuthenticateCode();
    }

    // 인증 메일 생성
    private MimeMessage createEmail(JavaMailSender javaMailSender, int authenticationCode) {
        MimeMessage message = javaMailSender.createMimeMessage();
        String subject = "CloseToYou 이메일 인증";
        try {
            message.setFrom(EMAIL_SENDER);
            message.setRecipients(RecipientType.TO, email);
            message.setSubject(subject);
            String body = "";
            body += "<h2>" + authenticationCode + "<h2>";
            body += "<h3> 요청하신 인증 코드 입니다. 인증 화면으로 돌아가 인증을 완료해주세요. </h3>";
            message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "Q"));
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new CloseToYouException(UserErrorCode.MAIL_CREATE_FAIL);
        }
        return message;
    }
}
