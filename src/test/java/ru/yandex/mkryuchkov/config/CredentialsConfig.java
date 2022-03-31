package ru.yandex.mkryuchkov.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:credentials.properties")
public interface CredentialsConfig extends Config {

    @Key("user")
    String login();

    @Key("password")
    String password();
}
