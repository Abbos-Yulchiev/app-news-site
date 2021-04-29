package uz.pdp.appnewssite.service;

import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.RegisterDTO;
import uz.pdp.appnewssite.repository.RoleRepository;
import uz.pdp.appnewssite.repository.UserRepository;

@Service
public class AuthService {

    final UserRepository userRepository;
    final RoleRepository roleRepository;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ApiResponse registerUser(RegisterDTO registerDTO) {

        boolean exists = userRepository.existsByUsername(registerDTO.getUsername());
        if (exists)
            return new ApiResponse("User Name Already exist!", false);
        /*User user = new User(
                registerDTO.getFullName(),
                registerDTO.getUsername(),
                null,

                )*/
        return null;
    }
}
