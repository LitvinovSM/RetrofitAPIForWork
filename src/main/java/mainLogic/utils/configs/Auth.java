package mainLogic.utils.configs;

import lombok.Data;

@Data
public class Auth {
    private Credentials defaultAuth;
    private Credentials mainCACredentials;
    private Credentials mainAnalystCredentials;
    private Credentials analystFirst;
}
