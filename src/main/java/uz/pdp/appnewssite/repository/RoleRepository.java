package uz.pdp.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewssite.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
