package api.v1.domain.member.mapper;

import api.v1.domain.member.entity.Member;
import api.v1.domain.member.memberdto.MemberResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberResponseDto memberToMemberResponseDto(Member member);
    List<MemberResponseDto> membersToMemberResponsesDto(List<Member> members);
}
