public interface Player{

    void setSide(int side, String notificationName);

    String getName();

    void updatedBoardNotification(String notificationName);

    void playing(int side, String notificationName);

}