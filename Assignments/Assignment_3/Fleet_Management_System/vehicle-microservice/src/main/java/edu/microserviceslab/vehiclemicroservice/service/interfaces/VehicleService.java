package edu.microserviceslab.vehiclemicroservice.service.interfaces;

import edu.microserviceslab.vehiclemicroservice.entity.Registration;
import edu.microserviceslab.vehiclemicroservice.entity.Vehicle;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getAllVehicles();

    String getVehicleLicensePlate(Long vehicleId);

    Vehicle addVehicle(Vehicle vehicle);

}
