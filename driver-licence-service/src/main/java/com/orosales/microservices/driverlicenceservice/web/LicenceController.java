package com.orosales.microservices.driverlicenceservice.web;


import com.orosales.microservices.driverlicenceservice.dto.FilterLicenceDTO;
import com.orosales.microservices.driverlicenceservice.dto.ValidityDTO;
import com.orosales.microservices.driverlicenceservice.model.Licence;
import com.orosales.microservices.driverlicenceservice.service.ILicenceService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/licences")
@OpenAPIDefinition(
        info = @Info(
               title="Driver Licence Service",
               version = "1.0",
               description = "Component which allow to manage infortion of driver licences",
                contact = @Contact(
                        name = "Omar Rosales",
                        url = "https://github.com/orosales",
                        email = "orosales.qast@gmail.com"
                ),
                license = @License(
                        name = "Some License",
                        url = "https://github.com/orosales"
                )
        ),
        servers = {
                @Server(url="http://localhost:9003")
        },
        tags = @Tag(name = "Omar Rosales", description = "Driver Licence Service")

)
public class LicenceController {

    ILicenceService licenceService;


    LicenceController(ILicenceService licenceService) {
        this.licenceService = licenceService;
    }

    @Operation(
            description = "Issue a Driver Licence",
            tags = {"Omar Rosales"},
            responses = {
                    @ApiResponse(
                            description = "Response Ok",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = License.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "plain/text",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = HttpClientErrorException.class)
                                    )
                            )
                    )
            }

    )

    @PostMapping
    public ResponseEntity<Licence> registerLicence(@RequestBody Licence licence) throws Exception {
        //log.info(licence.toString());
        return  new ResponseEntity(licenceService.register(licence), HttpStatus.CREATED);
    }

    @Operation(
            description = "Get All list of  Driver Licence",
            tags = {"Omar Rosales"},
            responses = {
                    @ApiResponse(
                            description = "Response Ok",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = License.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "plain/text",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = HttpClientErrorException.class)
                                    )
                            )
                    )
            }

    )

    @GetMapping
    public ResponseEntity<List<Licence>> listAll() throws Exception {
        return new ResponseEntity<>( licenceService.list(), HttpStatus.OK);
    }

    @Operation(
            description = "Validate the Driver Licence",
            tags = {"Omar Rosales"},
            responses = {
                    @ApiResponse(
                            description = "Response Ok",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = License.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "plain/text",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = HttpClientErrorException.class)
                                    )
                            )
                    )
            }

    )

    @GetMapping("/validity/{numLicence}")
    public ResponseEntity<ValidityDTO> validateLicence(@PathVariable("numLicence") String numLicence  )throws Exception {
        return  ResponseEntity.ok (licenceService.validateLicence(numLicence));
    }

    @Operation(
            description = "List Driver Licences by filter",
            tags = {"Omar Rosales"},
            responses = {
                    @ApiResponse(
                            description = "Response Ok",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = License.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "plain/text",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = HttpClientErrorException.class)
                                    )
                            )
                    )
            }

    )

    @GetMapping("/list/byFilter")
    public ResponseEntity<List<Licence>> listByFilter(@RequestParam(required = false) String status,
                                                      @RequestParam(required = false) String typeLicence,
                                                      @RequestParam(required = false) String numLicence ) throws Exception {
        System.out.println("ListByFilter in Driver Licence Service");

        FilterLicenceDTO filterLicenceDTO = new FilterLicenceDTO();

        if (status!=null) {
            filterLicenceDTO.setStatus(status);
        }

        if (typeLicence!=null) {
            filterLicenceDTO.setTypeLicence(typeLicence);
        }

        if (numLicence!=null) {
            filterLicenceDTO.setNumLicence(numLicence);
        }

        return   new ResponseEntity<>(  licenceService.listByFilter(filterLicenceDTO), HttpStatus.OK);
    }



    @Operation(
            description = "Maintain driver's licenses - Updating partial information",
            tags = {"Omar Rosales"},
            responses = {
                    @ApiResponse(
                            description = "Response Ok",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = License.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "plain/text",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = HttpClientErrorException.class)
                                    )
                            )
                    )
            }

    )

    @PatchMapping(path="/{idLicence}")
    public ResponseEntity<Licence> updateLicence(@PathVariable("idLicence") Integer idLicence,
                                                 @RequestBody Map<String, Object> fields) throws Exception {

        return ResponseEntity.ok ( licenceService.modifyByFields(idLicence, fields) ) ;
    }

    @Operation(
            description = "Eliminate driver's licenses - Valid more than 30 days which are not renovated",
            tags = {"Omar Rosales"},
            responses = {
                    @ApiResponse(
                            description = "Response Ok",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = License.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "plain/text",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = HttpClientErrorException.class)
                                    )
                            )
                    )
            }

    )

    @PutMapping("/eliminateExpired")
    public ResponseEntity<List<Licence>> eliminateExpiredLicences() throws Exception {
        return new ResponseEntity<>( licenceService.eliminateExpiredLicences() , HttpStatus.OK);
    }



}
