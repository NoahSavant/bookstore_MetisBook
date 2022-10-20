package com.metis.book.serviceImpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.metis.book.model.PasswordResetToken;
import com.metis.book.model.user.User;
import com.metis.book.repository.PasswordResetTokenRepository;
import com.metis.book.service.IPasswordResetTokenService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PasswordResetTokenServiceImpl implements IPasswordResetTokenService {

	@Autowired
	private MessageSource messages;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	PasswordResetTokenRepository passwordTokenRepository;

	@Override
	public PasswordResetToken findByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendResetPasswordToken(HttpServletRequest request, PasswordResetToken newToken, User user) {
		try {
			final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
			final String resetPasswordUrl = appUrl + "/auth/update-password?token=" + newToken.getToken();
			final SimpleMailMessage email = constructResetTokenEmail(resetPasswordUrl, user);
			mailSender.send(email);
		} catch (final MailAuthenticationException e) {
			log.debug("MailAuthenticationException", e);
			return "redirect:/emailError";
		} catch (final Exception e) {
			return "redirect:/login";
		}
		return null;
	}

	@Override
	public PasswordResetToken getPasswordTokenByUser(User user) {
		return passwordTokenRepository.findByUser(user);
	}

	private SimpleMailMessage constructResetTokenEmail(final String resetURL, final User user) {

		final String subject = "Đặt lại mật khẩu";
		final String message = messages.getMessage("message.regSuccLink", null, "Xin chào " + user.getFirstName() + " "
				+ user.getLastName() + "," + "\n\nVui lòng nhấn vào link bên dưới để tiến hành đặt lại mật khẩu.",
				null);
		final String endingMessage = "Thân chào, \nMetis's book store";
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject(subject);
		email.setText(message + " \r\n" + resetURL + "\n\n\n\n" + endingMessage);
		return email;
	}
}
