package ssafy.closetoyou.closet.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClosetJpaRepository extends JpaRepository<ClosetEntity, Long> {

    boolean existsByClosetIdAndIsDeleted(Long closetId, Boolean isDeleted);


    @Query(
            "SELECT COUNT(cl.nickname) > 0 " +
                    "FROM ClosetEntity cl " +
                    "WHERE cl.nickname = :nickname " +
                    "AND cl.isDeleted = :isDeleted " +
                    "AND cl.userId = :userId "
    )
    boolean existsByNicknameIsDeleted(Long userId, String nickname, boolean isDeleted);
    Optional<ClosetEntity> findClosetByClosetIdAndIsDeleted(Long closetId, Boolean isDeleted);
    Optional<List<ClosetEntity>> findClosetsByUserIdAndIsDeleted(Long userId, boolean isDeleted);
}