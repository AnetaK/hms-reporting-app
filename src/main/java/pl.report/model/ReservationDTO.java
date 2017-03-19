package pl.report.model;

public class ReservationDTO {

    private long Id;
    private String guestName;
    private String guestSurname;
    private long roomId;

    public long getId() {
        return Id;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getGuestSurname() {
        return guestSurname;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setGuestSurname(String guestSurname) {
        this.guestSurname = guestSurname;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "Id=" + Id +
                ", guestName='" + guestName + '\'' +
                ", guestSurname='" + guestSurname + '\'' +
                ", roomId=" + roomId +
                '}';
    }
}
