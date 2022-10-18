package mainLogic.utils.configs;

import lombok.Data;

@Data
public class StandConfig {
    private String baseURL;
    private String BDLink;
    private String BDLogin;
    private String BDPass;
    private Credentials defaultAuth;
    private Credentials mainCACredentials;
    private Credentials mainAnalystCredentials;
    private Credentials analystFirst;

}
