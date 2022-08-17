package api.v1.domain.companytype;

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
public class CompanyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;
    
    private String typeName;

    @Column(length = 3, nullable = false, unique=true)
    private String typeCode;

    @OneToMany(mappedBy = "companyType")
    private List<Member> members = new ArrayList<>();

    public CompanyType(String typeName, String typeCode) {
        this.typeName = typeName;
        this.typeCode = typeCode;
    }
}
