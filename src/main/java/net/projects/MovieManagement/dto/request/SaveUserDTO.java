package net.projects.MovieManagement.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class SaveUserDTO implements Serializable {

    @Pattern(regexp = "^[a-zA-Z0-9-_]{5,255}$",message = "{saveUser.username.pattern}")
    private String username;

    @Size(max = 255, message = "{generic.max}")
    private String name;

    @NotBlank(message = "{generic.notblank}")
    @Size(min = 5, max = 255, message = "{generic.size}")
    private String password;

    @NotBlank
    @Size(min = 5, max = 255, message = "{generic.size}")
    @JsonProperty(value = "password_repeated")
    private String passwordRepeated;


    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPasswordRepeated(){
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated){
        this.passwordRepeated = passwordRepeated;
    }
}
