package game.model;

public class Score {
    private int floor = 1;
    private int room = 0;
    private long startTime = 0;
    public int getFloor() {
        return floor;
    }
    public int getRoom() {
        return room;
    }
    public long getStartTime() {
        return startTime;
    }
    public void setFloor(int floor) {
        this.floor = floor;
    }
    public void setRoom(int room) {
        this.room = room;
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
