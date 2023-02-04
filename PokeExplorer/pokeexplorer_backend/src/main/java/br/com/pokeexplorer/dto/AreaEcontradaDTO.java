package br.com.pokeexplorer.dto;

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
    private List<String> areas;
    private String trainer;
}
