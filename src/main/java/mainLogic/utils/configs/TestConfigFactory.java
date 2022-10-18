package mainLogic.utils.configs;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;


public class TestConfigFactory {
    private volatile Config config;
    private volatile MainConf mainConf;
    private volatile StandConfig standConfig;

    /**
     * Создание выборки конфигов из системных свойств,
     * системных переменных и файла TestsConfig.conf
     */
    private TestConfigFactory() {
        config = ConfigFactory.systemProperties()
                .withFallback(ConfigFactory.systemEnvironment())
                .withFallback(ConfigFactory.parseResources("TestsConfig.conf"));

    }

    /**
     * Получение части основного конфига
     */
    public synchronized MainConf getMainConfig() {
        if (mainConf == null) {
            mainConf = ConfigBeanFactory.create(config.getConfig("main"), MainConf.class);
        }
        return mainConf;
    }

    /**
     * Получение части конфигураций стенда
     */
    public synchronized StandConfig getStandConfig() {
        if (standConfig == null) {
            standConfig = ConfigBeanFactory.create(config.getConfig(getMainConfig().getTargetStand()), StandConfig.class);
        }
        return standConfig;
    }
    /**
     * Получение экземпляра класса фабрики
     */
    public synchronized static TestConfigFactory getInstance() {
        return new TestConfigFactory();
    }
}
