package mainLogic.utils.configs;

import lombok.Data;

@Data
public class MainConf {
    private String targetStand;
    private int READ_TIMEOUT;
    private int CONNECT_TIMEOUT;
}
