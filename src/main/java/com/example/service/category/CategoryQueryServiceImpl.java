package com.example.service.category;

import com.example.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryQueryServiceImpl implements CategoryQueryService {
    private final CategoryRepository categoryRepository;

    @Override
    public boolean existsAllByIds(List<Long> ids) {
        return ids.stream().allMatch(id -> categoryRepository.existsById(id));
    }
}
