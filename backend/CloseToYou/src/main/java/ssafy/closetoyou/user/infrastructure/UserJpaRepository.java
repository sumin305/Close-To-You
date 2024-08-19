package ssafy.closetoyou.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByUserIdAndIsDeleted(Long userId, boolean isDeleted);
    boolean existsByEmailAndIsDeleted(String email, boolean isDeleted);
    Optional<UserEntity> findByUserIdAndIsDeleted(long id, boolean isDeleted);
    Optional<UserEntity> findByEmailAndIsDeleted(String email, boolean isDeleted);
}
