package com.sura.bibloteca.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sura.bibloteca.dto.ElementoPrestableBaseDTO;
import com.sura.bibloteca.service.IelementoPrestableBaseService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/biblioteca")
@CrossOrigin(origins = "*")
@Log4j2
public class ElementoPrestableBaseController {

    private final IelementoPrestableBaseService elementoPrestableBaseService;


    @ApiResponses(value = {
         @ApiResponse(code = 200,message = "ok. se guardo corectamente el elemento",response = ElementoPrestableBaseDTO.class),
         @ApiResponse(code = 400,message = "no llenaste los datos correctamente",response = String.class),
         @ApiResponse(code = 500,message = "error inesperado del sistema")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> guardarElementoPrestableBase(@RequestBody @Validated ElementoPrestableBaseDTO
                                                                            elementoPrestableBaseDTO){
        ElementoPrestableBaseDTO elementoPrestableBaseResponse = elementoPrestableBaseService.
                                                                crearElementoPrestableBase(elementoPrestableBaseDTO);
        try{
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(elementoPrestableBaseResponse));
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "ok. se guardo corectamente el elemento",response = ElementoPrestableBaseDTO.class),
            @ApiResponse(code = 400,message = "no llenaste los datos correctamente",response = String.class),
            @ApiResponse(code = 500,message = "error inesperado del sistema")
    })
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> actualizarElementoPrestableBase(@RequestBody @Validated ElementoPrestableBaseDTO
                                                                       elementoPrestableBaseDTO){
        ElementoPrestableBaseDTO elementoPrestableBaseResponse = elementoPrestableBaseService
                                                                .actualizarElementoPrestableBase(elementoPrestableBaseDTO);
        try{
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(elementoPrestableBaseResponse));
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "ok. se guardo corectamente el elemento",response = ElementoPrestableBaseDTO.class),
            @ApiResponse(code = 400,message = "no llenaste los datos correctamente",response = String.class),
            @ApiResponse(code = 500,message = "error inesperado del sistema")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> BuscarElementoPrestableBase(@Param("idElementoPrestableBaseDTO") Integer idElementoPrestableBaseDTO ){
        ElementoPrestableBaseDTO elementoPrestableBaseResponse = elementoPrestableBaseService.buscarElementoPrestableBase(idElementoPrestableBaseDTO);
        try{
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(elementoPrestableBaseResponse));
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "ok. se guardo corectamente el elemento",response = ElementoPrestableBaseDTO.class),
            @ApiResponse(code = 400,message = "no llenaste los datos correctamente",response = String.class),
            @ApiResponse(code = 500,message = "error inesperado del sistema")
    })
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> borrarElementoPrestableBase(@RequestBody @Validated ElementoPrestableBaseDTO
                                                                          elementoPrestableBaseDTO ){
        String elementoPrestableBaseResponse = elementoPrestableBaseService.borrarElementoPrestableBase(elementoPrestableBaseDTO);
        try{
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(elementoPrestableBaseResponse));
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }




}
