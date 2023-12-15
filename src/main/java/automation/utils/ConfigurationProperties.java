package automation.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("framework.properties")
public class ConfigurationProperties {
    @Value("${browser}")
    private String browser;

    @Value("${passwd}")
    private String password;

    @Value("${nomeutente}")
    private String username;

    @Value("${lockedmessage}")
    private String lockedmessage;
    @Value("${wrongusermessage}")
    private String wrongusermessage;

    @Value("${missingusernamemessage}")
    private String missingusernamemessage;

    @Value("${missingpasswordmessage}")
    private String missingpasswordmessage;

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getMessage(String message){
        switch (message){
            case "lockedmessage":
                return lockedmessage;
            case "wrongusermessage":
                return wrongusermessage;
            case "missingpasswordmessage":
                return missingpasswordmessage;
            case "missingusernamemessage"    :
                return missingusernamemessage;
            default:
                return "nostringfound";
        }
    }
}
