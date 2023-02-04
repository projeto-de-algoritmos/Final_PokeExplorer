package br.com.pokeexplorer.service;

import br.com.pokeexplorer.dto.AreaDTO;
import br.com.pokeexplorer.dto.AreaEcontradaDTO;
import br.com.pokeexplorer.dto.PokemonDTO;
import br.com.pokeexplorer.exception.EmptyListPokemonException;
import br.com.pokeexplorer.exception.PokemonNotFoundOnAreaException;
import br.com.pokeexplorer.model.Area;
import br.com.pokeexplorer.model.Pokemon;
import br.com.pokeexplorer.repository.AreaRepository;
import br.com.pokeexplorer.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AreaService {
    private final AreaRepository areaRepository;
    private final PokemonRepository pokemonRepository;
    private final PokemonService pokemonService;

    public AreaService(AreaRepository areaRepository, PokemonRepository pokemonRepository, PokemonService pokemonService) {
        this.areaRepository = areaRepository;
        this.pokemonRepository = pokemonRepository;
        this.pokemonService = pokemonService;
    }

    public List<AreaDTO> findAll() {
        List<AreaDTO> areaDTOList = new ArrayList<>();
        List<Area> areaList = areaRepository.findAll();
        areaList.forEach(area -> areaDTOList.add(new AreaDTO(area)));
        if (areaDTOList.isEmpty()) {
            throw new EmptyListPokemonException("Não existe pokêmon's cadastrados!");
        }
        return areaDTOList;
    }

    public AreaEcontradaDTO findClosestArea(Long startAreaId, Long finalAreaId, List<PokemonDTO> listPokemonDTO){

        //ids de areas onde o pokemon selecionado se encontra
        // List<Long> pokemonAreas= findAllByPokemon(idPokemon);
        List<Area> areas = areaRepository.findAll();

        //iniciar grafo
        MapGraph grafo = initAreaGraph(listPokemonDTO);

        //pegar o nó que irá iniciar a percurso
        List<AreaNode> initialNode = grafo.getAreaNodes().stream().filter(a -> a.getId().equals(startAreaId)).collect(Collectors.toList());

        AreaNode finalNode = grafo.getAreaNodes().stream().filter(a -> a.getId().equals(finalAreaId)).collect(Collectors.toList()).get(0);
        //aplicação do dijkstra
        calculateShortestPathFromSource(grafo, initialNode.get(0));

        //iniciar o iterator para percorrer o grafo
        Iterator<AreaNode> nodeIterator = grafo.getAreaNodes().iterator();

        //iniciar node inicial, com distância maior que a possivel(será substituído pelo primeiro)
        AreaNode closestNode = new AreaNode(1000L);
        closestNode.setDistance(1000);

        //percorre os nodes e vê se faz parte do que quer, com isso compara para ver se é o menor
        while (nodeIterator.hasNext()){
            finalNode = nodeIterator.next();
            if (areas.contains(finalNode.getId())){
                if(closestNode.getDistance() > finalNode.getDistance()){
                    closestNode = finalNode;
                }
            }
        }

        AreaEcontradaDTO dto = new AreaEcontradaDTO();
        try {
            Optional<Area> optionalArea = areaRepository.findById(closestNode.id);
            dto.setName(optionalArea.get().getName());
            dto.setUrl(optionalArea.get().getUrl());
            dto.setTrainer(optionalArea.get().getTrainer());
            List<String> names = new ArrayList<String>();
            closestNode.shortestPath.forEach(path -> {
                Optional<Area> areaOpt = areaRepository.findById(path.getId());
                names.add(areaOpt.get().getName());
                dto.setAreas(names);

            });
        } catch (NoSuchElementException e){
            throw new PokemonNotFoundOnAreaException("Não foi encontrados locais para este pokémon");
        }
        return dto;
    }

    public MapGraph initAreaGraph(List<PokemonDTO> listPokemonDTO){
        AreaNode node1 = new AreaNode(1L);
        AreaNode node2 = new AreaNode(2L);
        AreaNode node3 = new AreaNode(3L);
        AreaNode node4 = new AreaNode(4L);
        AreaNode node5 = new AreaNode(5L);
        AreaNode node6 = new AreaNode(6L);
        AreaNode node8 = new AreaNode(8L);
        AreaNode node7 = new AreaNode(7L);
        AreaNode node9 = new AreaNode(9L);
        AreaNode node10 = new AreaNode(10L);
        AreaNode node11 = new AreaNode(11L);
        AreaNode node12 = new AreaNode(12L);
        AreaNode node13 = new AreaNode(13L);
        AreaNode node14 = new AreaNode(14L);
        AreaNode node15 = new AreaNode(15L);
        AreaNode node16 = new AreaNode(16L);
        AreaNode node17 = new AreaNode(17L);
        AreaNode node18 = new AreaNode(18L);
        AreaNode node19 = new AreaNode(19L);
        AreaNode node20 = new AreaNode(20L);
        AreaNode node21 = new AreaNode(21L);
        AreaNode node22 = new AreaNode(22L);
        AreaNode node23 = new AreaNode(23L);
        AreaNode node24 = new AreaNode(24L);
        AreaNode node25 = new AreaNode(25L);
        AreaNode node26 = new AreaNode(26L);
        AreaNode node27 = new AreaNode(27L);
        AreaNode node28 = new AreaNode(28L);
        AreaNode node29 = new AreaNode(29L);
        AreaNode node30 = new AreaNode(30L);
        AreaNode node31 = new AreaNode(31L);
        AreaNode node32 = new AreaNode(32L);
        AreaNode node33 = new AreaNode(33L);
        AreaNode node34 = new AreaNode(34L);
        AreaNode node35 = new AreaNode(35L);
        AreaNode node36 = new AreaNode(36L);
        AreaNode node37 = new AreaNode(37L);
        AreaNode node38 = new AreaNode(38L);
        AreaNode node39 = new AreaNode(39L);
        AreaNode node40 = new AreaNode(40L);
        AreaNode node41 = new AreaNode(41L);
        AreaNode node42 = new AreaNode(42L);
        AreaNode node43 = new AreaNode(43L);
        AreaNode node44 = new AreaNode(44L);
        AreaNode node45 = new AreaNode(45L);
        AreaNode node46 = new AreaNode(46L);
        AreaNode node47 = new AreaNode(47L);

        Integer totalDamageMyTeam = pokemonService.totalDamage(listPokemonDTO);
        List<Integer> listAllTotalDamage = new ArrayList<Integer>();

        List<PokemonDTO> pokemonDTOSEnemyNode1 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode1 = pokemonService.totalDamage(pokemonDTOSEnemyNode1);
        listAllTotalDamage.add(totalDamageEnemyNode1);

        List<PokemonDTO> pokemonDTOSEnemyNode2 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode2 = pokemonService.totalDamage(pokemonDTOSEnemyNode2);
        listAllTotalDamage.add(totalDamageEnemyNode2);

        List<PokemonDTO> pokemonDTOSEnemyNode3 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode3 = pokemonService.totalDamage(pokemonDTOSEnemyNode3);
        listAllTotalDamage.add(totalDamageEnemyNode3);

        List<PokemonDTO> pokemonDTOSEnemyNode4 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode4 = pokemonService.totalDamage(pokemonDTOSEnemyNode4);
        listAllTotalDamage.add(totalDamageEnemyNode4);

        List<PokemonDTO> pokemonDTOSEnemyNode5 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode5 = pokemonService.totalDamage(pokemonDTOSEnemyNode5);
        listAllTotalDamage.add(totalDamageEnemyNode5);

        List<PokemonDTO> pokemonDTOSEnemyNode6 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode6 = pokemonService.totalDamage(pokemonDTOSEnemyNode6);
        listAllTotalDamage.add(totalDamageEnemyNode6);

        List<PokemonDTO> pokemonDTOSEnemyNode7 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode7 = pokemonService.totalDamage(pokemonDTOSEnemyNode7);
        listAllTotalDamage.add(totalDamageEnemyNode7);

        List<PokemonDTO> pokemonDTOSEnemyNode8 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode8 = pokemonService.totalDamage(pokemonDTOSEnemyNode8);
        listAllTotalDamage.add(totalDamageEnemyNode8);

        List<PokemonDTO> pokemonDTOSEnemyNode9 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode9 = pokemonService.totalDamage(pokemonDTOSEnemyNode9);
        listAllTotalDamage.add(totalDamageEnemyNode9);

        List<PokemonDTO> pokemonDTOSEnemyNode10 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode10 = pokemonService.totalDamage(pokemonDTOSEnemyNode10);
        listAllTotalDamage.add(totalDamageEnemyNode10);

        List<PokemonDTO> pokemonDTOSEnemyNode11 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode11 = pokemonService.totalDamage(pokemonDTOSEnemyNode11);
        listAllTotalDamage.add(totalDamageEnemyNode11);

        List<PokemonDTO> pokemonDTOSEnemyNode12 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode12 = pokemonService.totalDamage(pokemonDTOSEnemyNode12);
        listAllTotalDamage.add(totalDamageEnemyNode12);

        List<PokemonDTO> pokemonDTOSEnemyNode13 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode13 = pokemonService.totalDamage(pokemonDTOSEnemyNode13);
        listAllTotalDamage.add(totalDamageEnemyNode13);

        List<PokemonDTO> pokemonDTOSEnemyNode14 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode14 = pokemonService.totalDamage(pokemonDTOSEnemyNode14);
        listAllTotalDamage.add(totalDamageEnemyNode14);

        List<PokemonDTO> pokemonDTOSEnemyNode15 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode15 = pokemonService.totalDamage(pokemonDTOSEnemyNode15);
        listAllTotalDamage.add(totalDamageEnemyNode15);

        List<PokemonDTO> pokemonDTOSEnemyNode16 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode16 = pokemonService.totalDamage(pokemonDTOSEnemyNode16);
        listAllTotalDamage.add(totalDamageEnemyNode16);

        List<PokemonDTO> pokemonDTOSEnemyNode17 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode17 = pokemonService.totalDamage(pokemonDTOSEnemyNode17);
        listAllTotalDamage.add(totalDamageEnemyNode17);

        List<PokemonDTO> pokemonDTOSEnemyNode18 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode18 = pokemonService.totalDamage(pokemonDTOSEnemyNode18);
        listAllTotalDamage.add(totalDamageEnemyNode18);

        List<PokemonDTO> pokemonDTOSEnemyNode19 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode19 = pokemonService.totalDamage(pokemonDTOSEnemyNode19);
        listAllTotalDamage.add(totalDamageEnemyNode19);

        List<PokemonDTO> pokemonDTOSEnemyNode20 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode20 = pokemonService.totalDamage(pokemonDTOSEnemyNode20);
        listAllTotalDamage.add(totalDamageEnemyNode20);

        List<PokemonDTO> pokemonDTOSEnemyNode21 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode21 = pokemonService.totalDamage(pokemonDTOSEnemyNode21);
        listAllTotalDamage.add(totalDamageEnemyNode21);

        List<PokemonDTO> pokemonDTOSEnemyNode22 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode22 = pokemonService.totalDamage(pokemonDTOSEnemyNode22);
        listAllTotalDamage.add(totalDamageEnemyNode22);

        List<PokemonDTO> pokemonDTOSEnemyNode23 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode23 = pokemonService.totalDamage(pokemonDTOSEnemyNode23);
        listAllTotalDamage.add(totalDamageEnemyNode23);

        List<PokemonDTO> pokemonDTOSEnemyNode24 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode24 = pokemonService.totalDamage(pokemonDTOSEnemyNode24);
        listAllTotalDamage.add(totalDamageEnemyNode24);

        List<PokemonDTO> pokemonDTOSEnemyNode25 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode25 = pokemonService.totalDamage(pokemonDTOSEnemyNode25);
        listAllTotalDamage.add(totalDamageEnemyNode25);

        List<PokemonDTO> pokemonDTOSEnemyNode26 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode26 = pokemonService.totalDamage(pokemonDTOSEnemyNode26);
        listAllTotalDamage.add(totalDamageEnemyNode26);

        List<PokemonDTO> pokemonDTOSEnemyNode27= pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode27 = pokemonService.totalDamage(pokemonDTOSEnemyNode27);
        listAllTotalDamage.add(totalDamageEnemyNode27);

        List<PokemonDTO> pokemonDTOSEnemyNode28 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode28 = pokemonService.totalDamage(pokemonDTOSEnemyNode28);
        listAllTotalDamage.add(totalDamageEnemyNode28);

        List<PokemonDTO> pokemonDTOSEnemyNode29 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode29 = pokemonService.totalDamage(pokemonDTOSEnemyNode29);
        listAllTotalDamage.add(totalDamageEnemyNode29);

        List<PokemonDTO> pokemonDTOSEnemyNode30 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode30 = pokemonService.totalDamage(pokemonDTOSEnemyNode30);
        listAllTotalDamage.add(totalDamageEnemyNode30);

        List<PokemonDTO> pokemonDTOSEnemyNode31 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode31 = pokemonService.totalDamage(pokemonDTOSEnemyNode31);
        listAllTotalDamage.add(totalDamageEnemyNode31);

        List<PokemonDTO> pokemonDTOSEnemyNode32 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode32 = pokemonService.totalDamage(pokemonDTOSEnemyNode32);
        listAllTotalDamage.add(totalDamageEnemyNode32);

        List<PokemonDTO> pokemonDTOSEnemyNode33 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode33 = pokemonService.totalDamage(pokemonDTOSEnemyNode33);
        listAllTotalDamage.add(totalDamageEnemyNode33);

        List<PokemonDTO> pokemonDTOSEnemyNode34 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode34 = pokemonService.totalDamage(pokemonDTOSEnemyNode34);
        listAllTotalDamage.add(totalDamageEnemyNode34);

        List<PokemonDTO> pokemonDTOSEnemyNode35 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode35 = pokemonService.totalDamage(pokemonDTOSEnemyNode35);
        listAllTotalDamage.add(totalDamageEnemyNode35);

        List<PokemonDTO> pokemonDTOSEnemyNode36 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode36 = pokemonService.totalDamage(pokemonDTOSEnemyNode36);
        listAllTotalDamage.add(totalDamageEnemyNode36);

        List<PokemonDTO> pokemonDTOSEnemyNode37= pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode37 = pokemonService.totalDamage(pokemonDTOSEnemyNode37);
        listAllTotalDamage.add(totalDamageEnemyNode37);

        List<PokemonDTO> pokemonDTOSEnemyNode38 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode38 = pokemonService.totalDamage(pokemonDTOSEnemyNode38);
        listAllTotalDamage.add(totalDamageEnemyNode38);

        List<PokemonDTO> pokemonDTOSEnemyNode39 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode39 = pokemonService.totalDamage(pokemonDTOSEnemyNode39);
        listAllTotalDamage.add(totalDamageEnemyNode39);

        List<PokemonDTO> pokemonDTOSEnemyNode40 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode40 = pokemonService.totalDamage(pokemonDTOSEnemyNode40);
        listAllTotalDamage.add(totalDamageEnemyNode40);

        List<PokemonDTO> pokemonDTOSEnemyNode41 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode41 = pokemonService.totalDamage(pokemonDTOSEnemyNode41);
        listAllTotalDamage.add(totalDamageEnemyNode41);

        List<PokemonDTO> pokemonDTOSEnemyNode42 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode42 = pokemonService.totalDamage(pokemonDTOSEnemyNode42);
        listAllTotalDamage.add(totalDamageEnemyNode42);

        List<PokemonDTO> pokemonDTOSEnemyNode43 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode43 = pokemonService.totalDamage(pokemonDTOSEnemyNode43);
        listAllTotalDamage.add(totalDamageEnemyNode43);

        List<PokemonDTO> pokemonDTOSEnemyNode44 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode44 = pokemonService.totalDamage(pokemonDTOSEnemyNode44);
        listAllTotalDamage.add(totalDamageEnemyNode44);

        List<PokemonDTO> pokemonDTOSEnemyNode45 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode45 = pokemonService.totalDamage(pokemonDTOSEnemyNode45);
        listAllTotalDamage.add(totalDamageEnemyNode45);

        List<PokemonDTO> pokemonDTOSEnemyNode46 = pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode46 = pokemonService.totalDamage(pokemonDTOSEnemyNode46);
        listAllTotalDamage.add(totalDamageEnemyNode46);

        List<PokemonDTO> pokemonDTOSEnemyNode47= pokemonService.calculateBestTeamEnemy();
        Integer totalDamageEnemyNode47 = pokemonService.totalDamage(pokemonDTOSEnemyNode47);
        listAllTotalDamage.add(totalDamageEnemyNode47);


        List<Integer> listDifferences = listDifferences(totalDamageMyTeam, listAllTotalDamage);

        node1.addDestination(node2, listDifferences.get(1));
        node1.addDestination(node40, listDifferences.get(39));

        node2.addDestination(node1, listDifferences.get(0));
        node2.addDestination(node3, listDifferences.get(2));

        node3.addDestination(node41, listDifferences.get(40));
        node3.addDestination(node2, listDifferences.get(1));
        node3.addDestination(node4, listDifferences.get(3));

        node4.addDestination(node6, listDifferences.get(5));
        node4.addDestination(node3, listDifferences.get(2));
        node4.addDestination(node5, listDifferences.get(4));

        node5.addDestination(node47, listDifferences.get(46));
        node5.addDestination(node4, listDifferences.get(3));

        node6.addDestination(node4, listDifferences.get(3));
        node6.addDestination(node25, listDifferences.get(24));
        node6.addDestination(node16, listDifferences.get(15));

        node7.addDestination(node47, listDifferences.get(46));
        node7.addDestination(node8, listDifferences.get(7));

        node8.addDestination(node7, listDifferences.get(6));
        node8.addDestination(node9, listDifferences.get(9));

        node9.addDestination(node8, listDifferences.get(7));
        node9.addDestination(node11, listDifferences.get(10));

        node10.addDestination(node11, listDifferences.get(10));

        node11.addDestination(node10, listDifferences.get(9));
        node11.addDestination(node9, listDifferences.get(8));
        node11.addDestination(node44, listDifferences.get(43));
        node11.addDestination(node24, listDifferences.get(23));
        node11.addDestination(node12, listDifferences.get(11));

        node12.addDestination(node11, listDifferences.get(10));
        node12.addDestination(node13, listDifferences.get(12));

        node13.addDestination(node12, listDifferences.get(11));
        node13.addDestination(node14, listDifferences.get(13));
        node13.addDestination(node17, listDifferences.get(16));
        node13.addDestination(node15, listDifferences.get(14));
        node13.addDestination(node19, listDifferences.get(18));

        node14.addDestination(node13, listDifferences.get(12));

        node15.addDestination(node13, listDifferences.get(12));
        node15.addDestination(node16, listDifferences.get(15));

        node16.addDestination(node15, listDifferences.get(14));
        node16.addDestination(node6, listDifferences.get(5));

        node17.addDestination(node13, listDifferences.get(12));
        node17.addDestination(node18, listDifferences.get(17));

        node18.addDestination(node17, listDifferences.get(16));
        node18.addDestination(node32, listDifferences.get(31));

        node19.addDestination(node13, listDifferences.get(12));
        node19.addDestination(node46, listDifferences.get(45));

        node20.addDestination(node46, listDifferences.get(45));

        node21.addDestination(node46, listDifferences.get(45));
        node21.addDestination(node24, listDifferences.get(23));

        node22.addDestination(node24, listDifferences.get(23));

        node23.addDestination(node24, listDifferences.get(23));

        node24.addDestination(node21, listDifferences.get(20));
        node24.addDestination(node22, listDifferences.get(21));
        node24.addDestination(node23, listDifferences.get(22));

        node25.addDestination(node6, listDifferences.get(5));
        node25.addDestination(node26, listDifferences.get(25));

        node26.addDestination(node46, listDifferences.get(45));
        node26.addDestination(node25, listDifferences.get(24));
        node26.addDestination(node27, listDifferences.get(26));

        node27.addDestination(node26, listDifferences.get(25));
        node27.addDestination(node28, listDifferences.get(27));

        node28.addDestination(node27, listDifferences.get(26));
        node28.addDestination(node29, listDifferences.get(28));

        node29.addDestination(node28, listDifferences.get(27));
        node29.addDestination(node31, listDifferences.get(30));

        node30.addDestination(node31, listDifferences.get(30));

        node31.addDestination(node29, listDifferences.get(28));
        node31.addDestination(node30, listDifferences.get(29));
        node31.addDestination(node35, listDifferences.get(34));
        node31.addDestination(node34, listDifferences.get(33));

        node32.addDestination(node18, listDifferences.get(17));
        node32.addDestination(node33, listDifferences.get(32));

        node33.addDestination(node32, listDifferences.get(31));
        node33.addDestination(node34, listDifferences.get(33));

        node34.addDestination(node31, listDifferences.get(30));
        node34.addDestination(node33, listDifferences.get(32));

        node35.addDestination(node31, listDifferences.get(30));
        node35.addDestination(node36, listDifferences.get(35));

        node36.addDestination(node35, listDifferences.get(34));
        node36.addDestination(node37, listDifferences.get(36));

        node37.addDestination(node36, listDifferences.get(35));
        node37.addDestination(node38, listDifferences.get(37));

        node38.addDestination(node37, listDifferences.get(36));
        node38.addDestination(node40, listDifferences.get(39));
        node38.addDestination(node39, listDifferences.get(38));

        node39.addDestination(node38, listDifferences.get(37));

        node40.addDestination(node38, listDifferences.get(37));
        node40.addDestination(node1, listDifferences.get(0));

        node41.addDestination(node3, listDifferences.get(2));
        node41.addDestination(node42, listDifferences.get(41));

        node42.addDestination(node41, listDifferences.get(40));
        node42.addDestination(node43, listDifferences.get(42));

        node43.addDestination(node42, listDifferences.get(41));

        node44.addDestination(node11, listDifferences.get(10));
        node44.addDestination(node45, listDifferences.get(44));

        node45.addDestination(node44, listDifferences.get(43));

        node46.addDestination(node20, listDifferences.get(19));
        node46.addDestination(node26, listDifferences.get(25));
        node46.addDestination(node19, listDifferences.get(18));
        node46.addDestination(node21, listDifferences.get(20));

        node47.addDestination(node7, listDifferences.get(6));
        node47.addDestination(node5, listDifferences.get(4));

        MapGraph graph = new MapGraph();

        graph.addAreaNode(node1);
        graph.addAreaNode(node2);
        graph.addAreaNode(node3);
        graph.addAreaNode(node4);
        graph.addAreaNode(node5);
        graph.addAreaNode(node6);
        graph.addAreaNode(node7);
        graph.addAreaNode(node8);
        graph.addAreaNode(node9);
        graph.addAreaNode(node10);
        graph.addAreaNode(node11);
        graph.addAreaNode(node12);
        graph.addAreaNode(node13);
        graph.addAreaNode(node14);
        graph.addAreaNode(node15);
        graph.addAreaNode(node16);
        graph.addAreaNode(node17);
        graph.addAreaNode(node18);
        graph.addAreaNode(node19);
        graph.addAreaNode(node20);
        graph.addAreaNode(node21);
        graph.addAreaNode(node22);
        graph.addAreaNode(node23);
        graph.addAreaNode(node24);
        graph.addAreaNode(node25);
        graph.addAreaNode(node26);
        graph.addAreaNode(node27);
        graph.addAreaNode(node28);
        graph.addAreaNode(node29);
        graph.addAreaNode(node30);
        graph.addAreaNode(node31);
        graph.addAreaNode(node32);
        graph.addAreaNode(node33);
        graph.addAreaNode(node34);
        graph.addAreaNode(node35);
        graph.addAreaNode(node36);
        graph.addAreaNode(node37);
        graph.addAreaNode(node38);
        graph.addAreaNode(node39);
        graph.addAreaNode(node40);
        graph.addAreaNode(node41);
        graph.addAreaNode(node42);
        graph.addAreaNode(node43);
        graph.addAreaNode(node44);
        graph.addAreaNode(node45);
        graph.addAreaNode(node46);
        graph.addAreaNode(node47);


        return graph;
    }

    public static class MapGraph {

        private Set<AreaNode> nodes = new HashSet<>();

        public void addAreaNode(AreaNode nodeA) {
            nodes.add(nodeA);
        }

        public Set<AreaNode> getAreaNodes() {
            return nodes;
        }

        public void setAreaNodes(Set<AreaNode> nodes) {
            this.nodes = nodes;
        }
    }

    public static class AreaNode {

        private  Long id;

        private  List<AreaNode> shortestPath = new LinkedList<>();

        private  Integer distance = Integer.MAX_VALUE;

        Map<AreaNode, Integer> adjacentNodes = new HashMap<>();

        public void addDestination(AreaNode destination, int distance) {
            adjacentNodes.put(destination, distance);
        }

        public AreaNode(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public List<AreaNode> getShortestPath() {
            return shortestPath;
        }

        public void setShortestPath(List<AreaNode> shortestPath) {
            this.shortestPath = shortestPath;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public Map<AreaNode, Integer> getAdjacentNodes() {
            return adjacentNodes;
        }

        public void setAdjacentNodes(Map<AreaNode, Integer> adjacentNodes) {
            this.adjacentNodes = adjacentNodes;
        }
    }

    private static AreaNode getLowestDistanceNode(Set < AreaNode > unsettledNodes) {
        AreaNode lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (AreaNode node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(AreaNode evaluationNode,
                                                 Integer edgeWeigh, AreaNode sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<AreaNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    public static MapGraph calculateShortestPathFromSource(MapGraph graph, AreaNode source) {
        source.setDistance(0);

        Set<AreaNode> settledNodes = new HashSet<>();
        Set<AreaNode> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            AreaNode currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry< AreaNode, Integer> adjacencyPair:
                currentNode.getAdjacentNodes().entrySet()) {
                AreaNode adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    public List<Integer> listDifferences(Integer totalDamageMyTeam,List<Integer> listTotalDamage){
        List<Integer> diffs = new ArrayList<Integer>();
        Integer difference = 0;
        for (Integer totaldamageEnemy : listTotalDamage) {
            if (totalDamageMyTeam > totaldamageEnemy) {
                difference = totalDamageMyTeam - totaldamageEnemy;
                diffs.add(500 - difference);
            } else {
                difference = totaldamageEnemy - totalDamageMyTeam;
                diffs.add(500 + difference);
            }
        }
        return diffs;
    }

}
