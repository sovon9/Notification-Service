package com.sovon9.Notification_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sovon9.Notification_service.dto.GuestCommInfo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService
{
	Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendResCreateHTMLEmail(GuestCommInfo guestCommInfo) {
	    try {
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	        
	        helper.setTo(guestCommInfo.getEmail());
	        helper.setSubject("Reservation Created Successfully!");
	        
	        String emailContent = buildHtmlEmailContent(guestCommInfo);
	        helper.setText(emailContent, true); // true enables HTML content
	        
	        javaMailSender.send(mimeMessage);
	        LOGGER.info("Mail successfully sent to: {}", guestCommInfo.getEmail());
	    } catch (Exception e) {
	        LOGGER.error("Error occurred while sending mail: sendResCreateHTMLEmail()", e);
	    }
	}
	
	public void sendResModifyHTMLEmail(GuestCommInfo guestCommInfo) {
	    try {
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	        
	        helper.setTo(guestCommInfo.getEmail());
	        helper.setSubject("Reservation Modified Successfully!");
	        
	        String emailContent = buildHtmlEmailContent(guestCommInfo);
	        helper.setText(emailContent, true); // true enables HTML content
	        
	        javaMailSender.send(mimeMessage);
	        LOGGER.info("Mail successfully sent to: {}", guestCommInfo.getEmail());
	    } catch (Exception e) {
	        LOGGER.error("Error occurred while sending mail: sendResCreateHTMLEmail()", e);
	    }
	}
	
	public void sendCheckINHTMLEmail(GuestCommInfo guestCommInfo) {
		try
		{
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setTo(guestCommInfo.getEmail());
			helper.setSubject("Reservation Checked In Successfully!");

			String emailContent = buildHtmlEmailContent(guestCommInfo);
			helper.setText(emailContent, true); // true enables HTML content

			javaMailSender.send(mimeMessage);
			LOGGER.info("Mail successfully sent to: {}", guestCommInfo.getEmail());
		}
		catch (Exception e)
		{
			LOGGER.error("Error occurred while sending mail: sendCheckINHTMLEmail()", e);
		}
	}
	
	public void sendGuestInfoUpdateHTMLEmail(GuestCommInfo commInfo)
	{
		try
		{
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
			
			messageHelper.setTo(commInfo.getEmail());
			messageHelper.setSubject("Reservation GuestID Updated Successfully!");
			
			messageHelper.setText(buildHtmlEmailContent(commInfo),true);
			javaMailSender.send(mimeMessage);
			LOGGER.info("Mail successfully sent to: {}", commInfo.getEmail());
		}
		catch (MessagingException e)
		{
			LOGGER.error("Error occurred while sending mail: sendGuestInfoUpdateHTMLEmail()", e);
		}
	}
	
	public void sendCheckOUTHTMLEmail(GuestCommInfo guestCommInfo)
	{
		try
		{
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setTo(guestCommInfo.getEmail());
			helper.setSubject("Reservation Checked In Successfully!");

			String emailContent = buildHtmlEmailContent(guestCommInfo);
			helper.setText(emailContent, true); // true enables HTML content

			javaMailSender.send(mimeMessage);
			LOGGER.info("Mail successfully sent to: {}", guestCommInfo.getEmail());
		}
		catch (Exception e)
		{
			LOGGER.error("Error occurred while sending mail: sendCheckOUTHTMLEmail()", e);
		}
	}

	private String buildHtmlEmailContent(GuestCommInfo guestCommInfo) 
	{
		String mailContent="";
		switch(guestCommInfo.getAction())
		{
			case "RESCREATE":
				mailContent = "<html>" +
		           "<body>" +
		           "<h2>Hi " + guestCommInfo.getFirstName() + ",</h2>" +
		           "<p>We are pleased to inform you that your reservation has been successfully created.</p>" +
		           "<p><strong>Reservation ID:</strong> " + guestCommInfo.getResID() + "</p>" +
		           "<p>Thank you for choosing our service. We look forward to hosting you.</p>" +
		           "<br>" +
		           "<p>Best Regards,</p>" +
		           "<p><strong>PMS</strong></p>" +
		           "</body>" +
		           "</html>";
					break;
			case "RESMODIFY":
				mailContent = "<html>" +
		           "<body>" +
		           "<h2>Hi " + guestCommInfo.getFirstName() + ",</h2>" +
		           "<p>We are pleased to inform you that your reservation has been successfully modified.</p>" +
		           "<p><strong>Reservation ID:</strong> " + guestCommInfo.getResID() + "</p>" +
		           "<p><strong>Room Number:</strong> " + guestCommInfo.getRoomNum() + "</p>" +
		           "<p>Thank you for choosing our service. We look forward to hosting you.</p>" +
		           "<br>" +
		           "<p>Best Regards,</p>" +
		           "<p><strong>PMS</strong></p>" +
		           "</body>" +
		           "</html>";
				   break;
			case "CHECKIN":
				mailContent = "<html>" +
		           "<body>" +
		           "<h2>Hi " + guestCommInfo.getFirstName() + ",</h2>" +
		           "<p>We are pleased to inform you that your reservation has been successfully checked in.</p>" +
		           "<p><strong>Reservation ID:</strong> " + guestCommInfo.getResID() + "</p>" +
		           "<p>Thank you for choosing our service. We look forward to hosting you.</p>" +
		           "<br>" +
		           "<p>Best Regards,</p>" +
		           "<p><strong>PMS</strong></p>" +
		           "</body>" +
		           "</html>";
				   break;
			case "CHECKOUT":
				mailContent = "<html>" +
		           "<body>" +
		           "<h2>Hi " + guestCommInfo.getFirstName() + ",</h2>" +
		           "<p>We are pleased to inform you that your reservation has been successfully checked out.</p>" +
		           "<p><strong>Reservation ID:</strong> " + guestCommInfo.getResID() + "</p>" +
		           "<p>Thank you for choosing our service. We look forward to hosting you.</p>" +
		           "<br>" +
		           "<p>Best Regards,</p>" +
		           "<p><strong>PMS</strong></p>" +
		           "</body>" +
		           "</html>";
				   break;
			case "GUESTINFO":
				mailContent = "<html>" +
		           "<body>" +
		           "<h2>Hi " + guestCommInfo.getFirstName() + ",</h2>" +
		           "<p>We are pleased to inform you that your details has been successfully updated.</p>" +
		           "<p><strong>Guest ID:</strong> " + guestCommInfo.getGuestID() + "</p>" +
		           "<p>Thank you for choosing our service. We look forward to hosting you.</p>" +
		           "<br>" +
		           "<p>Best Regards,</p>" +
		           "<p><strong>PMS</strong></p>" +
		           "</body>" +
		           "</html>";
				   break;
			default:
				return null;
		}
		return mailContent;
	}
	
	/***************************************** Original Methods *********************************************/
	
	public void sendEmail(String to, String subject, String text)
	{
		try
		{
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(to);
			mailMessage.setSubject(subject);
			mailMessage.setText(text);
			javaMailSender.send(mailMessage);
			LOGGER.info("Mail sucessfully sent to : "+to);
		}
		catch (Exception e)
		{
			LOGGER.error("Error occured during sending mail : sendEmail() "+e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param guestCommInfo
	 */
	public void sendResCreateEmail(GuestCommInfo guestCommInfo)
	{
		try
		{
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(guestCommInfo.getEmail());
			mailMessage.setSubject("Resrvation Created Sucessfully!");
			mailMessage.setText("Hi "+guestCommInfo.getFirstName()+",\n"+"Reservation Sucessfully Created For RESID:"+guestCommInfo.getResID());
			javaMailSender.send(mailMessage);
			LOGGER.info("Mail sucessfully sent to : "+guestCommInfo.getEmail());
		}
		catch (Exception e)
		{
			LOGGER.error("Error occured during sending mail : sendResCreateEmail() "+e.getMessage());
		}
	}
	
	public void sendGuestInfoUpdateEmail(GuestCommInfo guestCommInfo)
	{
		try
		{
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(guestCommInfo.getEmail());
			mailMessage.setSubject("Guest Information Updated Sucessfully!");
			mailMessage.setText("Hi "+guestCommInfo.getFirstName()+",\n"+"Guest Information Updated Sucessfully For GuestID:"+guestCommInfo.getGuestID());
			javaMailSender.send(mailMessage);
			LOGGER.info("Mail sucessfully sent to : "+guestCommInfo.getEmail());
		}
		catch (Exception e)
		{
			LOGGER.error("Error occured during sending mail : sendGuestInfoUpdateEmail() "+e.getMessage());
		}
	}

}
