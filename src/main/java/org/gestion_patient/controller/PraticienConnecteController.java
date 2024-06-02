package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entityDto.PraticienconnecteDto;
import org.gestion_patient.service.PraticienConnecteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/praticien")
public class PraticienConnecteController {
    private PraticienConnecteService praticienConnecteService;



    @GetMapping("/profile")
    public ResponseEntity<PraticienconnecteDto> profile (Principal principal) throws Exception {
        PraticienconnecteDto praticienconnecteDto = praticienConnecteService.loadByEmail(principal.getName());
        return new ResponseEntity<>(praticienconnecteDto, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<PraticienconnecteDto> createNewPraticien(@RequestBody PraticienconnecteDto praticienconnecteDto) throws Exception {
        PraticienconnecteDto praticienDtoSaved = praticienConnecteService.create(praticienconnecteDto);
        return new ResponseEntity<>(praticienDtoSaved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PraticienconnecteDto>> getAllPraticien(){
        List<PraticienconnecteDto> praticiens = praticienConnecteService.findAll();
        return new ResponseEntity<>(praticiens,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PraticienconnecteDto> getPraticienById(@PathVariable int id) throws Exception {
        PraticienconnecteDto praticienconnecteDto = praticienConnecteService.findById(id);
        return new ResponseEntity<>(praticienconnecteDto,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PraticienconnecteDto> updatePraticien(@PathVariable int id,@RequestBody PraticienconnecteDto praticienconnecteDto) throws Exception {
        PraticienconnecteDto updatedPraticien = praticienConnecteService.update(id,praticienconnecteDto);
        return new ResponseEntity<>(updatedPraticien,HttpStatus.OK);
    }

}
