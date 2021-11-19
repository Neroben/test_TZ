package project.errorhandling.exception;


public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5178217661971076857L;

    private static final String MESSAGE = "User with email %s is not find";

    public UserNotFoundException(String email) {
        super(String.format(MESSAGE, email));
    }

}
