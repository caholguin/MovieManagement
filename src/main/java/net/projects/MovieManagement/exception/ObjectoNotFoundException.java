package net.projects.MovieManagement.exception;

public class ObjectoNotFoundException extends RuntimeException {

    private final String objectNotFoundName;

    private final Throwable cause;

    public ObjectoNotFoundException(String objectNotFoundName){
        this.objectNotFoundName = objectNotFoundName;
        this.cause = null;
    }

    public ObjectoNotFoundException(String objectNotFoundName, Throwable cause){
        this.objectNotFoundName = objectNotFoundName;
        this.cause = null;
    }

    @Override
    public String getMessage(){

        String message = super.getMessage();

        if(message == null){
            message = "";
        }

        return message.concat("(object not found").concat(this.objectNotFoundName).concat(")");
    }

    public String getObjectNotFoundName(){
        return objectNotFoundName;
    }
}