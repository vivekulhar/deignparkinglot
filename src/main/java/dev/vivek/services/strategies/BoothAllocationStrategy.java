package dev.vivek.services.strategies;

import dev.vivek.models.Booth;
import dev.vivek.models.Gate;
import dev.vivek.models.enums.VehicleType;

import java.util.Optional;

public interface BoothAllocationStrategy {
    Optional<Booth> assignBooth(VehicleType vehicleType, Gate gate);
}
