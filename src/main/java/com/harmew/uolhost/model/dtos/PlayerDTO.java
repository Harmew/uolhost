package com.harmew.uolhost.model.dtos;

import com.harmew.uolhost.model.GroupType;

public record PlayerDTO(
        String name,
        String email,
        String phoneNumber,
        GroupType groupType
) {

}
