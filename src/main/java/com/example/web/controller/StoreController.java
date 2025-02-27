package com.example.web.controller;

import com.example.converter.ReviewConverter;
import com.example.domain.Review;
import com.example.domain.Store;
import com.example.payload.CommonResponse;
import com.example.service.review.ReviewCommandService;
import com.example.service.store.StoreQueryService;
import com.example.validaion.annotation.CheckPage;
import com.example.validaion.annotation.ExistsMember;
import com.example.validaion.annotation.ExistsStore;
import com.example.web.dto.ReviewRequest;
import com.example.web.dto.ReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreQueryService storeQueryService;
    private final ReviewCommandService reviewCommandService;

    @PostMapping(value = "/{storeId}/{memberId}/reviews", consumes = "multipart/form-data")
    @Operation(summary = "가게에 리뷰 추가하는 API",
            description = "특정 가게의 리뷰를 추가하는 API이며 path variable로 가게id와 사용자 id가 필요합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "COMMON_200", description = "성공"),
            @ApiResponse(responseCode = "STORE_4001", description = "가게를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = CommonResponse.class))),
            @ApiResponse(responseCode = "Member_4001", description = "사용자를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = CommonResponse.class))),
    })
    @Parameters(value = {
            @Parameter(name = "storeId", description = "가게 ID(path variable)", required = true),
            @Parameter(name = "memberId", description = "사용자 ID(path variable)", required = true),
    })
    public CommonResponse<ReviewResponse.AddReviewResultDto> addReview(
            @ExistsStore @PathVariable(name = "storeId") Long storeId,
            @ExistsMember @PathVariable(name = "memberId") Long memberId,
            @RequestPart MultipartFile reviewImage,
            @RequestPart @Valid ReviewRequest.AddReviewDto dto) {
        Review review = reviewCommandService.addReview(dto, storeId, memberId, reviewImage);
        return CommonResponse.onSuccess(ReviewConverter.toAddReviewResultDto(review));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page번호가 필요합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "COMMON_200", description = "성공"),
            @ApiResponse(responseCode = "STORE_4001", description = "가게를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = CommonResponse.class))),
    })
    @Parameters(value = {
            @Parameter(name = "storeId", description = "가게 ID(path variable)", required = true),
    })
    public CommonResponse<ReviewResponse.ReviewListDto> getStoreReviews(
            @ExistsStore @PathVariable(name = "storeId") Long storeId,
            @CheckPage @RequestParam(name = "page", defaultValue = "0") Integer page) {
        Page<Review> reviews = storeQueryService.getReviews(storeId, page);

        return CommonResponse.onSuccess(ReviewConverter.toReviewListDto(reviews));
    }
}