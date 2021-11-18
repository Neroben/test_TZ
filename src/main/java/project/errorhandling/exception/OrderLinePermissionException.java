package project.errorhandling.exception;

public class OrderLinePermissionException extends RuntimeException {

    private static final String MESSAGE = "You cannot accept delivery from different suppliers";

    public OrderLinePermissionException() {
        super(MESSAGE);
    }

}
