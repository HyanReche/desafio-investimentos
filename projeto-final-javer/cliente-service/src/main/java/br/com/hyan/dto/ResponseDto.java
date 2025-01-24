package br.com.hyan.dto;

import java.util.Map;

public class ResponseDto {
    private String message;
    private Map<String, String> urls;

    //CONSTRUTOR
    
    public ResponseDto(String message, Map<String, String> urls) {
        this.message = message;
        this.urls = urls;
    }
    
    
    //GETTERS E SETTERS
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getUrls() {
        return urls;
    }

    public void setUrls(Map<String, String> urls) {
        this.urls = urls;
    }
}
