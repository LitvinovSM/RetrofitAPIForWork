package mainLogic.utils.configs;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;

public class TestConfigFactory {
    private volatile Config config;
    private volatile GeneralConfig generalConfig;
    private volatile CredentialConfig credentialConfig;

    /**
     * Создание выборки конфигов из системных свойств,
     * системных переменных и файла WebTestsConfig.cof
     */
    private TestConfigFactory() {
        config = ConfigFactory.systemProperties()
                .withFallback(ConfigFactory.systemEnvironment())
                .withFallback(ConfigFactory.parseResources("TestsConfig.conf"));

    }

    /**
     * Получение части основного конфига
     */
    public synchronized GeneralConfig getGeneralConfig() {
        if (generalConfig == null) {
            generalConfig = ConfigBeanFactory.create(config.getConfig("general"), GeneralConfig.class);
        }
        return generalConfig;
    }

    /**
     * Получение части основного конфига
     */
    public synchronized CredentialConfig getCredentialConfig() {
        if (credentialConfig == null) {
            credentialConfig = ConfigBeanFactory.create(config.getConfig("credential"), CredentialConfig.class);
        }
        return credentialConfig;
    }
    /**
     * Получение экземпляра класса фабрики
     */
    public synchronized static TestConfigFactory getInstance() {
        return new TestConfigFactory();
    }
}
