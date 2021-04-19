package br.com.calazans.shoppingcart.app.config.exceptions;

public class BusinessException extends LocalizedException {

    private static final long serialVersionUID = 1L;

    public BusinessException(Throwable cause, String bundleKey, Object... args) {
        super(cause, bundleKey, args);
    }

    public BusinessException(final String bundleKey, final Object... args) {
        super(bundleKey, args);
    }
}