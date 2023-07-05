package com.example.util;


import com.example.config.security.CustomUserDetails;
import com.example.enums.CompanyRole;
import com.example.enums.ProfileRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityUtil {

    public static ProfileRole getProfileRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getProfileEntity().getRole();
    }

//    public static CompanyRole getCompanyRole() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        return userDetails.getProfileEntity().getRole();
//    }
}
