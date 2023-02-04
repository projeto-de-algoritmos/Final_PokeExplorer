package br.com.pokeexplorer.controller;

import br.com.pokeexplorer.dto.PokemonDTO;
import br.com.pokeexplorer.exception.EmptyListPokemonException;
import br.com.pokeexplorer.exception.PokemonNotFoundOnAreaException;
import br.com.pokeexplorer.service.AreaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("area")
public class AreaController {
    private final AreaService service;

    public AreaController(AreaService service) {
        this.service = service;
    }

    @PostMapping(path = "/areas/{StartAreaId}/{FinalAreaId}")
    public ResponseEntity<?> findClosestArea(@PathVariable Long StartAreaId,@PathVariable Long FinalAreaId, @RequestBody List<PokemonDTO> listPokemonDTO){
        try {
            return ResponseEntity.ok(service.findClosestArea(StartAreaId, FinalAreaId, listPokemonDTO));
        } catch (PokemonNotFoundOnAreaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping(path = "/areas")
    public ResponseEntity<?> listAllAreas(){
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (EmptyListPokemonException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
