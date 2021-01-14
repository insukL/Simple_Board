package SimpleBoard.exception;

public class TokenExpireException extends NonCriticalException{
    public TokenExpireException(){
        super();
    }
    public TokenExpireException(String message){
        super(message);
    }
}
