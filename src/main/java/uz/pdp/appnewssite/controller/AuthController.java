package uz.pdp.appnewssite.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.RegisterDTO;
import uz.pdp.appnewssite.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {

        ApiResponse apiResponse = authService.registerUser(registerDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
