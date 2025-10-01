package oct1Assi4FilleAndExceptionHandling.customExceptions;

public class InvalidMarksException extends RuntimeException {
    public InvalidMarksException(String str){
        super(str);
    }
}
