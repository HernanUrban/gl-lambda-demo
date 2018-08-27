package com.globallogic.gllambdademo.converter;

import com.globallogic.gllambdademo.dao.Speaker;
import com.globallogic.gllambdademo.dto.SpeakerDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SpeakerConverter implements Converter<Speaker, SpeakerDTO> {

    @Override
    public SpeakerDTO convert(Speaker speaker) {
        return new SpeakerDTO().setEmail(speaker.getEmail())
                .setName(speaker.getName());
    }
}
