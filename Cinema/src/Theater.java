import java.util.Arrays;

public class Theater {
	private String movie;
	private String[][] seat;
	private boolean isImax;
	private int price;
	
	public Theater(String movie, int[] seatSize, boolean isImax) { 
		// Parameter int[] seatSize는 (5,6)으로 받음 : 5행 6열 짜리 상영관을 의미.
		this.movie = movie;
		this.seat = new String[seatSize[0]][seatSize[1]];
		// 좌석 배열 초기화
		for(int i=0; i < seatSize[0]; i++) {
			for(int j = 0; j<seatSize[1]; j++) {
				System.out.printf("[%d-%d]",i+1,j+1);
			}
			System.out.println();
		}
		this.isImax = isImax;
		this.price = (isImax) ? 25000:15000;
	}
	
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String[][] getSeat() {
		return seat;
	}
	// 상영관 좌석을 변경하려면 상영관 새로 생성
//	public void setSeat(String[][] seat) {
//		this.seat = seat;
//	}
	public boolean getIsImax() {
		return isImax;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Theater [movie: " + movie + ", seat: " + Arrays.toString(seat) + ", isImax: " + isImax + ", price: " + price
				+ "]";
	}
	
	public void showSeat() {
		for(int i=0; i < seat.length; i++) {
			for(String seatState:seat[i]) {
				System.out.printf("[%s]",seatState);
			}
			System.out.println();
		}
	}
	
	
	
}
