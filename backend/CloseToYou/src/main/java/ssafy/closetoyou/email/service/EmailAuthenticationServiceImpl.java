package ssafy.closetoyou.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ssafy.closetoyou.email.infrastructure.EmailAuthenticationEntity;
import ssafy.closetoyou.global.common.util.RandomHolder;
import ssafy.closetoyou.global.error.errorcode.UserErrorCode;
import ssafy.closetoyou.global.error.exception.CloseToYouException;
import ssafy.closetoyou.global.error.errorcode.CommonErrorCode;
import ssafy.closetoyou.email.controller.port.EmailAuthenticationService;
import ssafy.closetoyou.email.domain.EmailAuthentication;
import ssafy.closetoyou.email.domain.EmailAuthenticationCheck;
import ssafy.closetoyou.email.service.port.EmailAuthenticationRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailAuthenticationServiceImpl implements EmailAuthenticationService {

    private final JavaMailSender javaMailSender;
    private final EmailAuthenticationRepository emailAuthenticationRepository;
    private final RandomHolder randomHolder;

    public void sendEmail(String email) {
        EmailAuthentication emailAuthentication = EmailAuthentication.create(email);

        if (emailAuthenticationRepository.existsEmailAuthenticationCode(email)) {
            emailAuthentication = emailAuthenticationRepository.findEmailAuthenticationCode(email);

            if(emailAuthentication.isVerified()) {
                throw new CloseToYouException(UserErrorCode.ALREADY_AUTHENTICATED_CODE);
            }
        }

        emailAuthentication.send(randomHolder, javaMailSender);
        emailAuthenticationRepository.save(emailAuthentication);
    }

    @Override
    public void checkAuthenticationCode(EmailAuthenticationCheck emailAuthenticationCheck) {

        int authenticationCode = emailAuthenticationCheck.getCode();
        String email = emailAuthenticationCheck.getEmail();
        EmailAuthentication emailAuthentication = findEmailAuthentication(email);

        emailAuthentication.verifyCode(authenticationCode);
        emailAuthenticationRepository.setVerifiedAndSave(emailAuthentication);
    }

    private EmailAuthentication findEmailAuthentication(String email) {
        return emailAuthenticationRepository.findEmailAuthenticationCode(email);
    }
}
