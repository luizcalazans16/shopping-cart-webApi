package br.com.calazans.shoppingcart.app.config.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;

public class ResourceNotFoundException extends LocalizedException {

    private static final long serialVersionUID = 1L;
    private static final String RESOURCE_NOT_FOUND_BUNDLE_KEY = "Registro não encontrado";

    public ResourceNotFoundException(final Class<?> clazz, final Object resourceId) {
        super(RESOURCE_NOT_FOUND_BUNDLE_KEY, new DefaultMessageSourceResolvable(clazz.getCanonicalName()), resourceId);
    }

    public ResourceNotFoundException(String bundleKey, Object... args) {
        super(bundleKey, args);
    }
}
