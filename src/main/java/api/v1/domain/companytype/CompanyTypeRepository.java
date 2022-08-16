package api.v1.domain.companytype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTypeRepository extends JpaRepository<CompanyType,Long> {

    CompanyType findByTypeName(String typeName);
}
