package com.metis.book.serviceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.metis.book.model.user.User;
import com.metis.book.repository.UserRepository;
import com.metis.book.security.UserPrincipal;
import com.metis.book.utils.AppConstant;

@Service
public class CustomUserServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email){
		User user = userRepository.findByEmail(email);
		if(Objects.isNull(user)) {
			throw new UsernameNotFoundException(AppConstant.USER_NOT_FOUND+email);
		}
		return UserPrincipal.create(user);

		/*
		 * // khai -> database -> tìm được -> tạo userDetail -> trả về UserDetailService
		 * -> // 123 -> userDetailSerice -> kiểm tra -> nếu 123 = pass trong database
		 * (giải mã) -> đăng nhập -> lỗi // đăng nhập thành công -> tạo một
		 * http request -> stateless -> duy trì đăng nhập
		 * UserPrincipal ( đã authenticated ) trong spring container
		 * 
		 */	
		
	}

}
