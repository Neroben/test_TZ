package project.errorhandling.exception;

public class UserNotFoundException extends RuntimeException {

    private static final String MESSAGE = "User with email %s is not find";

    public UserNotFoundException(String email) {
        super(String.format(MESSAGE, email));
    }

}
