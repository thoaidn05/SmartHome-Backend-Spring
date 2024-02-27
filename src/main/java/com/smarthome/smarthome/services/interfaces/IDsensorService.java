package com.smarthome.smarthome.services.interfaces;

import com.smarthome.smarthome.dtos.DsensorDTO;
import com.smarthome.smarthome.models.Dsensor;
import com.smarthome.smarthome.models.Switch;

import java.util.List;

public interface IDsensorService {
    Dsensor createDsensor(DsensorDTO dsensorDTO) throws Exception;
    Dsensor updateDsensor(int id, DsensorDTO dsensorDTO) throws Exception;
    List<Dsensor> getAllDsensor();
}
