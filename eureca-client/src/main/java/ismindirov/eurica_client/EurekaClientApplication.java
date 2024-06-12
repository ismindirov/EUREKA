package ismindirov.eurica_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class EurekaClientApplication implements GreetingController {

    @Autowired
    @Lazy
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @Override
    @RequestMapping("/greeting")
    public String greeting() {
        return String.format(
          "Hello from '%s'!", discoveryClient.getInstances(appName).get(0).getServiceId());
    }
}
