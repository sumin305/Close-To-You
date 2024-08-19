package ssafy.closetoyou.user.controller.port;

import ssafy.closetoyou.user.controller.request.UserSignUp;
import ssafy.closetoyou.user.controller.request.UserUpdateRequest;

public interface UserService {
    Long signUp(UserSignUp userSignUp);
    void removeUser(Long userId);
    void changeUserPassword(Long userId, String oldPassword, String newPassword);
    void updateUserNickname(Long userId, UserUpdateRequest userUpdateRequest);
    void updateUserHighContrastMode(Long userId, boolean isHighContrast);
}
