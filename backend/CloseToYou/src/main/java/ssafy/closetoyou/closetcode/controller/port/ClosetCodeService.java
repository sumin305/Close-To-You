package ssafy.closetoyou.closetcode.controller.port;

import ssafy.closetoyou.closetcode.domain.ClosetCode;

public interface ClosetCodeService {
    ClosetCode makeRandomClosetCodeAndSave();
}