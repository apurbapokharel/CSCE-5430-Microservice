package edu.microserviceslab.vehiclemicroservice.controller;

import edu.microserviceslab.vehiclemicroservice.entity.Registration;
import edu.microserviceslab.vehiclemicroservice.entity.Vehicle;
import edu.microserviceslab.vehiclemicroservice.service.interfaces.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<Vehicle> listAllVehicles() {

        if (vehicleService.getAllVehicles() == null){
            throw new IllegalStateException("No vehicle data.");
        }
        else{
            return vehicleService.getAllVehicles();
        }
    }

    @ResponseBody
    @RequestMapping("/licensePlate/{vehicleId}")
    public String getVehicleLicensePlate(@PathVariable("vehicleId") Long vehicleId) {
        if (vehicleService.getVehicleLicensePlate(vehicleId) == null){
            throw new IllegalStateException("No license plate with this vehicle ID");
        }
        else{
            return vehicleService.getVehicleLicensePlate(vehicleId);
        }
    }

    @ResponseBody
    @PostMapping("/add")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        if (vehicle == null){
            throw new IllegalStateException("Submit a valid data");
        }
        else{
            return vehicleService.addVehicle(vehicle);
        }
    }
}
