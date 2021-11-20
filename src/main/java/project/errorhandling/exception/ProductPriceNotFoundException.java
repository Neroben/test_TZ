package project.errorhandling.exception;


public class ProductPriceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1421251019832890683L;

    private static final String MESSAGE = "Product price not found by product id %s";

    public ProductPriceNotFoundException(String productId) {
        super(String.format(MESSAGE, productId));
    }

}
