package com.example.user.volleyjson;

/**
 * Created by USER on 2/12/2017.
 */

public class notesgetter {

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String topic,url;

    public notesgetter(String topic,String url){
        this.setUrl(url);
        this.setTopic(topic);

    }
}
