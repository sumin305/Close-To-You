package ssafy.closetoyou.user.service.port;

import ssafy.closetoyou.user.domain.User;
import ssafy.closetoyou.user.infrastructure.UserEntity;

public interface UserRepository {
    User saveUser(User user);
    boolean existsUserByUserEmail(String userEmail);
    boolean existsUserByUserId(Long userId);
    User findUserByUserId(Long userId);
    User findUserByUserEmail(String userEmail);
}
