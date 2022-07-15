//package br.com.idolink.api.controller;
//
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class EmailController {
//
//	@Autowired
//	private JavaMailSender mailSender;
//	
//	@PostMapping(path = "/email-send")
//	public String sendMail() {
//		try {
//			
//			MimeMessage message = mailSender.createMimeMessage();
//
//			MimeMessageHelper helper = new MimeMessageHelper(message);
//			helper.setSubject("Idolink teste");
//			helper.setText("<html>\r\n"
//					+ "<body>\r\n"
//					+ "	<h1 style=\"text-align:center; color: green; font-size: 30px\"> Testando o envio de E-mail</h1>\r\n"
//													 
//					+"<center><img src=\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7HhjZMjU22AIhhjyeqgZvY2KUnTGON9yoe5ddJIUqQeaYfxXryDZrj9e4dANLVRPUDuo&usqp=CAU\"style=\"width:30%\"></center>"
//					
//					+ "\r\n"
//					
//					
//					+ "	<p><center><font size=\"3\" face=\"Verdana\">Testando 123 !</font></center></p>\r\n"
//					+ "</body>\r\n"
//					+ "</html>", true);
//			helper.setFrom("no-reply@idolink.bio");
//			helper.setTo("brawlhalacor@gmail.com");
//			mailSender.send(message);
//
//			return "Email enviado com sucesso!";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "Erro ao enviar email.";
//		}
//	}
//}