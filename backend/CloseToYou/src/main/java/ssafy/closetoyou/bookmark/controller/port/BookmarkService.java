package ssafy.closetoyou.bookmark.controller.port;

import ssafy.closetoyou.bookmark.controller.request.BookmarkRequest;
import ssafy.closetoyou.bookmark.controller.response.BookmarkResponse;
import ssafy.closetoyou.bookmark.domain.Bookmark;

import java.util.List;

public interface BookmarkService {

    Long addBookmark(Long userId, BookmarkRequest bookmarkRequest);
    void addBookmarkInformation(Long userId, Long bookmarkId, Long clothesId);
    void deleteBookmarkInformation(Long userId, Long bookmarkId, Long clothesId);
    void updateBookmarkNickname(Long userId, Long bookmarkId, String nickname);
    void deleteBookmark(Long userId, Long bookmarkId);
    BookmarkResponse findBookmark(Long userId, Long bookmarkId);
    List<Bookmark> findAllBookmarks(Long userId);
}
