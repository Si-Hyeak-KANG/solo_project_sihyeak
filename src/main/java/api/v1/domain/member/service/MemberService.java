package api.v1.domain.member.service;

import api.v1.domain.companylocation.CompanyLocation;
import api.v1.domain.companylocation.CompanyLocationRepository;
import api.v1.domain.companytype.CompanyType;
import api.v1.domain.companytype.CompanyTypeRepository;
import api.v1.domain.member.entity.Member;
import api.v1.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final CompanyLocationRepository companyLocationRepository;
    private final CompanyTypeRepository companyTypeRepository;

    /**
     * 회원 가입 기능
     */

    /**
     * 선택 회원 정보 조회 기능
     * @return
     */
    public Member findMember(long memberId) {
        return memberRepository.findById(memberId).get();
    }

    /**
     * 특정 조건에 맞는 회원 조회 기능(업종,지역)
     */
    public List<Member> findMembersByCondition(String type, String location) {

        CompanyType companyType = companyTypeRepository.findByTypeName(type);
        CompanyLocation companyLocation = companyLocationRepository.findByCity(location);

        return memberRepository.findAllByCompanyTypeAndCompanyLocationContaining(companyType.getTypeCode(),companyLocation.getLocationCode());
    }

    /**
     * 특정 조건에 맞는 회원 조회 기능(지역)
     */
    /*
    public List<Member> findMembersByLocation(String location) {

        CompanyLocation companyLocation = companyLocationRepository.findByCity(location);
        return memberRepository.findAllByCompanyLocationContaining(companyLocation.getLocationId());
    }
    */


    /**
     * 회원 전체 조회 기능
     */
    public Page<Member> findMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size, Sort.by("memberId").descending()));
    }

    /**
     * 선택한 회원의 정보 수정 기능
     */

    /**
     * 회원 탈퇴 기능
     */


}
