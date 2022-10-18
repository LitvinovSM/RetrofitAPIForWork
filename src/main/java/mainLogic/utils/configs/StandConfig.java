package mainLogic.utils.configs;

import lombok.Data;

@Data
public class StandConfig {
    private String baseURL;
    private String BDLink;
    private String BDLogin;
    private String BDPass;
    private Auth auth;

}
