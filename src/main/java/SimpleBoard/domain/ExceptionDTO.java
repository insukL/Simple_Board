package SimpleBoard.domain;

public class ExceptionDTO {
    protected String className;
    protected String errorMessage;
    protected String errorTrace;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorTrace() {
        return errorTrace;
    }

    public void setErrorTrace(String errorTrace) {
        this.errorTrace = errorTrace;
    }
}
