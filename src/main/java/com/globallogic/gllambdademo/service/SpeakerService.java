package com.globallogic.gllambdademo.service;

import com.globallogic.gllambdademo.converter.SpeakerConverter;
import com.globallogic.gllambdademo.converter.UserConverter;
import com.globallogic.gllambdademo.dao.DynamoDBSpeakerDAO;
import com.globallogic.gllambdademo.dao.Speaker;
import com.globallogic.gllambdademo.dto.User;
import com.globallogic.gllambdademo.message.NotificationMessage;
import com.globallogic.gllambdademo.message.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class SpeakerService {

    private static final DynamoDBSpeakerDAO speakerDao = DynamoDBSpeakerDAO.instance();

    @Autowired
    SpeakerConverter converter;

    @Autowired
    UserConverter userConverter;

    @Autowired
    private NotificationMessagingTemplate notificationMessagingTemplate;

    public User create(User user){
        Speaker speaker = speakerDao.create(userConverter.convert(user));
        sendMessage(user);
        return converter.convert(speaker);
    }

    public List<User> findAll(){
        List<Speaker> users = speakerDao.getAll();
        if (!isNull(users)) {
            return users.stream().map(s -> converter.convert(s)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }



    private void sendMessage(User user){
        try {
            notificationMessagingTemplate.convertAndSend("SNSGLTopic-1808", createMessage(user.getEmail(), user.getName()));
        } catch (Exception e) {
            System.out.println("Unable to send message: " + e.getMessage());
        }
    }

    private NotificationMessage createMessage(String email, String name){
        NotificationMessage message = new NotificationMessage();
        message.setEmail(email);
        message.setNotificationType(NotificationType.CREATE_USER.getNotificationName());
        Map<String, String> payload = new HashMap<String, String>();
        payload.put("first_name", name);
        message.setPayload(payload);
        return message;
    }
}
