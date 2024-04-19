package com.noname.carbonadventure.models;

public class LeaderboardEntry implements Comparable<LeaderboardEntry> {
    private String playerName;
    private int score;

    public LeaderboardEntry() {
    }

    public LeaderboardEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(LeaderboardEntry other) {
        // Sort in descending order
        return Integer.compare(other.score, this.score);
    }
}
