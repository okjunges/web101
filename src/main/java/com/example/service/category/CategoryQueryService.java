package com.example.service.category;

import java.util.List;

public interface CategoryQueryService {
    boolean existsAllByIds(List<Long> ids);
}
