import java.util.ArrayList;

public class MainEntry {

	public static void main(String[] args) {

		CinemaSystem cs = new CinemaSystem();		
//		cs.run();
		
		// nana test code //////////////////////////////
		FileIO fio = new FileIO();
		fio.Output();
		ArrayList<Theater> theaterList = fio.Input();
		System.out.println(theaterList.get(1).toString());
		
		///////////////////////////////////////////////
		
		

	}

}
