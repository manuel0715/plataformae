package com.plataformae.ws.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;

@Configuration
public class MessageConfig {

    private final Environment environment;

    public MessageConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public MessageProperties messageProperties() {
        return new MessageProperties(environment);
    }

    public static class MessageProperties {

        private final Environment environment;

        public MessageProperties(Environment environment) {
            this.environment = environment;
        }

        public String getLoginExitoso() {
            return environment.getProperty("app.messages.login-success", "Login exitoso");
        }

        public String getCredencialesInvalidas() {
            return environment.getProperty("app.messages.invalid-credentials", "Usuario o contraseña invalido");
        }

        public String getMensajeError() {
            return environment.getProperty("app.messages.error-occurred", "Ha ocurrido un error");
        }
        public String getUsuarioCreado() {
            return environment.getProperty("app.messages.usuario-creado", "Usuario creado");
        }
        public String getUsuarioYaExiste() {
            return environment.getProperty("app.messages.usuario-ya-existe", "Ya existe usuario");
        }
    }
}