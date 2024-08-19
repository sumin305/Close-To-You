package ssafy.closetoyou.bookmark.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ssafy.closetoyou.bookmark.domain.Bookmark;
import ssafy.closetoyou.bookmark.service.port.BookmarkRepository;
import ssafy.closetoyou.global.error.errorcode.BookmarkErrorCode;
import ssafy.closetoyou.global.error.exception.CloseToYouException;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepository {

    private final BookmarkJpaRepository bookmarkJpaRepository;

    @Override
    public boolean existsNicknameByUserIdAndNickname(Long userId, String nickname) {
        return bookmarkJpaRepository.existsNicknameByUserIdAndNicknameAndIsDeleted(userId, nickname, false);
    }

    @Override
    public boolean existsBookmarkByUserIdAndBookmarkId(Long userId, Long bookmarkId) {
        return bookmarkJpaRepository.existsBookmarkByUserIdAndBookmarkIdAndIsDeleted(userId, bookmarkId, false);
    }

    @Override
    public Bookmark findBookmarkByUserIdAndBookmarkId(Long userId, Long bookmarkId) {
        return bookmarkJpaRepository.findBookmarkByUserIdAndBookmarkIdAndIsDeleted(userId, bookmarkId, false)
                .orElseThrow(() -> new CloseToYouException(BookmarkErrorCode.NO_BOOKMARK_EXCEPTION))
                .toModel();
    }

    @Override
    public List<Bookmark> findBookmarksByUserId(Long userId) {
        return bookmarkJpaRepository.findBookmarksByUserIdAndIsDeleted(userId, false)
                .orElse(new ArrayList<>())
                .stream()
                .map(BookmarkEntity::toModel)
                .toList();
    }

    @Override
    public Bookmark saveBookmark(Bookmark bookmark) {
        return bookmarkJpaRepository.save(BookmarkEntity.fromModel(bookmark)).toModel();
    }
}
