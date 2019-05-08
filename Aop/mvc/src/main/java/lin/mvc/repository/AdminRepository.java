package lin.mvc.repository;

import lin.mvc.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    public Admin findOneByNameAndPassword(String name, String password);

}
