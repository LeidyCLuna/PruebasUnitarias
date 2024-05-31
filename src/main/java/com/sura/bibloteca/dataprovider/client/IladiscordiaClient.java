package com.sura.bibloteca.dataprovider.client;


import com.sura.bibloteca.dataprovider.model.ListaPreguntasDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${client.ladiscordia.url}",name = "ladiscordia")
public interface IladiscordiaClient {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ListaPreguntasDto> guardarPregunta(@RequestBody ListaPreguntasDto listaPreguntasDto) ;

}