package uz.pdp.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewssite.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
}
