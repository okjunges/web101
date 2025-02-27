package com.example.service.review;

import com.example.converter.ReviewConverter;
import com.example.domain.Member;
import com.example.domain.Review;
import com.example.domain.Store;
import com.example.repository.MemberRepository;
import com.example.repository.ReviewRepository;
import com.example.repository.StoreRepository;
import com.example.web.dto.ReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public Review addReview(ReviewRequest.AddReviewDto dto, Long memberId, Long storeId) {
        Member member = memberRepository.findById(memberId).get();
        Store store = storeRepository.findById(storeId).get();

        Review newReview = ReviewConverter.toReview(dto, member, store);

        return reviewRepository.save(newReview);
    }
}
