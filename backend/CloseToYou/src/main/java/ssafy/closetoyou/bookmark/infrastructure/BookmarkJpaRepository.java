package ssafy.closetoyou.bookmark.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkJpaRepository extends JpaRepository<BookmarkEntity, Long> {
    boolean existsNicknameByUserIdAndNicknameAndIsDeleted(Long userId, String nickname, boolean isDeleted);
    boolean existsBookmarkByUserIdAndBookmarkIdAndIsDeleted(Long userId, Long bookmarkId, boolean isDeleted);
    Optional<BookmarkEntity> findBookmarkByUserIdAndBookmarkIdAndIsDeleted(Long userId, Long bookmarkId, boolean isDeleted);
    Optional<List<BookmarkEntity>> findBookmarksByUserIdAndIsDeleted(Long userId, boolean isDeleted);
}
