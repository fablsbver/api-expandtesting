package com.github.fblsbver.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/application.properties"
})
public interface AppConfig extends Config {

    @Key("baseUrl")
    String baseUrl();

    @Key("basePath")
    String basePath();

    @Key("userName")
    String userName();

    @Key("userEmail")
    String userEmail();

    @Key("userPassword")
    String userPassword();

    @Key("userNameInProfile")
    String userNameInProfile();

    @Key("userPhoneNumber")
    String userPhoneNumber();

    @Key("userCompany")
    String userCompany();

    @Key("userReg")
    String userReg();

    @Key("userLogin")
    String userLogin();

    @Key("userInfo")
    String userInfo();

    @Key("userLogout")
    String userLogout();

    @Key("userDeleteAcc")
    String userDeleteAcc();



}
