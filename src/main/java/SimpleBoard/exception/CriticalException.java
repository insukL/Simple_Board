package SimpleBoard.exception;

public class CriticalException extends RuntimeException {
    public CriticalException(){
        super();
    }
    public CriticalException(String message){
        super(message);
    }
}
