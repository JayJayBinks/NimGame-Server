package github.jjbinks.nimgame.configuration;

import github.jjbinks.nimgame.ai.AIController;
import github.jjbinks.nimgame.ai.DumbMisereAI;
import github.jjbinks.nimgame.ai.SmartMisereAi;
import github.jjbinks.nimgame.persistence.GameStorage;
import github.jjbinks.nimgame.persistence.GameStorageImpl;
import github.jjbinks.nimgame.util.GameConfigurationFactory;
import github.jjbinks.nimgame.util.GameFactory;
import github.jjbinks.nimgame.util.GameInstanceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

public class ProjectConfiguration {


    private static final Logger logger = LoggerFactory.getLogger(ProjectConfiguration.class);

    /**
     * PRODUCTION SECTION
     */
    @Configuration
    @Profile("production")
    @PropertySource("classpath:/production.properties")
    static class ProductionConfiguration {
        @Autowired
        ProjectProperties projectProperties;

    }

    /**
     * DEVELOPMENT SECTION
     */
    @Configuration
    @Profile("development")
    @PropertySource("classpath:/development.properties")
    static class DevelopmentConfiguration {
        @Autowired
        ProjectProperties projectProperties;

        @Bean
        public GameStorage gameStorage() {
            return new GameStorageImpl();
        }

        @Bean
        public AIController aiMap() {
            return new AIController();
        }

        @Bean
        public DumbMisereAI dumbMisereAI() {
            return new DumbMisereAI();
        }

        @Bean
        public SmartMisereAi smartMisereAi() {
            return new SmartMisereAi();
        }
        
        @Bean
        public GameConfigurationFactory gameConfigurationFactory() {
            return new GameConfigurationFactory();
        }

        @Bean
        public GameFactory gameFactory() {
            return new GameFactory();
        }

        @Bean
        public GameInstanceUtil gameInstanceUtil() {
            return new GameInstanceUtil();
        }
    }
}