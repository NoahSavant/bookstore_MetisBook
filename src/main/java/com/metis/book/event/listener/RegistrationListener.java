package com.metis.book.event.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.metis.book.event.OnRegistrationCompleteEvent;
import com.metis.book.model.user.User;
import com.metis.book.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messages;

	@Autowired
	private JavaMailSender mailSender;


	@Override
	public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}

	private void confirmRegistration(final OnRegistrationCompleteEvent event) {
		log.info("In onApplicationEvent ");
		final User user = event.getUser();
		final String token = UUID.randomUUID().toString();
		userService.createVerificationTokenForUser(user, token);

		final SimpleMailMessage email = constructEmailMessage(event, user, token);
		mailSender.send(email);
		log.info("Done onApplicationEvent ");
	}


	private SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final User user,
			final String token) {
		final String recipientAddress = user.getEmail();
		final String subject = "Xác nhận đăng ký";
		final String confirmationUrl = event.getAppUrl() + "/auth/registrationConfirm?token=" + token;
		final String message = messages.getMessage("message.regSuccLink", null,
				"Chúc mừng bạn đã đăng ký tài khoản thành công. Vui lòng nhấn vào link bên dưới để xác thực tài khoản.",
				event.getLocale());
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText(message + " \r\n" + confirmationUrl);
		return email;
	}

}
