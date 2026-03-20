package com.footballscout.controller;

import com.footballscout.model.Attribute;
import com.footballscout.model.PlayerProfile;
import com.footballscout.model.Recommendation;
import com.footballscout.repository.DataStore;
import com.footballscout.service.ScoutEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ScoutController {

    private final DataStore dataStore = new DataStore();
    private final ScoutEngine engine = new ScoutEngine();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam Map<String, String> allParams, Model model) {
        Map<Attribute, Integer> stats = new HashMap<>();
        stats.put(Attribute.PACE,      Integer.parseInt(allParams.get("pace")));
        stats.put(Attribute.SHOOTING,  Integer.parseInt(allParams.get("shooting")));
        stats.put(Attribute.PASSING,   Integer.parseInt(allParams.get("passing")));
        stats.put(Attribute.DRIBBLING, Integer.parseInt(allParams.get("dribbling")));
        stats.put(Attribute.DEFENDING, Integer.parseInt(allParams.get("defending")));
        stats.put(Attribute.PHYSICAL,  Integer.parseInt(allParams.get("physical")));

        PlayerProfile user = new PlayerProfile(
                allParams.get("name"),
                stats,
                allParams.get("foot"),
                "true".equals(allParams.get("cutInside")),
                "true".equals(allParams.get("avoidContact")),
                "true".equals(allParams.get("highStamina")),
                "true".equals(allParams.get("longPasser")),
                "true".equals(allParams.get("ballToFeet")),
                "true".equals(allParams.get("tackler")),
                "true".equals(allParams.get("presser")),
                "true".equals(allParams.get("playmaker")),
                "true".equals(allParams.get("riskTaker")),
                "true".equals(allParams.get("defender85"))
        );

        List<Recommendation> recommendations = engine.getTopRecommendations(user, dataStore.getAllProfilesFromDB());
        dataStore.saveScoutResult(user.getName(), recommendations);

        model.addAttribute("results", recommendations);
        model.addAttribute("userName", user.getName());
        model.addAttribute("userStats", stats);

        return "results";
    }
}