package com.globallogic.gllambdademo.service;

import com.globallogic.gllambdademo.converter.SpeakerConverter;
import com.globallogic.gllambdademo.converter.UserConverter;
import com.globallogic.gllambdademo.dao.DynamoDBSpeakerDAO;
import com.globallogic.gllambdademo.dao.Speaker;
import com.globallogic.gllambdademo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpeakerService {

    private static final DynamoDBSpeakerDAO speakerDao = DynamoDBSpeakerDAO.instance();

    @Autowired
    SpeakerConverter converter;

    @Autowired
    UserConverter userConverter;

    public User create(User user){
        Speaker speaker = speakerDao.create(userConverter.convert(user));
        return converter.convert(speaker);
    }

    public List<User> findAll(){
        List<User> list = speakerDao.getAll().stream().map(s -> converter.convert(s)).collect(Collectors.toList());
        return list;
    }
}
