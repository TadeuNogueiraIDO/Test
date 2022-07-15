package br.com.idolink.api.service.email;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.idolink.api.execption.BaseFullException;

@Service
public class SendEmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${idolink.email.remetente}")
	private String sender;

	public void sendMail(String email, String emailSubject, String emailText) {
		try {
			MimeMessage message = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setSubject(emailSubject);
			helper.setText(emailText, true);
			helper.setFrom(sender);
			helper.setTo(email);

			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseFullException(HttpStatus.BAD_REQUEST,"E-mail",  "api.error.send.email.service.conflict");
		}
	}
	
	/**
	 * Envio de email a partir de um html
	 * @param html - O nome do html contido no classPath(/resources/html)
	 * @return
	 */
	public void sendEmail(String email, String emailSubject, String html) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String body = "";
		try {
			baos.write(new ClassPathResource("html/"+html).getInputStream().readAllBytes());
			body = baos.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sendMail(email, emailSubject, body);
	}
	
	/**
	 * Envio de email a partir de um html com placeholders
	 * @param html - O nome do html contido no classPath(/resources/html)
	 * @param params - HashMap com placeholders e valores
	 * @return
	 */
	public void sendEmail(String email, String emailSubject, String html, HashMap<String, String> params) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String body = "";
		try {
			baos.write(new ClassPathResource("html/"+html).getInputStream().readAllBytes());
			body = baos.toString();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				body = body.replace(entry.getKey(), entry.getValue());
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sendMail(email, emailSubject, body);
	}
	

}
