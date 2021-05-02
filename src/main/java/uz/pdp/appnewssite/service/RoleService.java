package uz.pdp.appnewssite.service;

import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.RoleDTO;
import uz.pdp.appnewssite.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ApiResponse addRole(RoleDTO roleDTO) {

        if (roleRepository.existsByName(roleDTO.getName()))
            return new ApiResponse("This role already exist!", false);

        Role role = new Role(
                roleDTO.getName(),
                roleDTO.getPermissionList(),
                roleDTO.getDescription()
        );
        roleRepository.save(role);
        return new ApiResponse("New Role saved", true);
    }

    public ApiResponse editRole(Long roleId, RoleDTO roleDTO) {

        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (!optionalRole.isPresent())
            return new ApiResponse("Invalid Role Id!", false);

        boolean exists = roleRepository.existsByNameAndIdNot(roleDTO.getName(), roleId);
        if (exists)
            return new ApiResponse("This role already exist!", false);


        Role role = optionalRole.get();
        role.setName(roleDTO.getName());
        role.setPermissionList(roleDTO.getPermissionList());
        role.setDescription(role.getDescription());
        roleRepository.save(role);
        return new ApiResponse("Role successfully edited!", true);
    }

    public ApiResponse deleteRole(Long roleId) {

        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (!optionalRole.isPresent())
            return new ApiResponse("Invalid Role Id!", false);
        roleRepository.deleteById(roleId);
        return new ApiResponse("Role successfully deleted!", true);
    }

    public List<Role> getRoleList() {
        return roleRepository.findAll();
    }
}
