package com.awanrpn.invenmanager.config;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Component
public class ErrorMessageSource extends ReloadableResourceBundleMessageSource {

    public ErrorMessageSource() {
        super();
        setBasename("classpath:err_lang");
        setDefaultEncoding(StandardCharsets.UTF_8.name());
    }

    public static Locale locale() {
        return Locale.of("id", "ID");
    }
}
