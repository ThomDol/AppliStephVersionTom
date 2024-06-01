package org.gestion_patient.controller;

import lombok.AllArgsConstructor;
import org.gestion_patient.entity.MesurerId;
import org.gestion_patient.entityDto.MesurerDto;
import org.gestion_patient.entityDto.PhysiqueDto;
import org.gestion_patient.entityDto.PratiquerDto;
import org.gestion_patient.service.MesurerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/mesurer")
public class MesurerController {
    private MesurerService mesurerService;

    @PostMapping("/{idPatient}")
    public ResponseEntity<MesurerDto> createMesurer (@RequestBody PhysiqueDto physiqueDto, @PathVariable int idPatient){
        MesurerDto savedMesurer = mesurerService.createByPatient(physiqueDto,idPatient);
        return new ResponseEntity<>(savedMesurer, HttpStatus.CREATED);
    }

    @GetMapping("/{idPatient}")
    public ResponseEntity<List<MesurerDto>> getAllMesurerByPatient (@PathVariable int idPatient){
        List<MesurerDto> mesurerDtoList =  mesurerService.getAllMesurersByIdPatient(idPatient);
        return new ResponseEntity<>(mesurerDtoList,HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<MesurerDto> updateMesurer (@RequestBody MesurerDto mesurerDto){
    MesurerDto mesurerDtoUpdated = mesurerService.update(mesurerDto);
    return new ResponseEntity<>(mesurerDtoUpdated,HttpStatus.OK);
    }
}
