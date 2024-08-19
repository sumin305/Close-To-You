package ssafy.closetoyou.bookmark.service.port;

import ssafy.closetoyou.bookmark.domain.Bookmark;
import java.util.List;
public interface BookmarkRepository {

    boolean existsNicknameByUserIdAndNickname(Long userId, String nickname);
    boolean existsBookmarkByUserIdAndBookmarkId(Long userId, Long bookmarkId);
    Bookmark findBookmarkByUserIdAndBookmarkId(Long userId, Long bookmarkId);
    List<Bookmark> findBookmarksByUserId(Long userId);
    Bookmark saveBookmark(Bookmark bookmark);
}
