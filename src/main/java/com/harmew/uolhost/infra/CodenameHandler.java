package com.harmew.uolhost.infra;

import com.harmew.uolhost.model.GroupType;
import com.harmew.uolhost.service.CodenameService;
import org.springframework.stereotype.Component;

@Component
public class CodenameHandler {

    private final CodenameService codenameService;

    public CodenameHandler(CodenameService codenameService) {
        this.codenameService = codenameService;
    }

    public String findCodename(GroupType groupType) {
        if (groupType == GroupType.AVENGERS) {
            String firstMatch = codenameService.getAvengersCodenameList().stream().findFirst().orElseThrow();
            codenameService.getAvengersCodenameList().remove(firstMatch);
            return firstMatch;
        }

        if (groupType == GroupType.JUSTICE_LEAGUE) {
            String firstMatch = codenameService.getJusticeLeagueCodenameList().stream().findFirst().orElseThrow();
            codenameService.getJusticeLeagueCodenameList().remove(firstMatch);
            return firstMatch;
        }

        throw new RuntimeException("Error invalid groupType");
    }
}
