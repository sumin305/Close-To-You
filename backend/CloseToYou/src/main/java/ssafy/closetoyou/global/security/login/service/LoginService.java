package ssafy.closetoyou.global.security.login.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ssafy.closetoyou.global.security.login.userdetail.CustomUserDetail;
import ssafy.closetoyou.user.domain.User;
import ssafy.closetoyou.user.service.port.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        User user = userRepository.findUserByUserEmail(userEmail);
        return new CustomUserDetail(user);
    }
}
