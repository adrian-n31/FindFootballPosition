package com.footballscout.service;

import com.footballscout.model.Attribute;
import com.footballscout.model.PlayerProfile;
import com.footballscout.model.Recommendation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoutEngine {

    public List<Recommendation> getTopRecommendations(PlayerProfile user, List<PlayerProfile> allPositions) {
        List<Recommendation> results = new ArrayList<>();

        for (PlayerProfile pos : allPositions) {
            double score = calculateBaseDistance(user, pos);
            score = applyTacticalAdjustments(score, user, pos);
            results.add(new Recommendation(pos.getName(), score));
        }

        results.sort(Comparator.comparingDouble(Recommendation::getMatchScore));
        return results.subList(0, Math.min(3, results.size()));
    }

    private double calculateBaseDistance(PlayerProfile user, PlayerProfile target) {
        double sum = 0;
        for (Attribute attr : Attribute.values()) {
            sum += Math.pow(user.getStats().get(attr) - target.getStats().get(attr), 2);
        }
        return Math.sqrt(sum);
    }

    private double applyTacticalAdjustments(double score, PlayerProfile user, PlayerProfile target) {
        if (user.getPreferredFoot().equals(target.getPreferredFoot())) score -= 15;
        if (user.isLikesCuttingInside() && target.getName().contains("Inverted")) score -= 25;
        if (user.isLikesCuttingInside()) {
            if (user.getPreferredFoot().equals("Left") && target.getName().contains("Right Winger")) score -= 20;
            if (user.getPreferredFoot().equals("Right") && target.getName().contains("Left Winger")) score -= 20;
            if (user.getPreferredFoot().equals("Left") && target.getName().contains("Left Winger")) score += 25;
            if (user.getPreferredFoot().equals("Right") && target.getName().contains("Right Winger")) score += 25;
        }
        if (user.isAvoidsContact() && target.getStats().get(Attribute.PHYSICAL) > 80) score += 40;
        if (user.isHighStamina() && target.isHighStamina()) score -= 10;

        int defending = target.getStats().get(Attribute.DEFENDING);
        int passing   = target.getStats().get(Attribute.PASSING);
        int dribbling = target.getStats().get(Attribute.DRIBBLING);
        int physical  = target.getStats().get(Attribute.PHYSICAL);

        if (user.isLongPasser() && passing >= 80) score -= 15;

        if (user.isBallToFeet() && dribbling >= 80) score -= 12;

        if (user.isTackler() && defending >= 75) score -= 15;
        if (user.isTackler() && defending < 50)  score += 20;

        if (user.isPresser() && physical >= 80) score -= 12;

        if (user.isPlaymaker() && passing >= 82) score -= 18;
        if (!user.isPlaymaker() && target.getName().contains("Midfielder") && passing >= 88) score += 10;

        if (user.isRiskTaker() && dribbling >= 85) score -= 15;
        if (!user.isRiskTaker() && dribbling >= 85) score += 10;

        if (user.isDefender85() && defending >= 75) score -= 20;
        if (!user.isDefender85() && defending >= 80) score += 15;

        return score;
    }
}
