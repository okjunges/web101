package com.example.service.review;

import com.example.domain.Review;
import com.example.web.dto.ReviewRequest;

public interface ReviewCommandService {
    Review addReview(ReviewRequest.AddReviewDto dto, Long memberId, Long storeId);
}
