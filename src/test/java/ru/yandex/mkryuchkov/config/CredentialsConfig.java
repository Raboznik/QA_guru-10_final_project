package ru.yandex.mkryuchkov.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:steam.properties")
public interface CredentialsConfig extends Config {

    @Key("user")
    String login();

    @Key("password")
    String password();

    @Key("remote.login")
    String remoteLogin();

    @Key("remote.password")
    String remotePassword();

//    @Key("remote.url")
//    String remoteUrl();


}
