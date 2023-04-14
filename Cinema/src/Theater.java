import java.io.Serializable;

public class Theater implements Serializable {
	private String movie;
	private String[][] seat;
	private boolean isImax;
	private int price;

	public Theater(String movie, int[] seatSize, boolean isImax) {
		// Parameter int[] seatSize는 (5,6)으로 받음 : 5행 6열 짜리 상영관을 의미.
		this.movie = movie;
		this.seat = new String[seatSize[0]][seatSize[1]];
		// 좌석 배열 초기화
		for (int i = 0; i < seatSize[0]; i++) {
			for (int j = 0; j < seatSize[1]; j++) {
				seat[i][j] = "[" + (i + 1) + "-" + (j + 1) + "]";
				// System.out.printf(seat[i][j]);
			}
			// System.out.println();
		}
		this.isImax = isImax;
		this.price = (isImax) ? 25000 : 15000;
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

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {

		return "Theater [movie: " + movie + ", seat: {" + seat.length + ", " + seat[0].length + "}, isImax: " + isImax
				+ ", price: " + price + "]";
	}
}
