package com.noname.carbonadventure.models;

public class LeaderboardEntry implements Comparable<LeaderboardEntry> {
    private String playerName;
    private int score;

    private float timeTaken;

    public LeaderboardEntry() {
    }

    public LeaderboardEntry(String playerName, int score,float timeTaken) {
        this.playerName = playerName;
        this.score = score;
        this.timeTaken = timeTaken;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public float getTimeTaken() {
        return timeTaken;
    }

    @Override
    public int compareTo(LeaderboardEntry other) {
        // Sorting primarily by score, then by shorter completion time for ties
        int scoreComparison = Integer.compare(other.score, this.score);
        if (scoreComparison == 0) {
            return Float.compare(this.timeTaken, other.timeTaken);
        }
        return scoreComparison;
    }
    }

