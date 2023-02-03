package br.com.pokeexplorer.repository;

import br.com.pokeexplorer.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

}
