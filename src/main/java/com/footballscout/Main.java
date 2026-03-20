package com.footballscout;

import com.footballscout.model.Attribute;
import com.footballscout.model.PlayerProfile;
import com.footballscout.model.Recommendation;
import com.footballscout.repository.DataStore;
import com.footballscout.service.ScoutEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        DataStore ds = new DataStore();
        List<PlayerProfile> allPositions = ds.getAllProfilesFromDB();
        ScoutEngine engine = new ScoutEngine();

        // SIMULARE USER: Rapid, stângaci, îi place să intre în centru (Style: Salah/Saka)
        Map<Attribute, Integer> userStats = new HashMap<>();
        userStats.put(Attribute.PACE, 92);
        userStats.put(Attribute.SHOOTING, 85);
        userStats.put(Attribute.PASSING, 78);
        userStats.put(Attribute.DRIBBLING, 90);
        userStats.put(Attribute.DEFENDING, 35);
        userStats.put(Attribute.PHYSICAL, 65);

        PlayerProfile user = new PlayerProfile("Adrian", userStats, "Left", true, false, false);

        List<Recommendation> topPos = engine.getTopRecommendations(user, allPositions);

        System.out.println("--- REZULTATE SCOUTING PENTRU: " + user.getName() + " ---");
        for (int i = 0; i < topPos.size(); i++) {
            System.out.println((i + 1) + ". " + topPos.get(i));
        }
    }
}