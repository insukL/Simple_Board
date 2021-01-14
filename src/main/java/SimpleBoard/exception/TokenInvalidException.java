package SimpleBoard.exception;

public class TokenInvalidException extends CriticalException {
    public TokenInvalidException(){
        super();
    }

    public TokenInvalidException(String message){
        super(message);
    }
}
