package ssafy.closetoyou.bookmark.infrastructure.bookmarkinformation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkInformationJpaRepository extends JpaRepository<BookmarkInformationEntity, Long> {
    boolean existsBookmarkByBookmarkIdAndClothesId(Long bookmarkId, Long clothesId);
    void deleteBookmarkInformationByClothesId(Long clothesId);
    void deleteBookmarkInformationByBookmarkId(Long bookmarkId);
    List<BookmarkInformationEntity> findBookmarkInformationsByBookmarkId(Long bookmarkId);
}
