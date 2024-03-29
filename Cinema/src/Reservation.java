import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;

public class Reservation implements Serializable {
	private static int count;
	private String reservationNo;
	private User user;
	private Theater theater;
	private int[] seat;

	public String createReservationNo() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR) + "" + (now.get(Calendar.MONTH) + 1) + "" + now.get(Calendar.DAY_OF_MONTH) + "-"
				+ count;
	}

	public Reservation(User user, Theater theater, int[] seat) {
		Reservation.count++;
		this.reservationNo = createReservationNo();
		this.user = user;
		this.theater = theater;
		this.seat = seat;
	}

	public static void setCount(int count) {
		Reservation.count = count;
	}

	public String getReservationNo() {
		return reservationNo;
	}

	public User getUser() {
		return user;
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

	@Override
	public String toString() {
		return "Reservation [reservationNo : " + reservationNo + ", user : " + user + ", theater : " + theater
				+ ", seat : " + Arrays.toString(seat) + "]";
	}
}
