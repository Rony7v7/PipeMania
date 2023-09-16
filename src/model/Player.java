package model;

/**
 * The Player class represents a player in a game with a nickname. and a score. Allows you to perform
 * operations related to the player, how to get and set the nickname and score and add score to the 
 * player 
 */

public class Player {
    //The player is nickname
    private String nickname;
    //The player is score
    private int score;

    /**
     * Creates a new instance of the player class with the specified nickname and score
     * @param nickname The player is nickname
     * @param score The player is score
     */
    public Player(String nickname, int score) {
        this.nickname = nickname;
        this.score = score;
    }

    /**
     * Gets the player is nickname
     * @return player nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Set the player's nickname
     * @param newNickname The new player nickname
     */
    public void setNickname(String newNickname) {
        nickname = newNickname;
    }

    /**
     * Get the player is score
     * @return player score
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the new player score
     * @param newScore The new player score
     */
    public void setScore(int newScore) {
        score = newScore;
    }

    /**
     * Adds a score amount to the player
     * @param score The amount of score to add
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Returns a text string representation of the player in the format:
     * "Player[nickname], Score: [score]"
     * @return The player is text string representation
     */
    @Override
    public String toString() {
        return "Player " + nickname + "   Score:" + score;
    }

}
