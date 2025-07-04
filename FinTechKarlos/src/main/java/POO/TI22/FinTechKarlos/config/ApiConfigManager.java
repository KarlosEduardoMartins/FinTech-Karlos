package POO.TI22.FinTechKarlos.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiConfigManager {
    private static volatile ApiConfigManager instance;

    private final String apiKey;

    private static final Logger logger = LoggerFactory.getLogger(ApiConfigManager.class);

    private ApiConfigManager() {
        this.apiKey = "FINTECH_API_KEY_" + System.currentTimeMillis();
        logger.info("Singleton ApiConfigManager inicializado. API Key: {}", this.apiKey);
    }

    public static ApiConfigManager getInstance() {
        if (instance == null) {
            synchronized (ApiConfigManager.class) {
                if (instance == null) {
                    instance = new ApiConfigManager();
                }
            }
        }
        return instance;
    }


    public String getApiKey() {
        return apiKey;
    }

    public void showMessage() {
        logger.info("Acessando a configuração através da instância Singleton: {}", this);
    }
}