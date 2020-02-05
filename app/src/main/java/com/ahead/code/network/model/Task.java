package com.ahead.code.network.model;

import java.io.Serializable;

public class Task implements Serializable {

    private String start_time, end_time, description;
    private String[] participants;

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getDescription() {
        return description;
    }

    public String[] getParticipants() {
        return participants;
    }
}
