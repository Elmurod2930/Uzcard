package com.example.config.security;

import com.example.entity.ProfileEntity;
import com.example.enums.GeneralStatus;
import com.example.enums.ProfileRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Getter
public class CustomUserDetails implements UserDetails {

    private ProfileEntity profileEntity;

    public CustomUserDetails(ProfileEntity profileEntity) {
        this.profileEntity = profileEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ProfileRole role = profileEntity.getRole();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.name());
        return List.of(simpleGrantedAuthority);
        //  return List.of(new SimpleGrantedAuthority(profileEntity.getRole().name()));
    }

    @Override
    public String getPassword() {
        return profileEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return profileEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return profileEntity.getStatus().equals(GeneralStatus.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return profileEntity.getVisible();
    }


}
