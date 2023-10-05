package dev.vivek.services;

import dev.vivek.models.Vehicle;
import dev.vivek.models.enums.VehicleType;
import dev.vivek.repositories.VehicleRepository;

import java.util.Optional;

public class VehicleService {
    private VehicleRepository vehicleRepository;
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    public Vehicle getVehicle(String vehicleNumber, VehicleType vehicleType) {
        Optional<Vehicle> vehicleWrapper = vehicleRepository.fetchVehicleByNumber(vehicleNumber);

        if(vehicleWrapper.isEmpty()){
            // register the new vehicle
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);

            Vehicle savedVehicle = vehicleRepository.save(vehicle);
            return savedVehicle;
        }else{
            return vehicleWrapper.get();

        }
    }
}
