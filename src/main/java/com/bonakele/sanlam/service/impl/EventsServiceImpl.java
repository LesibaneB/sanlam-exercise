package com.bonakele.sanlam.service.impl;

import com.amazonaws.services.sns.model.PublishRequest;
import com.bonakele.sanlam.config.AmazonSNSClient;
import com.bonakele.sanlam.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventsServiceImpl implements EventService {

    private final AmazonSNSClient amazonSnsClient;

    @Autowired
    public EventsServiceImpl(AmazonSNSClient amazonSNSClient) {
        this.amazonSnsClient = amazonSNSClient;
    }

    public void publishEvent(String eventJson, String snsTopicArn) {
        PublishRequest publishRequest = new PublishRequest()
                .withMessage(eventJson)
                .withTopicArn(snsTopicArn);

        try {
            this.amazonSnsClient.getSnsClient().publish(publishRequest);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
