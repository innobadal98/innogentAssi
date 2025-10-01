package oct1Assi4FilleAndExceptionHandling.customExceptions;

public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String str){
        super(str);
    }
}
