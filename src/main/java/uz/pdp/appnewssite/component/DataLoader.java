package uz.pdp.appnewssite.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.entity.enums.Permission;
import uz.pdp.appnewssite.repository.RoleRepository;
import uz.pdp.appnewssite.repository.UserRepository;
import uz.pdp.appnewssite.utils.AppConstants;

import java.util.Arrays;

import static uz.pdp.appnewssite.entity.enums.Permission.*;

@Component
public class DataLoader implements CommandLineRunner {

    final UserRepository userRepository;
    final RoleRepository roleRepository;

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {

        if (initialMode.equals("always")) {
            Permission[] permissionList = Permission.values();
            Role admin = roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    Arrays.asList(permissionList)
            ));
            Role user = roleRepository.save(new Role(
                    AppConstants.USER,
                    Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT)
            ));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    "admin123",
                    admin,
                    true
            ));
            userRepository.save(new User(
                    "User",
                    "user",
                    "user123",
                    user,
                    true
            ));
        }
    }
}
