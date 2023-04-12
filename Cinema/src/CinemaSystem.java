import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CinemaSystem {
	
	private boolean isLoggedIn;
	private ReservationProcess reservationProcess;
	private ArrayList<Theater> theaters;
	private ArrayList<Reservation> reservations; 
	private ArrayList<User> members;
	private FileIO fileIO;
	private BufferedReader br;
	private int inputValue;
	
	public void run() {
		
		outer: while(true) {
			firstDisplayPrint();
			getInputvalue();
			switch(inputValue) {
				case 1:{//1.회원 로그인
					//login()
				}
				case 2:{//2.비회원으로 예매
					//ReservationProcess reservationProcess = new ReservationProcess();
					//ReservationProcess.showReservationMenu;
					}
				case 3:{//3.회원가입
					//createUser();
				}
		
			
			}
		}
		
		
		
		
		
	}
	
	public void getInputvalue() {
		this.br = new BufferedReader(new InputStreamReader(System.in));
		try {
			this.inputValue = Integer.parseInt(br.readLine());
		} catch (Exception e) {
			System.out.println("숫자를 입력해주세요");
		}
		
	}
	
	public void firstDisplayPrint() {
	System.out.println("메뉴 선택 :");
	System.out.println("[1.회원 로그인]\t[2.비회원으로 예매]\t[3.회원가입]");
	System.out.println("입력> ");
	}
	
	

}
