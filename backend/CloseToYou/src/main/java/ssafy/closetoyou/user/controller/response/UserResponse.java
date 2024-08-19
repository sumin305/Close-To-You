package ssafy.closetoyou.user.controller.response;

import lombok.Builder;
import lombok.Getter;
import ssafy.closetoyou.user.domain.User;
import java.time.LocalDateTime;

@Getter
public class UserResponse {
    private String email;
    private String nickname;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private Boolean isHighContrast;

    @Builder
    public UserResponse(String email, String nickname, LocalDateTime createdDateTime, LocalDateTime updatedDateTime, Boolean isHighContrast) {
        this.email = email;
        this.nickname = nickname;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
        this.isHighContrast = isHighContrast;
    }

    public static UserResponse fromModel(User user) {
        return builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .createdDateTime(user.getCreatedDateTime())
                .updatedDateTime(user.getUpdatedDateTime())
                .isHighContrast(user.getIsHighContrast())
                .build();
    }
}
