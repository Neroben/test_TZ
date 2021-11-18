package project.errorhandling.exception;

public class ProductNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Product with id %s not found exception";

    public ProductNotFoundException(String productId) {
        super(String.format(MESSAGE, productId));
    }

}
