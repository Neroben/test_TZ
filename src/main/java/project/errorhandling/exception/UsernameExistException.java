package project.errorhandling.exception;

public class UsernameExistException extends RuntimeException {

    private static final String MESSAGE = "User with email %s is exist";

    public UsernameExistException(String email) {
        super(String.format(MESSAGE, email));
    }

}
