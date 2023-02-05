package br.com.pokeexplorer.dto;

import br.com.pokeexplorer.model.Area;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class SimpleAreaDTO {
    private String name;
    private String url;
    private String trainer;
    private List<PokemonDTO> listPokemonDTO;

    public SimpleAreaDTO(Area area, List<PokemonDTO> listPokemonDTO){
        this.name = area.getName();
        this.url = area.getUrl();
        this.trainer = area.getTrainer();
        this.listPokemonDTO = listPokemonDTO;
    }
}
