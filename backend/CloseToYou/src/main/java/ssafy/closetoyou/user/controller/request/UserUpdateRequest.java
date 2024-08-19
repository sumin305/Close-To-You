package ssafy.closetoyou.user.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {

    @NotBlank(message = "변경할 닉네임을 입력해주세요.")
    private String nickname;


    public UserUpdateRequest(String nickname) {
        this.nickname = nickname;
    }
}
