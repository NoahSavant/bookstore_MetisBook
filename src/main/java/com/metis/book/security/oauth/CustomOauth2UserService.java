package com.metis.book.security.oauth;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.metis.book.exception.OAuth2AuthenticationProcessingException;
import com.metis.book.model.Image;
import com.metis.book.model.user.AuthProvider;
import com.metis.book.model.user.User;
import com.metis.book.repository.ImageRepository;
import com.metis.book.security.UserPrincipal;
import com.metis.book.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	IUserService userService;
	
	@Autowired
	ImageRepository imageRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) {
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		try {
			return processOAuth2User(userRequest,oauth2User);
		}
		catch(AuthenticationException e) {
			throw e;
		}catch(Exception e) {
			throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
		}		
		
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest,
			OAuth2User oAuth2User) throws IOException {
		String email = oAuth2User.getAttribute("email");
		log.info(oAuth2User.getAttributes().toString());

		User user = userService.findByEmail(email);
		if (Objects.isNull(user)) {
			// register new user
			user = registerNewUser(userRequest, oAuth2User);
		} else {
			// update user
			AuthProvider provider = AuthProvider
					.valueOf(userRequest.getClientRegistration().getRegistrationId());
			
			// if user link account to oauth account
			if(Objects.isNull(user.getAuthProvider())) {
				user.setAuthProvider(provider);
			}
			if (!user.getAuthProvider().equals(provider)) {
				throw new OAuth2AuthenticationProcessingException(
						"Có vẻ như bạn đã đăng ký bằng tài khoản " + user.getAuthProvider() +" với email: "+user.getEmail() +". Vui lòng sử dụng tài khoản "
								+ user.getAuthProvider() + " để đăng nhập.");

			}
			user = updateUser(user, oAuth2User);
		}

		return UserPrincipal.create(user, oAuth2User.getAttributes());
	}
	
	private User registerNewUser(OAuth2UserRequest userRequest, OAuth2User oauth2User){

		Image imageThumbnail = new Image();
		imageThumbnail.setThumbnailName("avtThumbnail.jpg");
		imageThumbnail.setThumbnailURL("E:\\HCMUTE\\School_Project\\bookstore_MetisBook\\uploads\\avtThumbnail.jpg");
		imageRepository.save(imageThumbnail);
		
		User user = new User();

		AuthProvider provider = AuthProvider.valueOf(userRequest.getClientRegistration().getRegistrationId());
		user.setAuthProvider(provider);
		user.setFirstName(oauth2User.getAttribute("given_name"));
		user.setLastName(oauth2User.getAttribute("family_name"));
		user.setEmail(oauth2User.getAttribute("email"));
		user.setImage(imageThumbnail);
		user.setGender(1);
		user.setEnabled(true);
		user.setGender(1);
		return userService.createNewUserOAuth2(user);
	}

	private User updateUser(User existingUser, OAuth2User oauth2User) {
		existingUser.setFirstName(oauth2User.getAttribute("given_name"));
		existingUser.setLastName(oauth2User.getAttribute("family_name"));
		// set image url in future

		return userService.updateUser(existingUser);
	}

	@ExceptionHandler(OAuth2AuthenticationProcessingException.class)
	public OAuth2User viewLoginPageWithError(
			OAuth2AuthenticationProcessingException e) {
		return null;
//		request.getSession().setAttribute("errorMessage", e.getMessage());
//		response.sendRedirect("/auth/login?error=true");
	}
}
