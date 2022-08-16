package api.v1.domain.member.memberdto;

import api.v1.domain.companylocation.CompanyLocation;
import api.v1.domain.companytype.CompanyType;
import api.v1.domain.member.entity.Gender;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberResponseDto {
    private long memberId;
    private String email;
    private String username;
    private String age;
    private Gender gender;
    private String CompanyName;
    private CompanyType companyType;
    private CompanyLocation companyLocation;
}
