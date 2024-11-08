package com.harmew.uolhost.service;

import com.harmew.uolhost.infra.CodenameHandler;
import com.harmew.uolhost.model.GroupType;
import com.harmew.uolhost.model.Player;
import com.harmew.uolhost.model.dtos.PlayerDTO;
import com.harmew.uolhost.repositories.PlayerRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final CodenameHandler codenameHandler;

    public PlayerService(PlayerRepository playerRepository, CodenameHandler codenameHandler) {
        this.playerRepository = playerRepository;
        this.codenameHandler = codenameHandler;
    }

    public Player createPlayer(@Valid PlayerDTO dto) {
        Player player = new Player(dto);
        String codename = getCodename(dto.groupType());
        player.setCodename(codename);
        return playerRepository.save(player);
    }


    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.orElseThrow(() -> new RuntimeException("Player not found"));
    }

    private String getCodename(GroupType groupType) {
        return codenameHandler.findCodename(groupType);
    }

}
