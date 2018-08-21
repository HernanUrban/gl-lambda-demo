package com.globallogic.gllambdademo.dao;

import java.util.List;

public interface SpeakerDAO {

    List<Speaker> getAll();

    Speaker create(Speaker speaker);

}
