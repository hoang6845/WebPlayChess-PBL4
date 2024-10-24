package com.pbl4.model.bean;

public class RankModel extends AbstractModel<RankModel> {
    private long elo;
    private long win;
    private long lose;
    private long matches;

    public long getMatches() {
        return matches;
    }
    public void setMatches(long matches) {
        this.matches = matches;
    }
    public long getElo() {
        return elo;
    }
    public void setElo(long elo) {
        this.elo = elo;
    }
    public long getWin() {
        return win;
    }
    public void setWin(long win) {
        this.win = win;
    }
    public long getLose() {
        return lose;
    }
    public void setLose(long lose) {
        this.lose = lose;
    }
    public double getWinRate() {

        return (double) (win) / (matches) * 100;
    }
}
