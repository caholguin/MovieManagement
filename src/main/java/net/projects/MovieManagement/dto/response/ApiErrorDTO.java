package net.projects.MovieManagement.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ApiErrorDTO implements Serializable {

    @JsonProperty("http_code")
    private int httpCode;

    private String url;

    @JsonProperty("http_method")
    private String httpMethod;

    private String message;

    @JsonProperty("backend_message")
    private String backendMessage;

    private LocalDateTime timestamp;

    private List<String> details;


    public int getHttpCode(){
        return httpCode;
    }

    public void setHttpCode(int httpCode){
        this.httpCode = httpCode;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getHttpMethod(){
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod){
        this.httpMethod = httpMethod;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getBackendMessage(){
        return backendMessage;
    }

    public void setBackendMessage(String backendMessage){
        this.backendMessage = backendMessage;
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp = timestamp;
    }

    public List<String> getDetails(){
        return details;
    }

    public void setDetails(List<String> details){
        this.details = details;
    }
}
