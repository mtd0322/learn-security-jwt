package org.alan.service;

import java.util.Date;

import org.alan.entity.SysUser;
import org.alan.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

public class JwtUserService implements UserDetailsService{
	
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SysUserRepository sysUserRepository;
	
	public JwtUserService() {
		this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	public UserDetails getUserLoginInfo(String username) {
		String salt = "123456ef";
    	UserDetails user = loadUserByUsername(username);
    	return User.builder().username(user.getUsername()).password(salt).authorities(user.getAuthorities()).build();
	}
	
	public String saveUserLoginInfo(UserDetails user) {
		String salt = "123456ef";
		Algorithm algorithm = Algorithm.HMAC256(salt);
		Date date = new Date(System.currentTimeMillis()+3600*1000);
        return JWT.create()
        		.withSubject(user.getUsername())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		return User.builder().username("alan").password(passwordEncoder.encode("123456")).roles("USER").build();
		SysUser sysUser = sysUserRepository.findSysUserByUsername(username);
		return sysUser;
	}
	
	public void createUser(String username, String password) {
		String encryptPwd = passwordEncoder.encode(password);
	}
	
	public void deleteUserLoginInfo(String username) {
	}
}
