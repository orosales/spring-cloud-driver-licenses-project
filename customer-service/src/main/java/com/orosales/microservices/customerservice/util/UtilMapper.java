package com.orosales.microservices.customerservice.util;

import com.orosales.microservices.customerservice.model.dto.CustomerDTO;
import com.orosales.microservices.customerservice.model.entity.CustomerEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UtilMapper {

    public CustomerEntity convertDTOtoEntity(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = CustomerEntity.builder().build();
        BeanUtils.copyProperties(customerDTO, customerEntity);
        return customerEntity;
    }


    public CustomerDTO convertEntityToDTO(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = CustomerDTO.builder().build();
        BeanUtils.copyProperties(customerEntity, customerDTO);
        return customerDTO;
    }

}
