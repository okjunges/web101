package com.example.converter;

import com.example.domain.Member;
import com.example.domain.Review;
import com.example.domain.Store;
import com.example.web.dto.ReviewRequest;
import com.example.web.dto.ReviewResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponse.AddReviewResultDto toAddReviewResultDto(Review review) {
        return ReviewResponse.AddReviewResultDto.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequest.AddReviewDto dto, Member member, Store store) {
        return Review.builder()
                .title(dto.getTitle())
                .body(dto.getBody())
                .score(dto.getScore())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResponse.SimpleReviewDto toSimpleReviewDto(Review review) {
        return ReviewResponse.SimpleReviewDto.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static ReviewResponse.ReviewListDto toReviewListDto(Page<Review> reviews) {
        List<ReviewResponse.SimpleReviewDto> reviewDtos = reviews.stream()
                .map(review -> ReviewConverter.toSimpleReviewDto(review))
                .toList();

        return ReviewResponse.ReviewListDto.builder()
                .isLast(reviews.isLast())
                .isFirst(reviews.isFirst())
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .listSize(reviewDtos.size())
                .reviews(reviewDtos)
                .build();
    }
}