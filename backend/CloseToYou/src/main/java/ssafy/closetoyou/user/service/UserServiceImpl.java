package ssafy.closetoyou.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.closetoyou.email.controller.port.EmailAuthenticationService;
import ssafy.closetoyou.email.service.port.EmailAuthenticationRepository;
import ssafy.closetoyou.global.error.errorcode.UserErrorCode;
import ssafy.closetoyou.global.error.exception.CloseToYouException;
import ssafy.closetoyou.user.controller.port.UserService;
import ssafy.closetoyou.user.controller.request.UserUpdateRequest;
import ssafy.closetoyou.user.domain.User;
import ssafy.closetoyou.user.controller.request.UserSignUp;
import ssafy.closetoyou.user.infrastructure.UserEntity;
import ssafy.closetoyou.user.service.port.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailAuthenticationRepository emailAuthenticationRepository;

    public Long signUp(UserSignUp userSignUp) {
        validateSignUpEmail(userSignUp.getEmail());
        User user = userSignUp.toModel();
        user.passwordEncode(passwordEncoder, user.getPassword());
        return userRepository.saveUser(user).getUserId();
    }

    @Override
    public void removeUser(Long userId) {
        checkUserExists(userId);

        User user = userRepository.findUserByUserId(userId);
        user.delete();
        userRepository.saveUser(user);
    }

    @Override
    public void changeUserPassword(Long userId, String oldPassword, String newPassword) {
        checkUserExists(userId);

        User user = userRepository.findUserByUserId(userId);
        checkPasswordMatch(oldPassword, user.getPassword());
        user.passwordEncode(passwordEncoder, newPassword);

        userRepository.saveUser(user);
    }

    @Override
    public void updateUserNickname(Long userId, UserUpdateRequest userUpdateRequest) {
        checkUserExists(userId);

        User user = userRepository.findUserByUserId(userId);

        user.updateUserInfo(userUpdateRequest);
        userRepository.saveUser(user);
    }

    @Override
    public void updateUserHighContrastMode(Long userId, boolean isHighContrast) {
        checkUserExists(userId);

        User user = userRepository.findUserByUserId(userId);
        user.updateUserHighContrastMode(isHighContrast);
        userRepository.saveUser(user);
    }

    private void checkPasswordMatch(CharSequence oldPassword, String encodedOldpassword) {
        if (!passwordEncoder.matches(oldPassword, encodedOldpassword)) {
            throw new CloseToYouException(UserErrorCode.NO_MATCH_PASSWORD);
        }
    }

    public void validateSignUpEmail(String email) {
        if (userRepository.existsUserByUserEmail(email)) {
            throw new CloseToYouException(UserErrorCode.DUPLICATE_EMAIL);
        }

        if (!emailAuthenticationRepository.isEmailAuthenticated(email)) {
            throw new CloseToYouException(UserErrorCode.NOT_AUTHENTICATED);
        }
    }

    private void checkUserExists(Long userId) {
        if (!userRepository.existsUserByUserId(userId)) {
            throw new CloseToYouException(UserErrorCode.USER_NOT_FOUND);
        }
    }
}
