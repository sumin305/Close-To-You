package ssafy.closetoyou.closet.controller.port;

import ssafy.closetoyou.closet.controller.request.ClosetRequest;
import ssafy.closetoyou.closet.controller.response.ClosetResponse;

import java.util.List;

public interface ClosetService {
    Long addCloset(Long userId, ClosetRequest closetRequest);
    void changeClosetNickname(Long userId, Long closetId, String nickname);
    void deleteCloset(Long userId, Long closetId);
    List<ClosetResponse> getUserClosets(Long userId);
    String getClosetNicknameByClosetId(Long closetId);
}
