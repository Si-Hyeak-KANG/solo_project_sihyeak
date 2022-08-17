package api.v1.domain.member.controller;

import api.v1.domain.member.entity.Member;
import api.v1.domain.member.service.MemberService;
import api.v1.dto.MultiResponseDto;
import api.v1.dto.MultiResponseListDto;
import api.v1.dto.SingleResponseDto;
import api.v1.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;

    /**
     * PostMember : 회원 가입
     */

    /**
     * GetMember : 선택 회원 조회
     */
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        Member member = memberService.findMember(memberId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponseDto(member)), HttpStatus.OK);
    }

    /**
     * GetMembers : 특정 회원 조회 (업종,지역)
     */
    @GetMapping("/search")
    public ResponseEntity getMembersByCondition(@RequestParam @Nullable String type,
                                           @RequestParam @Nullable String location) {
        List<Member> members = memberService.findMembersByCondition(type, location);
        return new ResponseEntity<>(
                new MultiResponseListDto<>(mapper.membersToMemberResponsesDto(members)), HttpStatus.OK);
    }


    /**
     * GetMembers : 특정 회원 조회 (지역)
     */
    /*
    @GetMapping("/location")
    public ResponseEntity getMembersByLocation(@RequestParam @Nullable String location) {
        List<Member> members = memberService.findMembersByLocation(location);
        return new ResponseEntity<>(
                mapper.membersToMemberResponsesDto(members),HttpStatus.OK);
    }
     */


    /**
     * GetMembers : 전체 회원 조회
     */
    @GetMapping
    public ResponseEntity getMembers(@RequestParam int page,
                                     @RequestParam int size) {
        Page<Member> pageMembers = memberService.findMembers(page - 1, size);
        List<Member> members = pageMembers.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.membersToMemberResponsesDto(members), pageMembers), HttpStatus.OK);
    }

    /**
     * UpdateMember : 회원 정보 수정
     */

    /**
     * DeleteMember : 회원 탈퇴(삭제)
     */
}
