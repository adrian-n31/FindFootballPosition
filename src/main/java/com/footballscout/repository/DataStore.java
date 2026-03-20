package com.footballscout.repository;

import com.footballscout.config.DatabaseConfig;
import com.footballscout.model.Attribute;
import com.footballscout.model.PlayerProfile;
import com.footballscout.model.Recommendation;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore {

    public List<PlayerProfile> getAllProfilesFromDB() {
        List<PlayerProfile> profiles = new ArrayList<>();
        String query = "SELECT * FROM positions";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Map<Attribute, Integer> stats = new HashMap<>();
                stats.put(Attribute.PACE, rs.getInt("pace"));
                stats.put(Attribute.SHOOTING, rs.getInt("shooting"));
                stats.put(Attribute.PASSING, rs.getInt("passing"));
                stats.put(Attribute.DRIBBLING, rs.getInt("dribbling"));
                stats.put(Attribute.DEFENDING, rs.getInt("defending"));
                stats.put(Attribute.PHYSICAL, rs.getInt("physical"));

                PlayerProfile profile = new PlayerProfile(
                        rs.getString("name"),
                        stats,
                        rs.getString("preferred_foot"),
                        rs.getBoolean("likes_cutting_inside"),
                        rs.getBoolean("avoids_contact"),
                        rs.getBoolean("high_stamina")
                );
                profiles.add(profile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profiles;
    }

    public void saveScoutResult(String userName, List<Recommendation> recommendations) {
        String query = "INSERT INTO scout_results (user_name, top_1_position, top_2_position, top_3_position) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userName);
            pstmt.setString(2, recommendations.size() > 0 ? recommendations.get(0).getPositionName() : "N/A");
            pstmt.setString(3, recommendations.size() > 1 ? recommendations.get(1).getPositionName() : "N/A");
            pstmt.setString(4, recommendations.size() > 2 ? recommendations.get(2).getPositionName() : "N/A");

            pstmt.executeUpdate();
            System.out.println("Rezultatul a fost salvat cu succes pentru: " + userName);

        } catch (SQLException e) {
            System.err.println("Eroare la salvarea rezultatului: " + e.getMessage());
        }
    }
}