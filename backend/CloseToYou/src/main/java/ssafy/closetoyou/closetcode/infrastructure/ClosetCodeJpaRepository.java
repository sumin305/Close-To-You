package ssafy.closetoyou.closetcode.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClosetCodeJpaRepository extends JpaRepository<ClosetCodeEntity, Long> {
    boolean existsByClosetCode(String closetCode);
    boolean existsByClosetCodeAndIsUsed(String closetCode, boolean isUsed);
    Optional<ClosetCodeEntity> findByClosetCode(String closetCode);
}