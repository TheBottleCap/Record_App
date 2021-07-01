package com.example.record;

public class Model {

    String topic, order;

    public Model(){}

    public Model(String topic, String order) {
        this.topic = topic;
        this.order = order;
    }



    public String getTopic() {
        return topic;
    }

    public String getOrder() {
        return order;
    }
}
