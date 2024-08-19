package ssafy.closetoyou.bookmark.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ssafy.closetoyou.bookmark.controller.port.BookmarkService;
import ssafy.closetoyou.bookmark.controller.request.BookmarkRequest;
import ssafy.closetoyou.bookmark.controller.response.BookmarkResponse;
import ssafy.closetoyou.bookmark.domain.Bookmark;
import ssafy.closetoyou.closet.controller.port.ClosetService;
import ssafy.closetoyou.closet.controller.request.ClosetRequest;
import ssafy.closetoyou.global.common.response.SuccessResponse;
import ssafy.closetoyou.global.security.login.userdetail.CustomUserDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
@Tag(name = "북마크 API", description = "북마크 API")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Operation(summary = "북마크 생성 api")
    @PostMapping
    public ResponseEntity<SuccessResponse<Long>> addBookmark(Authentication authentication,
                                                             @Valid @RequestBody BookmarkRequest bookmarkRequest) {
        Long userId = ((CustomUserDetail) authentication.getPrincipal()).getUser().getUserId();
        Long bookmarkId = bookmarkService.addBookmark(userId, bookmarkRequest);
        return ResponseEntity.status(201)
                .body(new SuccessResponse<>("북마크 생성에 성공했습니다.", bookmarkId));
    }

    @Operation(summary = "북마크 옷 구성 추가 api")
    @PatchMapping("/{bookmarkId}/add/{clothesId}")
    public ResponseEntity<SuccessResponse<Long>> addBookmarkInformation(Authentication authentication,
                                                                        @PathVariable Long bookmarkId,
                                                                        @PathVariable Long clothesId) {
        Long userId = ((CustomUserDetail) authentication.getPrincipal()).getUser().getUserId();
        bookmarkService.addBookmarkInformation(userId, bookmarkId, clothesId);
        return ResponseEntity.ok(
                new SuccessResponse<>("북마크 옷 구성 추가에 성공했습니다.", bookmarkId)
        );
    }

    @Operation(summary = "북마크 옷 구성 삭제 api")
    @PatchMapping("/{bookmarkId}/delete/{clothesId}")
    public ResponseEntity<SuccessResponse<Long>> deleteBookmarkInformation(Authentication authentication,
                                                                           @PathVariable Long bookmarkId,
                                                                           @PathVariable Long clothesId) {
        Long userId = ((CustomUserDetail) authentication.getPrincipal()).getUser().getUserId();
        bookmarkService.deleteBookmarkInformation(userId, bookmarkId, clothesId);
        return ResponseEntity.ok(
                new SuccessResponse<>("북마크 옷 구성 삭제에 성공했습니다.", bookmarkId)
        );
    }

    @Operation(summary = "북마크 닉네임 변경 api")
    @PatchMapping("/{bookmarkId}/nickname")
    public ResponseEntity<SuccessResponse<Long>> updateBookmarkNickname(Authentication authentication,
                                                                        @PathVariable Long bookmarkId,
                                                                        @Valid @RequestBody BookmarkRequest bookmarkRequest) {
        Long userId = ((CustomUserDetail) authentication.getPrincipal()).getUser().getUserId();
        String nickname = bookmarkRequest.getNickname();
        bookmarkService.updateBookmarkNickname(userId, bookmarkId, nickname);
        return ResponseEntity.ok(
                new SuccessResponse<>("북마크 닉네임 변경에 성공했습니다.", bookmarkId)
        );
    }

    @Operation(summary=  "북마크 삭제 api")
    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<SuccessResponse<Long>> deleteBookmark(Authentication authentication,
                                                                @PathVariable Long bookmarkId) {
        Long userId = ((CustomUserDetail) authentication.getPrincipal()).getUser().getUserId();
        bookmarkService.deleteBookmark(userId, bookmarkId);
        return ResponseEntity.ok(
                new SuccessResponse<>("북마크 삭제에 성공했습니다.", bookmarkId)
        );
    }

    @Operation(summary = "북마크 상세 조회 api")
    @GetMapping("/{bookmarkId}")
    public ResponseEntity<SuccessResponse<BookmarkResponse>> findBookmark(Authentication authentication,
                                                                               @PathVariable Long bookmarkId) {
        Long userId = ((CustomUserDetail) authentication.getPrincipal()).getUser().getUserId();
        BookmarkResponse bookmark = bookmarkService.findBookmark(userId, bookmarkId);
        return ResponseEntity.ok(
                new SuccessResponse<>("북마크 상세 조회에 성공했습니다.", bookmark)
        );
    }

    @Operation(summary = "북마크 전체 조회 api")
    @GetMapping
    public ResponseEntity<SuccessResponse<List<Bookmark>>> findBookmarks(Authentication authentication) {
        Long userId = ((CustomUserDetail) authentication.getPrincipal()).getUser().getUserId();
        List<Bookmark> bookmarks = bookmarkService.findAllBookmarks(userId);
        return ResponseEntity.ok(
                new SuccessResponse<>("북마크 전체 조회에 성공했습니다.", bookmarks)
        );
    }

}
