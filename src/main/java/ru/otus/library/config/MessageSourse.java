package ru.otus.library.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourse {

    @Bean
    public MessageSource messageSource(YamlProps props) {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename(props.getBundlebase());
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
