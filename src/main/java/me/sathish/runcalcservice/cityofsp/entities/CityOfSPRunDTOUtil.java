package me.sathish.runcalcservice.cityofsp.entities;

import org.springframework.beans.BeanUtils;

public class CityOfSPRunDTOUtil {
    public static CityOfSPRunDTO toCitySPRunDTO(CityOfSPRun cityOfSPRun) {
        CityOfSPRunDTO cityOfSPRunDTO = new CityOfSPRunDTO();
        BeanUtils.copyProperties(cityOfSPRun, cityOfSPRunDTO);
        return cityOfSPRunDTO;
    }

    public static CityOfSPRun toEntityCitySPRun(CityOfSPRunDTO cityOfSPRunDTO) {
        CityOfSPRun cityOfSPRun = new CityOfSPRun();
        BeanUtils.copyProperties(cityOfSPRunDTO, cityOfSPRun);
        return cityOfSPRun;
    }
}
