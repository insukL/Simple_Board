package SimpleBoard.exception;

public class EmptyStringException extends NonCriticalException {
    public EmptyStringException(){
        super();
    }

    public EmptyStringException(String message){
        super(message);
    }
}
