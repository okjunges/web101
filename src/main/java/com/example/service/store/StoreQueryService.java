package com.example.service.store;

import com.example.domain.Review;
import com.example.domain.Store;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long id);
    Page<Review> getReviews(Long StoreId, Integer page);
}