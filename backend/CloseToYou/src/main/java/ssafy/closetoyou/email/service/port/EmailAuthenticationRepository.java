package ssafy.closetoyou.email.service.port;

import ssafy.closetoyou.email.domain.EmailAuthentication;

public interface EmailAuthenticationRepository {

    void save(EmailAuthentication emailAuthentication);
    void setVerifiedAndSave(EmailAuthentication emailAuthentication);

    EmailAuthentication findEmailAuthenticationCode(String email);
    boolean existsEmailAuthenticationCode(String email);
    boolean isEmailAuthenticated(String email);

}
