import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CinemaSystem {
	
	public boolean isLoggedIn;
	private ReservationProcess reservationProcess;
	public ArrayList<Theater> theaters;
	public ArrayList<Reservation> reservations; 
	public ArrayList<Member> members;
	private FileIO fileIO;
	private BufferedReader br;	
	private Member userLoggedIn;
	private AdminOS AdminOs = new AdminOS(this);
	
	public void run() {		
		
		while(true) {
			firstDisplayPrint();			
			switch(getInputValue()) {
				case 1:{//1.회원 로그인
					if( (userLoggedIn = login()) != null);{ //일반 회원이라면
						if(!userLoggedIn.isAdmin()) {
							//ReservationProcess reservationProcess = new ReservationProcess(this);
							//ReservationProcess.showReservationMenu(isLoggedIn, userLoggedIn);	
						} else { //관리자라면				 			
							 AdminOs.run();
						}
					} //일반회원이라면 
					break;
					} //로그인
				case 2:{//2.비회원으로 예매
					//ReservationProcess reservationProcess = new ReservationProcess(this);
					//ReservationProcess.showReservationMenu(isLoggedIn, null);
					break;
					}
				case 3:{//3.회원가입
					createUser();
					break;
					
				}
		
			
			}
		}
		
	}
	
	public int getInputValue() {
		this.br = new BufferedReader(new InputStreamReader(System.in));
		try {
			return Integer.parseInt(br.readLine());
		} catch (Exception e) {
			System.out.println("숫자를 입력해주세요");
			return getInputValue();
		} 	
	}
	
	public String getStringValue() {
		this.br = new BufferedReader(new InputStreamReader(System.in));
		try {
			return br.readLine();
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다.");
			return getStringValue();
		} 	
	}
	
	public void firstDisplayPrint() {
	System.out.println("메뉴 선택 :");
	System.out.println("[1.회원 로그인]\t[2.비회원으로 예매]\t[3.회원가입]");
	System.out.print("입력> ");
	}
	
	public Member login() {
		System.out.print("아이디를 입력해주세요 : ");
		String id = getStringValue();
		for(Member m : members) {
			if(m.getUserId().equals(id)) {
				System.out.print("비밀번호를 입력해주세요 : ");
				if(m.getUserPassword().equals(getStringValue())) {
					System.out.println("로그인 성공!");
					System.out.println(m.getUserName()+" 님, 환영합니다^^*");
					return m;
				} else {
					System.out.println("비밀번호가 올바르지 않습니다.");
					System.out.println("초기화면으로 돌아가려면 exit을 입력하고,");
					System.out.println("다시 로그인 시도하려면 아무값이나 입력하세요");
					if(getStringValue().equals("exit")) return null;
					else return login();
				}
			}
		}
		System.out.println("해당 아이디가 존재하지 않습니다.");
		return null;
	}	
	
	public void createUser() {
		System.out.println("희망하는 회원ID를 입력하세요");
		String userId = getStringValue();
		System.out.println("비밀번호를 입력하세요");
		String userPassword = getStringValue();
		System.out.println("회원이름을 입력하세요");
		String userName = getStringValue();
		System.out.println("핸드폰번호를 입력하세요");
		int userPhoneNumber = getInputValue();
		members.add(new Member(userPhoneNumber, userPassword, userId, userName));
		System.out.println("회원가입이 완료되었습니다.");
		System.out.println("가입정보 : ");
		System.out.println("ID : "+userId + " / " + "회원이름 : "+userName + " / " + "핸드폰 번호 : " + userPhoneNumber);
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}	

}
