package uz.pdp.appnewssite.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.aop.CheckPermission;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.RegisterDTO;
import uz.pdp.appnewssite.payload.RoleDTO;
import uz.pdp.appnewssite.service.RoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/role")
public class RoleController {

    final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize(value = "hasAnyAuthority('ADD_ROLE')")
    @PostMapping
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDTO roleDTO) {

        ApiResponse apiResponse = roleService.addRole(roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    // Editing role
    //    @PreAuthorize(value = "hasAnyAuthority('EDIT_ROLE')")
    @CheckPermission(values = "EDIT_ROLE")
    @PutMapping(value = "/{roleId}")
    public HttpEntity<?> editRole(@PathVariable Long roleId,
                                  @Valid @RequestBody RoleDTO roleDTO) {

        ApiResponse apiResponse = roleService.editRole(roleId, roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    // Deleting role
    @PreAuthorize(value = "hasAnyAuthority('DELETE_ROLE')")
    @DeleteMapping(value = "/{roleId}")
    public HttpEntity<?> deleteRole(@PathVariable Long roleId) {

        ApiResponse apiResponse = roleService.deleteRole(roleId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    // View Role List
    @PreAuthorize(value = "hasAnyAuthority('VIEW_ROLE')")
    @GetMapping
    public HttpEntity<?> getRoleList() {

        List<Role> roleList = roleService.getRoleList();
        return ResponseEntity.ok(roleList);
    }
}
