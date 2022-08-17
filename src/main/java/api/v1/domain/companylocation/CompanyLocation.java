package api.v1.domain.companylocation;

import api.v1.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    private String city;

    @Column(length = 3, nullable = false, unique=true)
    private String locationCode;

    @OneToMany(mappedBy = "companyLocation")
    private List<Member> members = new ArrayList<>();

    public CompanyLocation(String city, String locationCode) {
        this.city = city;
        this.locationCode = locationCode;
    }
}
