package com.footballscout.model;

public class Recommendation {
    private String positionName;
    private double matchScore;

    public Recommendation(String positionName, double matchScore) {
        this.positionName = positionName;
        this.matchScore = matchScore;
    }

    public String getPositionName() { return positionName; }

    public double getMatchScore() { return matchScore; }

    public int getMatchPercentage() {
        double maxDistance = 150.0;
        double percentage = 100 - (matchScore / maxDistance * 100);

        if (percentage < 0) percentage = 0;
        if (percentage > 100) percentage = 100;

        return (int) percentage;
    }

    @Override
    public String toString() {
        // scor mic = potrivire buna
        return positionName + " (Inaccuracy Score: " + String.format("%.2f", matchScore) + ")";
    }


}