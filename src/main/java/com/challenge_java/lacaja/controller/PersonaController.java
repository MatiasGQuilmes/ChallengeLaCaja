package com.challenge_java.lacaja.controller;

import com.challenge_java.lacaja.dto.EstadisticasDTO;
import com.challenge_java.lacaja.dto.PersonaDTO;
import com.challenge_java.lacaja.exceptions.PersonaConIdNoEncontradoException;
import com.challenge_java.lacaja.exceptions.PersonaNoEncontradaException;
import com.challenge_java.lacaja.model.Persona;
import com.challenge_java.lacaja.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class PersonaController {

    @Autowired
    IPersonaService personaService;

    @GetMapping("/personas")
    public ResponseEntity<List<PersonaDTO>> personasProcesadas() {
        return new ResponseEntity<>(personaService.listaPersonasProcesadas(), HttpStatus.OK);
    }

    @GetMapping("/personas/{id}")
    public ResponseEntity<?> personaProcesada(@PathVariable Long id) {
            return ResponseEntity.ok(personaService.obtenerPersonaProcesada(id));
    }
    @DeleteMapping("/personas/eliminar/{id}")
    public ResponseEntity<Object> eliminarPersona(@PathVariable Long id) {
            return ResponseEntity.ok(personaService.eliminarPersona(id));
    }


    @PatchMapping("/personas/editar/{id}")
    public ResponseEntity<Object> actualizarPersona(@PathVariable Long id, @RequestBody Persona personaAct){
        return new ResponseEntity<>(personaService.actualizarPersona(id, personaAct), HttpStatus.OK);
    }

    @GetMapping("/estadisticasEdades")
    public ResponseEntity<EstadisticasDTO> estadisticasEdadPersonas() {
        EstadisticasDTO estadisticas = personaService.calcularEstadisticasEdadPersonas();
        return new ResponseEntity<>(estadisticas, HttpStatus.OK);
    }


}
