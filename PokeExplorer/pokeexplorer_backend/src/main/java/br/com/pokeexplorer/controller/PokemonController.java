package br.com.pokeexplorer.controller;

import br.com.pokeexplorer.exception.EmptyListPokemonException;
import br.com.pokeexplorer.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pokemon")
public class PokemonController {
    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping(path = "/pokemons")
    public ResponseEntity<?> listAllPokemon(){
        try {
            return ResponseEntity.ok(service.listAll());
        } catch (EmptyListPokemonException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
