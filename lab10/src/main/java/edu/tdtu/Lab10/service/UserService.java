package edu.tdtu.Lab10.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import edu.tdtu.Lab10.configuration.SecurityConfig;
import edu.tdtu.Lab10.dto.request.UserRequest;
import edu.tdtu.Lab10.dto.response.AuthenticationResponse;
import edu.tdtu.Lab10.dto.response.UserResponse;
import edu.tdtu.Lab10.entity.User;
import edu.tdtu.Lab10.execption.AppException;
import edu.tdtu.Lab10.execption.ErrorCode;
import edu.tdtu.Lab10.mapper.UserMapper;
import edu.tdtu.Lab10.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;
    PasswordEncoder passwordEncoder;

    public UserResponse create(UserRequest request) {
        if(userRepository.existsByName(request.getName()))  throw new AppException(ErrorCode.ENTITY_EXISTS);
        var user = userMapper.createUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public AuthenticationResponse authenticate(UserRequest request) throws Exception {
        var user = userRepository.findByName(request.getName())
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_EXISTS));
        boolean authenticated = passwordEncoder
                .matches(request.getPassword(), user.getPassword());
        String token = authenticated ? generateToken(user) : "Wrong Password";
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(authenticated)
                .build();
    }

    private String generateToken(User user) throws Exception {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet =
                new JWTClaimsSet.Builder()
                        .subject(user.getName())
                        .issuer("lhn.com")
                        .issueTime(new Date())
                        .expirationTime(new Date(
                                Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                        ))
                        .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (AppException e) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
    }
}
