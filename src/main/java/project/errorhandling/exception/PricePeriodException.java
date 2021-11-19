package project.errorhandling.exception;

import java.math.BigDecimal;

public class PricePeriodException extends RuntimeException {

    private static final long serialVersionUID = -7417592403017344137L;

    private static final String MESSAGE = "Actual price is exist %s";

    public PricePeriodException(BigDecimal price) {
        super(String.format(MESSAGE, price));
    }

}
