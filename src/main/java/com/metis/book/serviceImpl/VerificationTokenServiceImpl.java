package com.metis.book.serviceImpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.metis.book.model.VerificationToken;
import com.metis.book.model.user.User;
import com.metis.book.repository.PasswordResetTokenRepository;
import com.metis.book.repository.VerificationTokenRepository;
import com.metis.book.service.IVerificationTokenService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VerificationTokenServiceImpl implements IVerificationTokenService {

	@Autowired
	VerificationTokenRepository tokenRepository;

	@Autowired
	PasswordResetTokenRepository passwordTokenRepository;
	
	@Autowired
	private MessageSource messages;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public VerificationToken getVerificationToken(String token) {
		return tokenRepository.findByToken(token);
	}

	@Override
	public String sendVerificationToken(HttpServletRequest request, VerificationToken newToken,User user) {
		try {
            final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            final String confirmationUrl = appUrl + "/auth/register-confirm?token=" + newToken.getToken();
            final SimpleMailMessage email = constructVerificationTokenEmail(confirmationUrl, user);
            mailSender.send(email);
        } catch (final MailAuthenticationException e) {
            log.debug("MailAuthenticationException", e);
            return "redirect:/emailError";
        } catch (final Exception e) {
            return "redirect:/login";
        }
		return null;
		
	}
	
   private SimpleMailMessage constructVerificationTokenEmail(final String resetURL, final User user) {
        
        final String subject = "Xác nhận đăng ký";
		final String message = messages.getMessage("message.regSuccLink", null,
				"Xin chào "+user.getFirstName()+" "+user.getLastName()+","
				+"\n\nChúc mừng bạn đã đăng ký tài khoản thành công. Vui lòng nhấn vào link bên dưới để xác thực tài khoản.",null);
		final String endingMessage = "Thân chào, \nMetis's book store";
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject(subject);
		email.setText(message + " \r\n" + resetURL +"\n\n\n\n"+endingMessage);
        return email;
    }

	@Override
	public VerificationToken getTokenByUser(User savedUser) {		
		return tokenRepository.getTokenByUser(savedUser);
	}

	
	

}
