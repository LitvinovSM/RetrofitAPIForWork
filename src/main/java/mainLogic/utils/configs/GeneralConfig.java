package mainLogic.utils.configs;

import lombok.Data;

/**
 * Вебконфиг в формате DTO  с геттерами и сеттерами через ломбук
 */
@Data
public class GeneralConfig {
    private String baseURL;
    private int READ_TIMEOUT;
    private int CONNECT_TIMEOUT;
}
