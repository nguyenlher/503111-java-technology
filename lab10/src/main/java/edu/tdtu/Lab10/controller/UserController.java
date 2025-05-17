package edu.tdtu.Lab10.controller;

import edu.tdtu.Lab10.dto.request.UserRequest;
import edu.tdtu.Lab10.dto.response.ApiResponse;
import edu.tdtu.Lab10.dto.response.AuthenticationResponse;
import edu.tdtu.Lab10.dto.response.UserResponse;
import edu.tdtu.Lab10.entity.User;
import edu.tdtu.Lab10.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping("/register")
    ApiResponse<UserResponse> create(@RequestBody UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.create(request))
                .build();
    }

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody UserRequest request) throws Exception {
        var result = userService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }
}

