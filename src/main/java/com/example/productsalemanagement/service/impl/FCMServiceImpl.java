package com.example.productsalemanagement.service.impl;

import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class FCMServiceImpl {

    public void sendMessageToToken(String title, String messageBody, String token)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageToToken(title, messageBody, token);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
//        logger.info("Sent message to token. Device token: " + request.getToken() + "," + response + " msg " + jsonOutput );
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException{
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private AndroidConfig getAndroidConfig(String topic){
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder()
                        .setTag(topic).build()).build();
    }

    private ApnsConfig getApnsConfig(String topic){
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }

    private Message getPreconfiguredMessageToToken(String title, String message, String token) {
        return getPreconfiguredMessageBuilder(title, message).setToken(token)
                .build();
    }

//    private  Message getPreconfiguredMessageWithoutData(PushNotificationRequest request){
//        return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic()).build();
//    }
//
//    private  Message getPreconfiguredMessageWithData(Map<String, String> data, PushNotificationRequest request){
//        return getPreconfiguredMessageBuilder(request).putAllData(data).setToken(request.getToken())
//                .build();
//    }

    private Message.Builder getPreconfiguredMessageBuilder(String title, String message){
        AndroidConfig androidConfig = getAndroidConfig("");
        ApnsConfig apnsConfig = getApnsConfig("");
        return Message.builder().setApnsConfig(apnsConfig).setAndroidConfig(androidConfig)
                .setNotification(Notification.builder().setTitle(title).setBody(message).build());
    }
}
