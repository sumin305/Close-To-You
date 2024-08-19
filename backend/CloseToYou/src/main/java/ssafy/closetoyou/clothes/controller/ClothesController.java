package ssafy.closetoyou.clothes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ssafy.closetoyou.clothes.controller.port.ClothesService;
import ssafy.closetoyou.clothes.controller.request.ClothesCondition;
import ssafy.closetoyou.clothes.controller.request.ClothesRequest;
import ssafy.closetoyou.clothes.controller.request.ClothesUpdateRequest;
import ssafy.closetoyou.clothes.controller.response.ClothesDetail;
import ssafy.closetoyou.clothes.controller.response.ClothesSummary;
import ssafy.closetoyou.global.common.response.SuccessResponse;
import ssafy.closetoyou.global.security.login.userdetail.CustomUserDetail;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/clothes")
@RequiredArgsConstructor
@Tag(name = "옷 API")
public class ClothesController {

    private final ClothesService clothesService;

    @Operation(summary = "옷 생성 api")
    @PostMapping
    public ResponseEntity<SuccessResponse<Long>> addClothes(@Valid @RequestBody ClothesRequest clothesRequest) {
        Long clothesId = clothesService.addClothes(clothesRequest);
        return ResponseEntity.status(201)
                .body(new SuccessResponse<>("옷 생성 성공", clothesId));
    }


    @Operation(summary = "옷 정보 수정 api")
    @PatchMapping("/{clothesId}")

    public ResponseEntity<SuccessResponse<Long>> updateClothes(Authentication authentication,
                                                               @Valid @RequestBody ClothesUpdateRequest clothesUpdateRequest,
                                                               @PathVariable Long clothesId) {
        Long userId = ((CustomUserDetail) authentication.getPrincipal()).getUser().getUserId();
        clothesService.updateClothes(userId, clothesId, clothesUpdateRequest);
        return ResponseEntity.ok()
                .body(new SuccessResponse<>("옷 수정 성공", clothesId));
    }

    @Operation(summary = "옷 삭제 수정 api")
    @DeleteMapping("/{clothesId}")
    public ResponseEntity<SuccessResponse<Long>> removeClothes(@PathVariable Long clothesId) {
        clothesService.removeClothes(clothesId);
        return ResponseEntity.ok()
                .body(new SuccessResponse<>("옷 삭제 성공", clothesId));
    }

    @Operation(summary = "옷 상세 정보 조회 api")
    @GetMapping("/{clothesId}")
    public ResponseEntity<SuccessResponse<ClothesDetail>> findClothes(@PathVariable Long clothesId) {
        ClothesDetail clothesDetail = clothesService.findClothes(clothesId);
        return ResponseEntity.ok()
                .body((new SuccessResponse<>("옷 상세 정보 조회 성공", clothesDetail)));
    }
    @Operation(summary = "nfc id 기반 옷 상세 정보 조회 api")
    @GetMapping("/nfc/{nfcId}")
    public ResponseEntity<SuccessResponse<ClothesDetail>> findClothesByNfcId(@PathVariable Long nfcId) {
        ClothesDetail clothesDetail = clothesService.findClothesByNfcId(nfcId);
        return ResponseEntity.ok()
                .body((new SuccessResponse<>("nfc id 기반 옷 상세 정보 조회 성공", clothesDetail)));
    }

    @Operation(summary = "옷 전체 조회 api")
    @GetMapping
    public ResponseEntity<SuccessResponse<List<ClothesSummary>>> findAllClothes(Authentication authentication) {
        Long userId = ((CustomUserDetail) authentication.getPrincipal()).getUser().getUserId();
        List<ClothesSummary> clothesResponse = clothesService.findAllClothes(userId);
        return ResponseEntity.ok()
                .body((new SuccessResponse<>("옷 전체 조회 성공", clothesResponse)));
    }

    @Operation(summary = "키워드 기반 옷 검색 api")
    @GetMapping("/search/{keyword}")
    public ResponseEntity<SuccessResponse<List<ClothesSummary>>> searchClothesBySearchKeyword(@PathVariable String keyword) {
        List<ClothesSummary> clothesRespons = clothesService.searchClothesBySearchKeyword(keyword);
        return ResponseEntity.ok()
                .body(new SuccessResponse<>("키워드 기반 옷 검색 성공", clothesRespons));

    }

    @Operation(summary = "옷장 및 필터 기반 옷 검색 api")
    @GetMapping("/filter")
    public ResponseEntity<SuccessResponse<List<ClothesSummary>>> searchClothesBySearchFilter(@RequestParam(value = "closetId", required = false, defaultValue = "0") Long closetId,
                                                                                                 @RequestParam(value = "color", required = false) String color,
                                                                                                 @RequestParam(value = "type", required = false) String type,
                                                                                                 @RequestParam(value = "pattern", required = false) String pattern) {
        ClothesCondition clothesCondition = new ClothesCondition(closetId, color, type, pattern);
        List<ClothesSummary> clothesResponse = clothesService.searchClothesByClothesCondition(clothesCondition);
        return ResponseEntity.ok()
                .body(new SuccessResponse<>("필터 기반 옷 검색 성공", clothesResponse));

    }
}