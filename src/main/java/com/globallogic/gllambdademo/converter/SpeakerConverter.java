package com.globallogic.gllambdademo.converter;

import com.globallogic.gllambdademo.dao.Speaker;
import com.globallogic.gllambdademo.dto.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SpeakerConverter implements Converter<Speaker, User> {

    @Override
    public User convert(Speaker speaker) {
        return new User().setEmail(speaker.getEmail())
                .setName(speaker.getName());
    }
}
