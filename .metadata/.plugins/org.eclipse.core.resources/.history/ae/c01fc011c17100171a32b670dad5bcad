package com.example.demo.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.windowsazure.Configuration;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.services.servicebus.ServiceBusConfiguration;
import com.microsoft.windowsazure.services.servicebus.ServiceBusContract;
import com.microsoft.windowsazure.services.servicebus.ServiceBusService;
import com.microsoft.windowsazure.services.servicebus.models.BrokeredMessage;
import com.microsoft.windowsazure.services.servicebus.models.GetSubscriptionResult;
import com.microsoft.windowsazure.services.servicebus.models.ReceiveMessageOptions;
import com.microsoft.windowsazure.services.servicebus.models.ReceiveMode;
import com.microsoft.windowsazure.services.servicebus.models.SubscriptionInfo;

@RestController
public class AzureServiceBusTopicController {
	/*
	 * Endpoint=sb://springbootdemo.servicebus.windows.net/;
	 * SharedAccessKeyName=test;
	 * SharedAccessKey=q/xUcLnu9n+w6EeT+e9njk9F0Nbw4BuOJZHdtMvXjvo=;
	 * EntityPath=testtopic
	 * 
	 * Endpoint=sb://springbootdemo.servicebus.windows.net/;
	 * SharedAccessKeyName=RootManageSharedAccessKey;
	 * SharedAccessKey=oc/FYkx+fJ1iC8wWpwyD/8YqNurulni81m4HmXXis3o=
	 */
	@RequestMapping("/send")
	public String sendMessage() {
		System.out.println("Client wants to add = ");
		Configuration config = ServiceBusConfiguration.configureWithSASAuthentication("springbootdemo",
				"RootManageSharedAccessKey", "oc/FYkx+fJ1iC8wWpwyD/8YqNurulni81m4HmXXis3o=", ".servicebus.windows.net");

		ServiceBusContract service = ServiceBusService.create(config);
		System.out.println(service);
		// TopicInfo topicInfo = new TopicInfo("mytopic");
		try {
			// CreateTopicResult result = service.createTopic(topicInfo);
			BrokeredMessage message = new BrokeredMessage("MyMessage");
			service.sendTopicMessage("mytopic", message);
			System.out.println("Message Sent...");
		} catch (ServiceException e) {
			System.out.print("ServiceException encountered: ");
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		return "Done";
	}

	@RequestMapping("/receive")
	public String receiveMessage() {
		Configuration config = ServiceBusConfiguration.configureWithSASAuthentication("springbootdemo",
				"RootManageSharedAccessKey", "oc/FYkx+fJ1iC8wWpwyD/8YqNurulni81m4HmXXis3o=", ".servicebus.windows.net");

		ServiceBusContract service = ServiceBusService.create(config);
		SubscriptionInfo subInfo = new SubscriptionInfo("AllMessages");

		try {
			GetSubscriptionResult result = service.getSubscription("mytopic", "AllMessages");
			// System.out.println(result);
			ReceiveMessageOptions opts = ReceiveMessageOptions.DEFAULT;
			opts.setReceiveMode(ReceiveMode.PEEK_LOCK);
			// service.getTopic("mytopic");
			BrokeredMessage msg = service.receiveSubscriptionMessage("mytopic", "AllMessages").getValue();
			System.out.println(msg.getContentType());
			byte[] b = new byte[200];
			String s = null;
			int numRead = 0;
			numRead = msg.getBody().read(b);
			s = new String(b);
			s = s.trim();
			System.out.println("Message = " + s);
			service.deleteMessage(msg);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Done";
	}
}
