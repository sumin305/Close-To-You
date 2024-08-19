package ssafy.closetoyou.closet.service.port;

import ssafy.closetoyou.closet.domain.Closet;

import java.util.List;

public interface ClosetRepository {
    Closet saveCloset(Closet closet);
    boolean existsClosetByClosetNickname(Long userId, String closetNickname);

    boolean existsClosetByClosetId(Long closetId);
    List<Closet> getUserClosets(Long userId);
    Closet getClosetByClosetId(Long closetId);
}