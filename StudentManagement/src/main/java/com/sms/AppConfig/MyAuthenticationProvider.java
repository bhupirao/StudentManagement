package com.sms.AppConfig;
import com.sms.model.Student;
import com.sms.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName=authentication.getName();
        String userPassword=authentication.getCredentials().toString();

        Optional<Student> optional=studentRepo.findByUniqueStudentCode(userName);
        if(optional.isEmpty()){
            throw new BadCredentialsException("Invalid Credentials");
        }
        Student st=optional.get();
        if(passwordEncoder.matches(userPassword,st.getDateOfBirth())){
            List<GrantedAuthority> authorities=new ArrayList<>();
            return new UsernamePasswordAuthenticationToken(userName,userPassword,authorities);
        }else{
            throw new BadCredentialsException("Invalid DOP");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
