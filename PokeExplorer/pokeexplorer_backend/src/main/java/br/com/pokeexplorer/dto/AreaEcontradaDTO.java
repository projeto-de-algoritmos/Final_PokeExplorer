package br.com.pokeexplorer.dto;

import br.com.pokeexplorer.model.Area;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class AreaEcontradaDTO {
    private String name;
    private String url;
    private List<SimpleAreaDTO> listAreaDTO;
    private String trainer;
    private List<PokemonDTO> listPokemonDTO;
}
