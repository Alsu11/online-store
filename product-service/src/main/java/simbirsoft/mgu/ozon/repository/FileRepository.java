package simbirsoft.mgu.ozon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simbirsoft.mgu.ozon.domain.Files;

@Repository
public interface FileRepository extends JpaRepository<Files, Integer> {
}
