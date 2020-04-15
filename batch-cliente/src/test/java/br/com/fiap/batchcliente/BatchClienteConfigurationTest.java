package br.com.fiap.batchcliente;

import java.util.Properties;

import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class BatchClienteConfigurationTest {
   
    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        return new JobLauncherTestUtils();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() throws Exception {
        final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        Properties properties = new Properties();

        properties.setProperty("input.file", "classpath:clientes.txt");
        pspc.setProperties(properties);

        return pspc;
    }
}