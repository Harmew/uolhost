package com.harmew.uolhost.model;

import com.harmew.uolhost.model.dtos.PlayerDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Entity(name = "player")
@Table(name = "tb_player")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    public String name;

    @NotBlank
    @Email
    public String email;

    public String phoneNumber;

    public String codename;

    public GroupType groupType;

    public Player(PlayerDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.phoneNumber = dto.phoneNumber();
        this.groupType = dto.groupType();
    }
}
