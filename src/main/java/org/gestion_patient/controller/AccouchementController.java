package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entity.Accouchement;
import org.gestion_patient.entityDto.AccouchementDto;
import org.gestion_patient.service.AccouchementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/accouchement")
public class AccouchementController {
    private AccouchementService accouchementService;


    @PostMapping("/{idPatient}")
    public ResponseEntity<AccouchementDto> createAccouchement(@RequestBody AccouchementDto accouchementDto,@PathVariable int idPatient ) {
        AccouchementDto accouchementDtoCreated = accouchementService.create(accouchementDto,idPatient);
        return new ResponseEntity<>(accouchementDtoCreated, HttpStatus.CREATED);
    }
    @GetMapping("{idAccouchement}")
    public ResponseEntity<AccouchementDto> getAccouchementById(@PathVariable int idAccouchement) {
        AccouchementDto accouchementDto = accouchementService.getByIdAccouchement(idAccouchement);
        return new ResponseEntity<>(accouchementDto, HttpStatus.OK);
    }
    @GetMapping("patient/{idPatient}")
    public ResponseEntity<List<AccouchementDto>> getAllAccouchementByPatientId(@PathVariable int idPatient)  {
        List<AccouchementDto> accouchementList = accouchementService.getAllByIdPatient(idPatient);
        return new ResponseEntity<>(accouchementList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccouchementDto> updateAccouchement (@PathVariable int id,@RequestBody AccouchementDto accouchementDto){
        AccouchementDto accouchementDtoUpdated = accouchementService.update(id,accouchementDto);
        return new ResponseEntity<>(accouchementDtoUpdated,HttpStatus.OK);
    }


}
