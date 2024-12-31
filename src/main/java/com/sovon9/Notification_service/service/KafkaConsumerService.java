package com.sovon9.Notification_service.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sovon9.Notification_service.dto.GuestCommInfo;

@Service
public class KafkaConsumerService
{
	Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);
	
	@Autowired
	private EmailService emailService;
	
	@KafkaListener(topics = "PMS2", groupId = "email") // consuming from PMS2 topic
	public void consume(GuestCommInfo commInfo)
	{
		try
		{
			System.out.println("Consuming message : " + commInfo.getEmail());
			CompletableFuture.runAsync(() -> sendEmailBasedOnAction(commInfo));
		}
		catch (Exception e) {
			LOGGER.error("Consumer Error while consuming messge from Topic");
		}	
	}
	
	/**
	 * send email notification to guest
	 * @param commInfo
	 */
	public void sendEmailBasedOnAction(GuestCommInfo commInfo)
	{
		switch(commInfo.getAction())
		{
			case "RESCREATE":
				emailService.sendResCreateHTMLEmail(commInfo);
				break;
			case "RESMODIFY":
				emailService.sendResCreateHTMLEmail(commInfo);
				break;
			case "CHECKIN":
				emailService.sendCheckINHTMLEmail(commInfo);
				break;
			case "CHECKOUT":
				emailService.sendCheckOUTHTMLEmail(commInfo);
				break;
			case "GUESTINFO":
				emailService.sendGuestInfoUpdateHTMLEmail(commInfo);
				break;
			default:
				
		}
	}
}
