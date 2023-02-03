package br.com.pokeexplorer.exception;

import lombok.Getter;

@Getter
public class PokemonNotFoundOnAreaException  extends RuntimeException {
    private final String message;

    public PokemonNotFoundOnAreaException(String message) {
        super(message);
        this.message = message;
    }
}
