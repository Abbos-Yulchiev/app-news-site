package uz.pdp.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //User name checking
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    boolean existsByUsernameAndIdNot(String username, Long id);


}
