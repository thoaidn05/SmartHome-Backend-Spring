package com.smarthome.smarthome.services.interfaces;

import com.smarthome.smarthome.dtos.AsensorDTO;
import com.smarthome.smarthome.models.Asensor;

import java.util.List;

public interface IAsensorService {
    Asensor createAsensor(AsensorDTO asensorDTO) throws Exception;
    Asensor updateAsensor(int id, AsensorDTO asensorDTO) throws Exception;
    List<Asensor> getAllAsensor();
}
