package com.example.service;

import com.example.config.SecurityConfig;
import com.example.dto.auth.AuthDTO;
import com.example.dto.auth.AuthResponseDTO;
import com.example.dto.auth.RegistrationDTO;
import com.example.dto.auth.RegistrationResponseDTO;
import com.example.dto.jwt.JwtDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.GeneralStatus;
import com.example.enums.ProfileRole;
import com.example.exps.AppBadRequestException;
import com.example.exps.ItemNotFoundException;
import com.example.repository.ProfileRepository;

import com.example.util.JwtUtil;
import com.example.util.MD5Util;
import com.example.util.SpringSecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final ProfileRepository profileRepository;
    private final MailSenderService mailSenderService;

    public RegistrationResponseDTO registration(RegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByUsername(dto.getUsername());

        if (optional.isPresent()) {
            throw new ItemNotFoundException("Email/phone already exists");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setUsername(dto.getUsername());
        entity.setPassword(MD5Util.getMd5Hash(dto.getPassword()));
        entity.setSurname(dto.getSurname());
        entity.setName(dto.getName());
        entity.setStatus(GeneralStatus.REGISTER);
        entity.setRole(ProfileRole.ROLE_USER);

        mailSenderService.sendRegistrationEmailMime(dto.getUsername());

        profileRepository.save(entity);
        String s = "Verification link was send to email: " + dto.getUsername();
        return new RegistrationResponseDTO(s);
    }

    public AuthResponseDTO login(AuthDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndPasswordAndVisible(
                dto.getUsername(),
                MD5Util.getMd5Hash(dto.getPassword()),
                true);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Email or password incorrect");
        }
        ProfileEntity entity = optional.get();
        if (!entity.getStatus().equals(GeneralStatus.ACTIVE)) {
            throw new AppBadRequestException("Wrong status");
        }
        AuthResponseDTO responseDTO = new AuthResponseDTO();
        responseDTO.setName(entity.getName());
        responseDTO.setSurname(entity.getSurname());
        responseDTO.setRole(entity.getRole());
        responseDTO.setJwt(JwtUtil.encode(entity.getUsername(), entity.getRole()));
        return responseDTO;
    }

    public RegistrationResponseDTO emailVerification(String jwt) {
        String email = JwtUtil.decodeEmailVerification(jwt);
        Optional<ProfileEntity> optional = profileRepository.findByUsername(email);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Email not found.");
        }
        ProfileEntity entity = optional.get();
        if (!entity.getStatus().equals(GeneralStatus.REGISTER)) {
            throw new AppBadRequestException("Wrong status");
        }
        entity.setStatus(GeneralStatus.ACTIVE);
        profileRepository.save(entity);
        return new RegistrationResponseDTO("Registration Done");
    }
}
