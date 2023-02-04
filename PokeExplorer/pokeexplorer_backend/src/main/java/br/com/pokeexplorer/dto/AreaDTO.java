package br.com.pokeexplorer.dto;

import br.com.pokeexplorer.model.Area;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class AreaDTO {
    private Long id;
    private String name;
    private String url;
    private String trainer;
    private List<PokemonDTO> listPokemonDTO;

    public AreaDTO(Area area){
        this.id = area.getId();
        this.name = area.getName();
        this.url = area.getUrl();
    }

    public AreaDTO(Area area, List<PokemonDTO> listPokemonDTO){
        this.id = area.getId();
        this.name = area.getName();
        this.url = area.getUrl();
        this.trainer = area.getTrainer();
        this.listPokemonDTO = listPokemonDTO;
    }
}
