package com.example.service.review;

import com.example.aws.S3Manager;
import com.example.converter.ReviewConverter;
import com.example.domain.Member;
import com.example.domain.Review;
import com.example.domain.Store;
import com.example.domain.Uuid;
import com.example.repository.MemberRepository;
import com.example.repository.ReviewRepository;
import com.example.repository.StoreRepository;
import com.example.repository.UuidRepository;
import com.example.service.member.MemberQueryService;
import com.example.web.dto.ReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final UuidRepository uuidRepository;
    private final S3Manager s3Manager;

    @Override
    public Review addReview(ReviewRequest.AddReviewDto dto, Long memberId, Long storeId, MultipartFile reviewImage) {
        Member member = memberRepository.findById(memberId).get();
        Store store = storeRepository.findById(storeId).get();

        Review newReview = ReviewConverter.toReview(dto, member, store);

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid)
                .build());
        String imageUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), reviewImage);

        return reviewRepository.save(newReview);
    }
}
