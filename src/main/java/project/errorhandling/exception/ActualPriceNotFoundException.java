package project.errorhandling.exception;

public class ActualPriceNotFoundException extends RuntimeException {

    private static final String MESSAGE = "For product id %s not found actual price";

    public ActualPriceNotFoundException(String productId) {
        super(String.format(MESSAGE, productId));
    }

}
