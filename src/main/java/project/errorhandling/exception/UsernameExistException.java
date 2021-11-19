package project.errorhandling.exception;


public class UsernameExistException extends RuntimeException {

    private static final long serialVersionUID = -7738276732556122207L;

    private static final String MESSAGE = "User with email %s is exist";

    public UsernameExistException(String email) {
        super(String.format(MESSAGE, email));
    }

}
