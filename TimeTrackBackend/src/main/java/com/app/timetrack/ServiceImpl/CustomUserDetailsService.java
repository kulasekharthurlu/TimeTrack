package com.app.timetrack.ServiceImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.timetrack.IService.IEmployeeService;
import com.app.timetrack.entity.Employee;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private IEmployeeService empService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee user = empService.findByUserEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or Password");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmpEmail(), user.getEmpPassword(),
				getAuthority(user));
	}

	private Collection<? extends GrantedAuthority> getAuthority(Employee user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}

}
