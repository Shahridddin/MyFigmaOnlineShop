package uz.pdp.myappfigma.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.myappfigma.dto.BaseResponse;
import uz.pdp.myappfigma.dto.auth.AuthUserCreateDto;
import uz.pdp.myappfigma.dto.auth.AuthUserUpdateDto;
import uz.pdp.myappfigma.dto.auth.UserSessionData;
import uz.pdp.myappfigma.entity.AuthUser;
import uz.pdp.myappfigma.repository.UserCreatRepository;
import uz.pdp.myappfigma.service.EmailService;
import uz.pdp.myappfigma.service.impl.UserCreateService;

import java.util.Optional;

@RestController
@RequestMapping("/auth/register")
public class UserRegisterController {


    private final UserCreateService userCreateService;
    private final EmailService emailService;

    public UserRegisterController(UserCreateService userCreateService, EmailService emailService, UserCreatRepository userCreatRepository) {
        this.userCreateService = userCreateService;
        this.emailService = emailService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<Long> create(@Valid @RequestBody AuthUserCreateDto dto) {

        Long id = userCreateService.createUser(dto);
        return new BaseResponse<>(id);
    }


    @PostMapping("/update/{id}")
    public BaseResponse<Long> update(@Valid @PathVariable Long id, @RequestBody AuthUserUpdateDto dto) {
        Long newId = userCreateService.update(id, dto);
        return new BaseResponse<>(newId);
    }

    @GetMapping("/me")
    public BaseResponse<UserSessionData> getMe() {
        UserSessionData userSessionData = userCreateService.getMe();
        return new BaseResponse<>(userSessionData);
    }

    @PostMapping("/send/{id}")
    public BaseResponse<String> sendPassword(@Valid @RequestParam Long id) {

        Optional<AuthUser> currentUser = userCreateService.getById(id);
        String email = currentUser.get().getEmail();
        String code = emailService.sendVerificationEmail(email);
        return new BaseResponse<>(code);
    }

    @PostMapping("/password/reset/{id}")
    public BaseResponse<String> resetPassword(
            @PathVariable Long id,
            @RequestParam String password,
            @RequestParam String code) {

        AuthUser currentUser = userCreateService.getById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        if (!emailService.isCodeValid(code)) {
            return new BaseResponse<>("Verification code is invalid or expired");
        }

        String encodedPassword = userCreateService.getPasswordEncoder().encode(password);
        currentUser.setPassword(encodedPassword);

        userCreateService.updateUser(currentUser);

        return new BaseResponse<>("Password has been reset successfully");
    }


}
