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

    @GetMapping(path = "/areas/{PokemonId}")
    public ResponseEntity<?> listAllAreasByPokemon(@PathVariable Long PokemonId){
        try {
            return ResponseEntity.ok(service.findAllByPokemon(PokemonId));
        } catch (EmptyListPokemonException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping(path = "/areas/{PokemonId}/{AreaId}")
    public ResponseEntity<?> findClosestArea(@PathVariable Long PokemonId,@PathVariable Long AreaId, @RequestBody List<PokemonDTO> listPokemonDTO){
        try {
            return ResponseEntity.ok(service.findClosestArea(PokemonId, AreaId, listPokemonDTO));
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
