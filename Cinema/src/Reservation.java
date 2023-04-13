import java.io.Serializable;
import java.util.Arrays;

public class Reservation implements Serializable{ 

	private int reservationNo;
	private User user;
	private Theater theater;
	private int[] seat;
	
	
	
	public Reservation(int reservationNo, User user, Theater theater, int[] seat) {
		this.reservationNo = reservationNo;
		this.user = user;
		this.theater = theater;
		this.seat = seat;
	}
	public int getReservationNo() {
		return reservationNo;
	}
	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Theater getTheater() {
		return theater;
	}
	public void setTheater(Theater theater) {
		this.theater = theater;
	}
	public int[] getSeat() {
		return seat;
	}
	public void setSeat(int[] seat) {
		this.seat = seat;
	}
	@Override
	public String toString() {
		return "Reservation [reservationNo : " + reservationNo + ", user : " + user + ", theater : " + theater + ", seat : "
				+ Arrays.toString(seat) + "]";
	}
//	@Override
//	public String getClassName() {
//		return "Reservation";
//	}
	
	
}
