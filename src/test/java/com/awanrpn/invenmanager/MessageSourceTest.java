package com.awanrpn.invenmanager;

import com.awanrpn.invenmanager.config.ErrorMessageSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Locale;

@SpringBootTest(
        classes = ErrorMessageSource.class
)
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    private final Locale LOCALE = Locale.of("id", "ID");

    @Test
    @Disabled
    void messageTest() {

        String hello = messageSource.getMessage("hello", new Object[]{"awan"}, LOCALE);
        Assertions.assertEquals("Hello awan", hello);

    }
}
