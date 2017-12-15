package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by admin on 2017/11/10.
 */
@Configuration
@ComponentScan(basePackages = {"controller","service","dao","learn"})
public class ComponentConfig {

}
