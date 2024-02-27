package com.smarthome.smarthome.services.interfaces;

import com.smarthome.smarthome.dtos.DimmerDTO;
import com.smarthome.smarthome.models.Device;
import com.smarthome.smarthome.models.Dimmer;

import java.util.List;

public interface IDimmerService {
    Dimmer createDimmer(DimmerDTO dimmerDTO) throws Exception;
    Dimmer updateDimmer(int id, DimmerDTO dimmerDTO) throws Exception;
    List<Dimmer> getAllDimmer();
}
