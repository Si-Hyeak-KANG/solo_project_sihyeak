package api.v1.domain.member.repository;

import api.v1.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {


    List<Member> findAllByCompanyLocationContaining(Long locationId);

    List<Member> findAllByCompanyTypeContaining(Long typeId);
}
