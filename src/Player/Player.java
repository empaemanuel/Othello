package Player;

public interface Player{

    void setSide(int side);

    String getName();

    void updatedBoardNotification();

    void playing(int side, String notificationName);

}