package com.orosales.microservices.customerservice.web;

import com.orosales.microservices.customerservice.model.dto.CustomerDTO;
import com.orosales.microservices.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> saveProduct(@RequestBody CustomerDTO customerDTO) {
        log.info(customerDTO.toString());
        return ResponseEntity.ok(customerService.saveProduct(customerDTO));
    }

    @GetMapping("/{numIdentification}")
    public ResponseEntity<CustomerDTO> readByNumIdentification(@PathVariable("numIdentification") String numIdentification) {
        return new ResponseEntity<>(customerService.getByNumberIdentification(numIdentification), HttpStatus.OK );
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getListCustomers() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK );
    }


}
