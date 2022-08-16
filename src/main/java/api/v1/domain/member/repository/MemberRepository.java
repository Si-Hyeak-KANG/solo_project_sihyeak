package api.v1.domain.member.repository;

import api.v1.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query(value = "SELECT m FROM Member m WHERE m.companyLocation = :locationId")
    List<Member> findAllByCompanyLocationContaining(Long locationId);

    @Query(value = "SELECT m FROM Member m WHERE m.companyType = :typeId")
    List<Member> findAllByCompanyTypeContaining(Long typeId);
}
