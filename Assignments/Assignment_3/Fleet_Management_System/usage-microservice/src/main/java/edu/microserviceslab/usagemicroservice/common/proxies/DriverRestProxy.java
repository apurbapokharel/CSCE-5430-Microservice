package edu.microserviceslab.usagemicroservice.common.proxies;

import edu.microserviceslab.usagemicroservice.common.config.UsageConfigProperties;
import org.h2.Driver;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DriverRestProxy {

    private UsageConfigProperties usageConfigProperties;

    private final RestTemplate restTemplate;

    public DriverRestProxy(UsageConfigProperties usageConfigProperties) {
        this.usageConfigProperties = usageConfigProperties;
        this.restTemplate = new RestTemplate();
    }

    public Long getVehicleForDriver(Long driverId) {
        return restTemplate.getForObject(usageConfigProperties.getDriverBaseUrl() + "driver/{driverId}/vehicleId", Long.class, driverId);
    }

    public String getDriverById(Long driverId) {
        return restTemplate.getForObject(usageConfigProperties.getDriverBaseUrl() + "driver/{driverId}/name", String.class, driverId);
    }

}
