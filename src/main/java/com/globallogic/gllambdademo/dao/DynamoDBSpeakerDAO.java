package com.globallogic.gllambdademo.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.globallogic.gllambdademo.dbmanager.DynamoDBManager;

import java.util.List;

public class DynamoDBSpeakerDAO implements SpeakerDAO {

    private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

    private static volatile DynamoDBSpeakerDAO instance;

    public static DynamoDBSpeakerDAO instance() {

        if (instance == null) {
            synchronized (DynamoDBSpeakerDAO.class) {
                if (instance == null)
                    instance = new DynamoDBSpeakerDAO();
            }
        }
        return instance;
    }

    @Override
    public List<Speaker> getAll() {
        return mapper.scan(Speaker.class, new DynamoDBScanExpression());
    }

    @Override
    public Speaker create(Speaker speaker) {
        mapper.save(speaker);
        return speaker;
    }

}
