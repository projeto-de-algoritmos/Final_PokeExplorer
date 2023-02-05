package br.com.pokeexplorer.controller;

import br.com.pokeexplorer.dto.PokemonDTO;
import br.com.pokeexplorer.exception.EmptyListPokemonException;
import br.com.pokeexplorer.service.PokemonService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pokemon")
public class PokemonController {
    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping(path = "/listPokemonOptions")
    public ResponseEntity<?> listAll(){
        try {
            return ResponseEntity.ok(service.listAll());
        } catch (EmptyListPokemonException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping(path = "/bestTeam", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> bestTeam(@RequestBody List<PokemonDTO> listPokemonDTO){
        try {
            return ResponseEntity.ok(service.bestTeamPokemon(listPokemonDTO));
        } catch (EmptyListPokemonException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
