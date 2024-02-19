package com.orosales.microservices.customerservice.service;

import com.orosales.microservices.customerservice.model.dto.CustomerDTO;
import com.orosales.microservices.customerservice.model.entity.CustomerEntity;
import com.orosales.microservices.customerservice.repository.CustomerRepository;
import com.orosales.microservices.customerservice.util.UtilMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final UtilMapper utilMapper;

    private final CustomerRepository customerRepository;

    public CustomerDTO saveProduct(CustomerDTO customerDTO) {
        CustomerEntity productEntity = utilMapper.convertDTOtoEntity(customerDTO);
        customerRepository.save(productEntity);
        return customerDTO;
    }

    public CustomerDTO getByNumberIdentification(String numberIdentification) {
        Optional<CustomerEntity> customerEntity = customerRepository.findByNumIdentification(numberIdentification);
        CustomerDTO customerDTO = new CustomerDTO();
        if ( customerEntity.isPresent() ) {
            customerDTO = utilMapper.convertEntityToDTO(customerEntity.get());
        } else {
            //throw exception

        }
        return customerDTO;
    }

    public List<CustomerDTO> getCustomers() {
         Iterable<CustomerEntity> allCustomers = customerRepository.findAll();
         List<CustomerDTO> customerList = new ArrayList<>();
         allCustomers.forEach( c -> {
             CustomerDTO customerDTO = utilMapper.convertEntityToDTO(c);
             customerList.add(customerDTO);
         });

         return customerList;
    }

}
