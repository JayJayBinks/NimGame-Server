package github.jjbinks.nimgame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"github.jjbinks"})
public class NimGameApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(NimGameApplication.class);

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = new SpringApplication(NimGameApplication.class).run(args);
    }

}
