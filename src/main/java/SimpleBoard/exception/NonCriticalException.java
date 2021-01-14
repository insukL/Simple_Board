package SimpleBoard.exception;

public class NonCriticalException extends RuntimeException {
    public NonCriticalException(){
        super();
    }
    public NonCriticalException(String message){
        super(message);
    }
}
