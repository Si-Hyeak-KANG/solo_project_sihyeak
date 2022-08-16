package api.v1.domain.companytype;

import api.v1.domain.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class CompanyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;
    
    private String typeName;

    @OneToMany(mappedBy = "companyType")
    private List<Member> members = new ArrayList<>();
}
