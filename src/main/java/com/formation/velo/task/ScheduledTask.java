package com.formation.velo.task;

import com.formation.velo.service.ParkingService;
import com.formation.velo.service.PumpService;
import com.formation.velo.service.StationService;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log
public class ScheduledTask {
    private final StationService stationService;
    private final PumpService pumpService;
    private final ParkingService parkingService;

    public ScheduledTask(StationService stationService, PumpService pumpService, ParkingService parkingService) {
        this.stationService = stationService;
        this.pumpService = pumpService;
        this.parkingService = parkingService;
    }

    @Scheduled(fixedRate = 300000)
    public void updateStation() {
        log.info("updating...");
        try {
            stationService.getRecord();
            log.info("✅ station update");
        } catch (Exception e) {
            log.info("❌ station not update");
        }

        try {
            pumpService.getRecord();
            log.info("✅ pump update");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.info("❌ pump not update");
        }

        try {
            parkingService.getRecord();
            log.info("✅ parking update");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.info("❌ parking not update");
        }
    }
}