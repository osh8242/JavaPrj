package kr.or.team4;

public class Movie {
	private String moviename;
	private int price;
	
	
	public Movie(String moviename, int price) {
		super();
		this.moviename = moviename;
		this.price = price;
	}



	public String getMoviename() {
		return moviename;
	}


	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}



	@Override
	public String toString() {
		return "Movie [moviename=" + moviename + ", price=" + price + "]";
	}



	public String getTitle() {
		// TODO Auto-generated method stub
		return "Movie [moviename=" + moviename + ", price=" + price + "]";
	}
	
	
}
