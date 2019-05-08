package lin.mvc.repository;

import lin.mvc.entity.InsertLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsertLogRepository extends JpaRepository<InsertLog, Long> {
}
