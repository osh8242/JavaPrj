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
//		reservation.get
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
