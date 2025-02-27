package com.example.service.member;

import com.example.converter.MemberConverter;
import com.example.converter.MemberPreferConverter;
import com.example.domain.Category;
import com.example.domain.Member;
import com.example.domain.mapping.MemberPrefer;
import com.example.repository.CategoryRepository;
import com.example.repository.MemberRepository;
import com.example.web.dto.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Member join(MemberRequest.JoinDto dto) {
        Member newMember = MemberConverter.toMember(dto);
        List<Category> categories = dto.getPreferCategories().stream()
                .map(category -> categoryRepository.findById(category).get())
                .toList();

        List<MemberPrefer> memberPrefers = MemberPreferConverter.toMemberPrefers(categories);
        memberPrefers.forEach(memberPrefer -> memberPrefer.setMember(newMember));

        return memberRepository.save(newMember);
    }
}