package com.smarthome.smarthome.services.interfaces;

import com.smarthome.smarthome.dtos.SwitchDTO;
import com.smarthome.smarthome.models.Switch;

import java.util.List;

public interface ISwitchService {
    Switch createSwitch(SwitchDTO switchDTO) throws Exception;
    Switch updateSwitch(int id, SwitchDTO switchDTO) throws Exception;
    List<Switch> getAllSwitch();
}
