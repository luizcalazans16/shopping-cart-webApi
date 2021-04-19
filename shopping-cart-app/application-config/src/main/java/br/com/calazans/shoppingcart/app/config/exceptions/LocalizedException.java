package br.com.calazans.shoppingcart.app.config.exceptions;

public class LocalizedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String bundleKey;
    private final Object[] args;


    public LocalizedException(String bundleKey, Object... args) {
        this(null, bundleKey, args);
    }

    public LocalizedException(final Throwable cause, final String bundleKey, final Object... args) {
        super(bundleKey, cause);
        this.bundleKey = bundleKey;
        this.args = args;
    }

    public String getBundleKey() {
        return bundleKey;
    }

    public Object[] getArgs() {
        return args;
    }
}
