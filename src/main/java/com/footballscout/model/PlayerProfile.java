package com.footballscout.model;

import java.util.Map;

public class PlayerProfile {
    private String name;
    private Map<Attribute, Integer> stats;
    private String preferredFoot;
    private boolean likesCuttingInside;
    private boolean avoidsContact;
    private boolean highStamina;
    private boolean longPasser;
    private boolean ballToFeet;
    private boolean tackler;
    private boolean presser;
    private boolean playmaker;
    private boolean riskTaker;
    private boolean defender85;

    public PlayerProfile(String name, Map<Attribute, Integer> stats, String preferredFoot,
                         boolean likesCuttingInside, boolean avoidsContact, boolean highStamina,
                         boolean longPasser, boolean ballToFeet, boolean tackler,
                         boolean presser, boolean playmaker, boolean riskTaker, boolean defender85) {
        this.name = name;
        this.stats = stats;
        this.preferredFoot = preferredFoot;
        this.likesCuttingInside = likesCuttingInside;
        this.avoidsContact = avoidsContact;
        this.highStamina = highStamina;
        this.longPasser = longPasser;
        this.ballToFeet = ballToFeet;
        this.tackler = tackler;
        this.presser = presser;
        this.playmaker = playmaker;
        this.riskTaker = riskTaker;
        this.defender85 = defender85;
    }


    public PlayerProfile(String name, Map<Attribute, Integer> stats, String preferredFoot,
                         boolean likesCuttingInside, boolean avoidsContact, boolean highStamina) {
        this(name, stats, preferredFoot, likesCuttingInside, avoidsContact, highStamina,
                false, false, false, false, false, false, false);
    }

    public String getName() { return name; }
    public Map<Attribute, Integer> getStats() { return stats; }
    public String getPreferredFoot() { return preferredFoot; }
    public boolean isLikesCuttingInside() { return likesCuttingInside; }
    public boolean isAvoidsContact() { return avoidsContact; }
    public boolean isHighStamina() { return highStamina; }
    public boolean isLongPasser() { return longPasser; }
    public boolean isBallToFeet() { return ballToFeet; }
    public boolean isTackler() { return tackler; }
    public boolean isPresser() { return presser; }
    public boolean isPlaymaker() { return playmaker; }
    public boolean isRiskTaker() { return riskTaker; }
    public boolean isDefender85() { return defender85; }
}