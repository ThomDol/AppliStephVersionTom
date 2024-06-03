package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.AntecedentAdulteEnfantDto;
import org.gestion_patient.service.AntecedentAdulteEnfantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/antecedent")
public class AntecedentAdulteEnfantController {
    private AntecedentAdulteEnfantService antecedentAdulteEnfantServiceService;

    @PostMapping("/{idPatient}")
    public ResponseEntity<AntecedentAdulteEnfantDto> createAntecedent (@RequestBody AntecedentAdulteEnfantDto antecedentAdulteEnfantDto, @PathVariable int idPatient) throws Exception {
        AntecedentAdulteEnfantDto antecedentAdulteEnfantDtoSaved = antecedentAdulteEnfantServiceService.create(antecedentAdulteEnfantDto,idPatient);
        return new ResponseEntity<>(antecedentAdulteEnfantDtoSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{idAntecedent}")
    public ResponseEntity<AntecedentAdulteEnfantDto> updateAntecedent (@PathVariable int idAntecedent,@RequestBody AntecedentAdulteEnfantDto antecedentAdulteEnfantDto ) throws Exception {
        AntecedentAdulteEnfantDto antecedentAdulteEnfantDtoUpdated = antecedentAdulteEnfantServiceService.update(idAntecedent,antecedentAdulteEnfantDto);
        return new ResponseEntity<>(antecedentAdulteEnfantDtoUpdated,HttpStatus.OK);
    }

    @GetMapping("/{idPatient}")
    public ResponseEntity<AntecedentAdulteEnfantDto> getAntecedentssanteByIdPatient (@PathVariable int idPatient) throws Exception {
        AntecedentAdulteEnfantDto antecedentAdulteEnfantDto = antecedentAdulteEnfantServiceService.getByidPatient(idPatient);
        return new ResponseEntity<>(antecedentAdulteEnfantDto,HttpStatus.OK);
    }

}
