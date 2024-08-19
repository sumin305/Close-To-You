package ssafy.closetoyou.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;
import ssafy.closetoyou.user.controller.request.UserUpdateRequest;

import java.time.LocalDateTime;

@Getter
public class User {

    private Long userId;
    private String nickname;
    private String password;
    private String email;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private Boolean isDeleted;
    private Boolean isHighContrast;

    @Builder
    public User(Long userId, String nickname, String password, String email, LocalDateTime createdDateTime, LocalDateTime updatedDateTime, Boolean isDeleted, Boolean isHighContrast) {
        this.userId = userId;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
        this.isDeleted = isDeleted;
        this.isHighContrast = isHighContrast;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public void updateUserInfo(UserUpdateRequest userUpdateRequest) {
        this.nickname = userUpdateRequest.getNickname();
    }

    public void updateUserHighContrastMode(boolean isHighContrast) {
        this.isHighContrast = isHighContrast;
    }

    public void passwordEncode(PasswordEncoder passwordEncoder, String newPassword) {
        this.password = passwordEncoder.encode(newPassword);
    }
}
