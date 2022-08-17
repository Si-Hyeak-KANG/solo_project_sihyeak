package api.v1.domain.member.entity;

import api.v1.domain.companylocation.CompanyLocation;
import api.v1.domain.companytype.CompanyType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String email;

    private String userName;

    private String password;

    private Enum<Gender> gender;

    private int age;

    private String companyName;

    @ManyToOne
    @JoinColumn(name = "TYPE_CODE")
    private CompanyType companyType;

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    @ManyToOne
    @JoinColumn(name = "LOCATION_CODE")
    private CompanyLocation companyLocation;

    public void setCompanyType(CompanyLocation companyLocation) {
        this.companyLocation = companyLocation;
    }

    private LocalDateTime joinedAt;
}
