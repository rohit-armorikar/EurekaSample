package com.servicebus.AzureServiceBus.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.windowsazure.Configuration;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.services.servicebus.ServiceBusConfiguration;
import com.microsoft.windowsazure.services.servicebus.ServiceBusContract;
import com.microsoft.windowsazure.services.servicebus.ServiceBusService;
import com.microsoft.windowsazure.services.servicebus.models.BrokeredMessage;
import com.microsoft.windowsazure.services.servicebus.models.CreateQueueResult;
import com.microsoft.windowsazure.services.servicebus.models.QueueInfo;
import com.microsoft.windowsazure.services.servicebus.models.ReceiveMessageOptions;
import com.microsoft.windowsazure.services.servicebus.models.ReceiveMode;
import com.microsoft.windowsazure.services.servicebus.models.ReceiveQueueMessageResult;

@RestController
public class AzureServiceBusQueueController {

	/*
	 * 
	 * Endpoint= sb://satyensbnamespace.servicebus.windows.net/;
	 * SharedAccessKeyName=RootManageSharedAccessKey;
	 * SharedAccessKey=bj48aW+FdBRjSDr1q5mau93FnyOgUH3kc/NiiAkWMx4=
	 * 
	 */

	private static ServiceBusContract service;

	static {
		// Create a queue
		Configuration config = ServiceBusConfiguration.configureWithSASAuthentication("satyensbnamespace",
				"RootManageSharedAccessKey", "bj48aW+FdBRjSDr1q5mau93FnyOgUH3kc/NiiAkWMx4=", ".servicebus.windows.net");

		service = ServiceBusService.create(config);
	}

	@RequestMapping("/add/{message}")
	public void sendMessageToQueue(@PathVariable("message") String message) {
		try {
			// Create a queue
			Configuration config = ServiceBusConfiguration.configureWithSASAuthentication("satyensbnamespace",
					"RootManageSharedAccessKey", "bj48aW+FdBRjSDr1q5mau93FnyOgUH3kc/NiiAkWMx4=",
					".servicebus.windows.net");

			service = ServiceBusService.create(config);
			QueueInfo queueInfo = new QueueInfo("MessageQueue");
			queueInfo.setMaxSizeInMegabytes(1024L);// 1 GB Max Size

			CreateQueueResult result = service.createQueue(queueInfo);

			BrokeredMessage brokeredMessage = new BrokeredMessage(message);
			service.sendQueueMessage("MessageQueue", brokeredMessage);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/read")
	public String receiveMessageFromQueue() {
		try {
			ReceiveMessageOptions opts = ReceiveMessageOptions.DEFAULT;
			opts.setReceiveMode(ReceiveMode.PEEK_LOCK);

			while (true) {
				ReceiveQueueMessageResult resultQM = service.receiveQueueMessage("MessageQueue", opts);
				BrokeredMessage message = resultQM.getValue();
				System.out.println(message);
				if (message != null && message.getMessageId() != null) {
					System.out.println("MessageID: " + message.getMessageId());
					// Display the queue message.
					System.out.print("From queue: ");
					byte[] b = new byte[200];
					String s = null;
					int numRead = message.getBody().read(b);
					while (-1 != numRead) {
						s = new String(b);
						s = s.trim();
						System.out.print("s = " + s);
						numRead = message.getBody().read(b);
					}
					System.out.println("Deleting the message after reading it.");
					service.deleteMessage(message);
				} else {
					System.out.println("No more messages.");
					break;
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
