package config;

import learn.Fruits;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by admin on 2017/11/10.
 */
@Configuration
public class FruitsConfig {
    @Bean
    public Fruits getFruits(){
        return new Fruits();
    }

}
