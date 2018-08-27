package com.globallogic.gllambdademo.controller;


import com.globallogic.gllambdademo.dto.SpeakerDTO;
import com.globallogic.gllambdademo.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/speaker")
public class SpeakerController {

    @Autowired
    SpeakerService service;

    @GetMapping()
    public ResponseEntity<List<SpeakerDTO>> getSpeakers() {
        List<SpeakerDTO> speakerDTOS = service.findAll();
        if (speakerDTOS != null && !speakerDTOS.isEmpty()) {
         return new ResponseEntity<>(speakerDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<SpeakerDTO> addSpeaker(@RequestBody SpeakerDTO speakerDTO) {
        return new ResponseEntity<>(service.create(speakerDTO), HttpStatus.CREATED);

    }
}
