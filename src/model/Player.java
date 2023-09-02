package model;

public class Player {
    private String nickname;
    private int score;

    public Player(String nickname, int score) {
        this.nickname = nickname;
        this.score = score;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String newNickname) {
        nickname = newNickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int newScore) {
        score = newScore;
    }

    public void addScore(int score) {
        this.score += score;
    }

    @Override
    public String toString() {
        return "Player{" + "nickname=" + nickname + ", score=" + score + '}';
    }

}
