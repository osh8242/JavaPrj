import java.util.Scanner;

public class ReservationProcess {

	private CinemaSystem cinemaSystem;
	private Reservation reservation;
	private int[] selectedSeat;
  
	public ReservationProcess(CinemaSystem cinemaSystem, Reservation reservation) {
		this.cinemaSystem = cinemaSystem;
		this.reservation = reservation;
	}

	public void showReservationMenu(boolean isLoggedIn) {
		
		switch (cinemaSystem.getInputValue()) {
		case 1:
			showMovieList();
			break;
		case 2:
			createReservation();
			break;
		case 3:
			isPossibleToReservation();
			break;
		case 4:
		}
		
	}
	
	private void showMovieList() {
<<<<<<< HEAD
//		reservation.get
=======
		//reservation.get
>>>>>>> d9a1287ef92e6bfe925ecbfc0158326151f7f199
	}
	
	private void createReservation() {
		
	}
	
	private void cancelReservation() {
		
	}
	
	private void showUserPoint() {
		
	}
	
	private boolean isPossibleToReservation() {
		return true;
	}


}
