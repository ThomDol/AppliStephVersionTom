package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.AntecedentBebeDto;
import org.gestion_patient.service.AntecedentBebeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/antecedentbebe")
public class AntecedentBebeController {
    private AntecedentBebeService antecedentBebeService;

    @PostMapping("/{idPatient}")
    public ResponseEntity<AntecedentBebeDto> createAntecedentBebe (@RequestBody AntecedentBebeDto antecedentBebeDto, @PathVariable int idPatient)  {
        AntecedentBebeDto antecedentBebeDtoSaved = antecedentBebeService.create(antecedentBebeDto,idPatient);
        return new ResponseEntity<>(antecedentBebeDtoSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{idAntecedetnBebe}")
    public ResponseEntity<AntecedentBebeDto> updateAntecedentBebe (@PathVariable int idAntecedetnBebe,@RequestBody AntecedentBebeDto antecedentBebeDto )  {
        AntecedentBebeDto antecedentBebeDtoUpdated = antecedentBebeService.update(idAntecedetnBebe,antecedentBebeDto);
        return new ResponseEntity<>(antecedentBebeDtoUpdated,HttpStatus.OK);
    }

    @GetMapping("/{idPatient}")
    public ResponseEntity<AntecedentBebeDto> getAntecedentBebeByIdPatient (@PathVariable int idPatient)  {
        AntecedentBebeDto antecedentBebeDto = antecedentBebeService.getByidPatient(idPatient);
        return new ResponseEntity<>(antecedentBebeDto,HttpStatus.OK);
    }

}
