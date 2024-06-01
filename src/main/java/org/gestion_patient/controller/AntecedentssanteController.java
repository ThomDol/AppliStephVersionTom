package org.gestion_patient.controller;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gestion_patient.entityDto.AntecedentssanteDto;
import org.gestion_patient.service.AntecedentssanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/antecedent")
public class AntecedentssanteController {

    @Autowired
    private AntecedentssanteService antecedentssanteService;

    @PostMapping("/{idPatient}")
    public ResponseEntity<AntecedentssanteDto> createAntecedent (@RequestBody AntecedentssanteDto antecedentssanteDto, @PathVariable int idPatient) throws Exception {
        AntecedentssanteDto antecedentssanteDtoSaved = antecedentssanteService.create(antecedentssanteDto,idPatient);
        return new ResponseEntity<>(antecedentssanteDtoSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AntecedentssanteDto> updateAntecedent (@PathVariable int id,@RequestBody AntecedentssanteDto antecedentssanteDto ) throws Exception {
        AntecedentssanteDto antecedentssanteDtoUpdated = antecedentssanteService.update(id,antecedentssanteDto);
        return new ResponseEntity<>(antecedentssanteDtoUpdated,HttpStatus.OK);
    }

    @GetMapping("/{idPatient}")
    public ResponseEntity<AntecedentssanteDto> getAntecedentssanteByIdPatient (@PathVariable int idPatient) throws Exception {
        AntecedentssanteDto antecedentssanteDto = antecedentssanteService.getByidPatient(idPatient);
        return new ResponseEntity<>(antecedentssanteDto,HttpStatus.OK);
    }
}