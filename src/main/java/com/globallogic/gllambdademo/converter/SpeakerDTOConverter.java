package com.globallogic.gllambdademo.converter;

import com.globallogic.gllambdademo.dao.Speaker;
import com.globallogic.gllambdademo.dto.SpeakerDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SpeakerDTOConverter implements Converter<SpeakerDTO, Speaker> {

    @Override
    public Speaker convert(SpeakerDTO speakerDTO) {
        Speaker speaker = new Speaker();
        speaker.setName(speakerDTO.getName());
        speaker.setEmail(speakerDTO.getEmail());
        return speaker;
    }
}
