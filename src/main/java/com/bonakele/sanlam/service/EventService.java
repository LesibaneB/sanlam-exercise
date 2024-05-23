package com.bonakele.sanlam.service;

public interface EventService {
    public void publishEvent(String eventJson, String snsTopicArn);
}

