
public class Theater {
	private String movie;
	private String[][] seat;
	private boolean isImax;
	private int price;
	
	
	
	public Theater(String movie, int[] seatSize, boolean isImax) { 
		// Parameter int[] seatSize는 (5,6)으로 받음 : 5행 6열 짜리 상영관을 의미.
		this.movie = movie;
		this.seat = new String[seatSize[0]][seatSize[1]];
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
	public void setSeat(String[][] seat) {
		this.seat = seat;
	}
	public boolean isImax() {
		return isImax;
	}
	public void setImax(boolean isImax) {
		this.isImax = isImax;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
