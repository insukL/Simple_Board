package SimpleBoard.exception;

public class PermissionInvalidException extends NonCriticalException {
    public PermissionInvalidException(){ super(); }

    public PermissionInvalidException(String message){ super(message); }
}
