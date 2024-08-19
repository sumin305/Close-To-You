package ssafy.closetoyou.user.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserPasswordUpdateRequest {

    @NotNull(message = "기존 비밀번호를 입력해주세요.")
    private String oldPassword;

    @NotNull(message = "새로운 비밀번호를 입력해주세요.")
    private String newPassword;
}
