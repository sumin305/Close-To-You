package ssafy.closetoyou.email.infrastructure;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ssafy.closetoyou.global.error.errorcode.UserErrorCode;
import ssafy.closetoyou.global.error.exception.CloseToYouException;
import ssafy.closetoyou.global.error.errorcode.CommonErrorCode;
import ssafy.closetoyou.email.domain.EmailAuthentication;
import ssafy.closetoyou.email.service.port.EmailAuthenticationRepository;

@RequiredArgsConstructor
@Repository
public class EmailAuthenticationRepositoryImpl implements EmailAuthenticationRepository {

    private final EmailAuthenticationJpaRepository emailAuthenticationJpaRepository;

    @Override
    public void save(EmailAuthentication emailAuthentication) {
        emailAuthenticationJpaRepository.save(EmailAuthenticationEntity.fromModel(emailAuthentication));
    }

    @Override
    public void setVerifiedAndSave(EmailAuthentication emailAuthentication) {
        Long emailId = emailAuthentication.getEmailAuthenticationId();
        EmailAuthenticationEntity emailAuthenticationEntity = emailAuthenticationJpaRepository.findById(emailId)
                .orElseThrow(() -> new CloseToYouException(UserErrorCode.NOT_FOUND_MAIL_CODE));
        emailAuthenticationEntity.verify();
        emailAuthenticationJpaRepository.save(emailAuthenticationEntity);
    }

    @Override
    public EmailAuthentication findEmailAuthenticationCode(String email) {
        return emailAuthenticationJpaRepository.findByEmail(email)
                .orElseThrow(() -> new CloseToYouException(UserErrorCode.NOT_FOUND_MAIL_CODE))
                .toModel();
    }

    @Override
    public boolean existsEmailAuthenticationCode(String email) {
        return emailAuthenticationJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean isEmailAuthenticated(String email) {
        return emailAuthenticationJpaRepository.existsByEmailAndAndIsVerified(email, true);
    }

}
