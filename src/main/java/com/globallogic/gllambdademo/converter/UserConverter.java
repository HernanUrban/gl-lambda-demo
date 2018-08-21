package com.globallogic.gllambdademo.converter;

import com.globallogic.gllambdademo.dao.Speaker;
import com.globallogic.gllambdademo.dto.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, Speaker> {

    @Override
    public Speaker convert(User user) {
        Speaker speaker = new Speaker();
        speaker.setName(user.getName());
        speaker.setEmail(user.getEmail());
        return speaker;
    }
}
