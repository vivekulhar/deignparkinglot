package dev.vivek.services.strategies;

import dev.vivek.models.Booth;
import dev.vivek.models.Gate;
import dev.vivek.models.ParkingLot;
import dev.vivek.models.enums.ParkingBoothStatus;
import dev.vivek.models.enums.VehicleType;
import dev.vivek.repositories.BoothRepository;
import dev.vivek.repositories.ParkingLotRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FarthestEmptyBootStrategy implements BoothAllocationStrategy{
    private ParkingLotRepository parkingLotRepository;
    private BoothRepository boothRepository;
    public FarthestEmptyBootStrategy(ParkingLotRepository parkingLotRepository, BoothRepository boothRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.boothRepository = boothRepository;
    }
    @Override
    public Optional<Booth> assignBooth(VehicleType vehicleType, Gate gate) {
        ParkingLot parkingLot = parkingLotRepository.fetchByGateId(gate.getId());
        List<Booth> parkingBooths = boothRepository.fetchAllBoothsByParkingLotId(parkingLot.getId());
        Collections.reverse(parkingBooths);

        for(Booth booth : parkingBooths){
            if(booth.getBoothStatus().equals(ParkingBoothStatus.FREE)
            && booth.getSupportedVehicleType().equals(vehicleType)){
                return Optional.of(booth);
            }
        }
        return Optional.empty();

    }
}
