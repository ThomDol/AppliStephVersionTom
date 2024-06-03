package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.AccouchementDto;
import org.gestion_patient.entityDto.GrossesseDto;
import org.gestion_patient.service.AccouchementService;
import org.gestion_patient.service.GrossesseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/grossesse")
public class GrossesseController {
    private GrossesseService grossesseService;


    @PostMapping("/{idPatient}")
    public ResponseEntity<GrossesseDto> createGrossesse(@RequestBody GrossesseDto grossesseDto, @PathVariable int idPatient ) {
        GrossesseDto createAccouchement = grossesseService.create(grossesseDto,idPatient);
        return new ResponseEntity<>(createAccouchement, HttpStatus.CREATED);
    }
    @GetMapping("{idGrossesse}")
    public ResponseEntity<GrossesseDto> getGrossesseById(@PathVariable int idGrossesse) {
        GrossesseDto grossesseDto = grossesseService.getById(idGrossesse);
        return new ResponseEntity<>(grossesseDto, HttpStatus.OK);
    }
    @GetMapping("patient/{idPatient}")
    public ResponseEntity<List<GrossesseDto>> getAllGrossesseByPatientId(@PathVariable int idPatient)  {
        List<GrossesseDto> grossesseDtoList = grossesseService.getAllByidPatient(idPatient);
        return new ResponseEntity<>(grossesseDtoList, HttpStatus.OK);
    }

    @PutMapping("/{idGrossesse}")
    public ResponseEntity<GrossesseDto> updateGrossesse (@PathVariable int idGrossesse,@RequestBody GrossesseDto grossesseDto){
        GrossesseDto grossesseDtoUpdated = grossesseService.update(idGrossesse,grossesseDto);
        return new ResponseEntity<>(grossesseDtoUpdated,HttpStatus.OK);
    }


}
