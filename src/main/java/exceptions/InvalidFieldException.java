package exceptions;

public class InvalidFieldException extends RuntimeException{
    public InvalidFieldException(String message){
        super(message);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
