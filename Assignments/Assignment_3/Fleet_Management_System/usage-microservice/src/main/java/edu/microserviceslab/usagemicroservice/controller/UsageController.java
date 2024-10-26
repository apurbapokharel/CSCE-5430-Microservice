package edu.microserviceslab.usagemicroservice.controller;

import edu.microserviceslab.usagemicroservice.entity.UsageStatistic;
import edu.microserviceslab.usagemicroservice.service.interfaces.UsageService;
import org.h2.Driver;
import org.springframework.web.bind.annotation.*;
import edu.microserviceslab.usagemicroservice.common.proxies.VehicleRestProxy;
import edu.microserviceslab.usagemicroservice.common.proxies.DriverRestProxy;

import java.util.List;

@RestController
@RequestMapping("/usage")
public class UsageController {

    private UsageService usageService;
    private VehicleRestProxy vehicleRestProxy;
    private DriverRestProxy driverRestProxy;

    public UsageController(UsageService usageService, VehicleRestProxy vehicleRestProxy, DriverRestProxy driverRestProxy) {
        this.usageService = usageService;
        this.vehicleRestProxy = vehicleRestProxy;
        this.driverRestProxy = driverRestProxy;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<UsageStatistic> listAllUsageStatistics() {

        if (usageService.getAllUsageStatistics() == null){
            throw new IllegalStateException("No usage data present");
        }
        else{
            return usageService.getAllUsageStatistics();
        }
    }

    @ResponseBody
    @RequestMapping("/driver/{driverId}")
    public List<UsageStatistic> listAllUsageStatisticsForDriver(@PathVariable("driverId") Long driverId) {

        if (usageService.getUsageStatisticsPerDriver(driverId) == null){
            throw new IllegalStateException("No usage data for this driver ID");
        }
        else{
            return usageService.getUsageStatisticsPerDriver(driverId);
        }
    }

    @ResponseBody
    @RequestMapping("/vehicle/{vehicleId}")
    public List<UsageStatistic> listAllUsageStatisticsForVehicle(@PathVariable("vehicleId") Long vehicleId) {

        if (usageService.getUsageStatisticsPerVehicle(vehicleId) == null){
            throw new IllegalStateException("No usage data for this vehicle ID");
        }
        else{
            return usageService.getUsageStatisticsPerVehicle(vehicleId);
        }
    }

    @ResponseBody
    @PostMapping("/add")
    public UsageStatistic addUsageStatics(@RequestBody UsageStatistic usageStatistic) {

        if (usageStatistic == null){
            throw new IllegalStateException("Submit a valid usage statistic");
        }
        else {
            Long vehicleId = usageStatistic.getVehicleId();
            String licensePlate = vehicleRestProxy.getVehicleLicensePlate(vehicleId);
            usageStatistic.setVehicleLicensePlate(licensePlate);

            Long driverId = 0L;
            while (true) {
                Long returned_vehicle_id = driverRestProxy.getVehicleForDriver(driverId);
                if (returned_vehicle_id == vehicleId)
                    break;
                driverId += 1L;
            }
            usageStatistic.setDriverId(driverId);

            String fullName = driverRestProxy.getDriverById(driverId);
            usageStatistic.setDriverFullname(fullName);

            return usageService.addUsageStatistic(usageStatistic);
        }
    }
}
