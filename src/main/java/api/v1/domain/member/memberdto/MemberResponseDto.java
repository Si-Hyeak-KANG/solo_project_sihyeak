package api.v1.domain.member.memberdto;

import api.v1.domain.companylocation.CompanyLocation;
import api.v1.domain.companytype.CompanyType;
import api.v1.domain.member.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private long memberId;
    private String email;
    private String username;
    private int age;
    private Gender gender;
    private String companyName;
    private CompanyType companyType;
    private CompanyLocation companyLocation;

    public String getCompanyType() {
        return companyType.getTypeCode();
    }

    public String getCompanyLocation() {
        return companyLocation.getLocationCode();
    }
}
