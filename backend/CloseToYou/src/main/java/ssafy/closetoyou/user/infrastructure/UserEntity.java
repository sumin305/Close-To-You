package ssafy.closetoyou.user.infrastructure;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import ssafy.closetoyou.user.domain.User;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity(name = "users")
@Getter
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long userId;

    private String nickname;

    private String password;

    @Email(message = "이메일 형식이 올바르지 않습니다.", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    private Boolean isDeleted;
    private Boolean isHighContrast;

    @CreatedDate
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    private LocalDateTime updatedDateTime;

    @Builder
    public UserEntity(Long userId, String nickname, String password, String email, Boolean isDeleted, Boolean isHighContrast, LocalDateTime createdDateTime, LocalDateTime updatedDateTime) {
        this.userId = userId;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.isDeleted = isDeleted;
        this.isHighContrast = isHighContrast;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
    }

    public static UserEntity fromModel(User user) {
        return builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .password(user.getPassword())
                .email(user.getEmail())
                .isDeleted(user.getIsDeleted() != null && user.getIsDeleted())
                .isHighContrast(user.getIsHighContrast() != null && user.getIsHighContrast())
                .createdDateTime(user.getCreatedDateTime())
                .updatedDateTime(user.getUpdatedDateTime())
                .build();
    }

    public User toModel() {
        return User.builder()
                .userId(userId)
                .nickname(nickname)
                .password(password)
                .email(email)
                .createdDateTime(createdDateTime)
                .updatedDateTime(updatedDateTime)
                .isDeleted(isDeleted)
                .isHighContrast(isHighContrast)
                .createdDateTime(createdDateTime)
                .updatedDateTime(updatedDateTime)
                .build();
    }

}
