package SimpleBoard.exception;

public class NoContentException extends NonCriticalException {
    public NoContentException(){
        super();
    }

    public NoContentException(String message){
        super(message);
    }
}
