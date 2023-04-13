import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CinemaSystem {
	
	public boolean isLoggedIn;
	private ReservationProcess reservationProcess;
	private ArrayList<Theater> theaters;
	private ArrayList<Reservation> reservations; 
	private ArrayList<Member> members;
	private FileIO fileIO;
	private BufferedReader br;	
	private Member userLoggedIn;
	
	
	// 임시 데이터 셋팅 함수
	private void setDatasets() {
		System.out.println("초기 데이터를 셋팅중...");
		// default 데이터
		this.theaters.add(new Theater("스즈메", new int[] {3,4}, true));
		this.theaters.add(new Theater("존윅", new int[] {10,10}, false));
		this.members.add(new Member(0101234123, "1234", "oshh","오승환"));
		this.members.add(new Member(0101234567, "5678", "sjsj","장석진"));
		Theater theater1 = (Theater) this.theaters.get(0);
		Theater theater2 = (Theater) this.theaters.get(1);
		User user1 = (User) this.members.get(0);
		User user2 = (User) this.members.get(1);
		this.reservations.add(new Reservation( user1, theater1,new int[]{3,4}));
		this.reservations.add(new Reservation( user2, theater2,new int[]{1,1}));
        // 임시 데이터 파일로 저장(출력)
		fileIO.Output(this.theaters, "Theater");
		fileIO.Output(this.members, "Member");
		fileIO.Output(this.reservations, "Reservation");		
	}
	private void getDatasets() {
		System.out.println("데이터를 불러옵니다.");
		// 입력(데이터 불러오기) 
		this.theaters = fileIO.Input( "Theater");
		this.members = fileIO.Input( "Member");
		this.reservations = fileIO.Input( "Reservation");
		//System.out.println(this.theaters.get(0));
	}
	
	public CinemaSystem() {
		super();

		this.isLoggedIn = false;
		this.reservationProcess = new ReservationProcess();
		
		this.theaters = new ArrayList<Theater>();
		this.members = new ArrayList<Member>();
		this.reservations = new ArrayList<Reservation>();
		
		this.fileIO = new FileIO();
		setDatasets();// 초기데이터 셋팅 : 기능 구현 후 삭제
		getDatasets();// 입력(데이터 불러오기)
//		System.out.println(this.theaters.get(0));
		
		this.br = br;
		this.userLoggedIn = userLoggedIn;
	}

	public void run() {		
		
outerMain: while(true) {
			firstDisplayPrint();			
			switch(getInputValue()) {
				case 1:{//1.회원 로그인
					if( (userLoggedIn = login()) != null);{ //일반 회원이라면
						if(!userLoggedIn.isAdmin()) {
							//ReservationProcess reservationProcess = new ReservationProcess();
							//ReservationProcess.showReservationMenu(isLoggedIn, userLoggedIn);	
						} else { //관리자라면				 			
							 while(true) {
									showAdminMenu();
									switch(getInputValue()) {
										case 1:{ //상영관 관리
											while(true) {
												showAdminMenu();
												switch(getInputValue()) {
													case 1:{ //상영관 정보 조회
														showTheaters();
														break;
													}
													case 2:{ //상영관 정보 수정
														editTheaters();
														break;
													}
													case 0:{
														
													}
												}
											}
											
										}
										case 2:{ //회원목록 조회
											showMembers();
											break;
										}
										case 3:{ //회원정보 수정
											editMember();
											break;
										}
										case 4:{ //매출액 조회
											showRevenue();
											break;
										}
										case 5:{ //예매목록 조회
											showReservations();
											break;
										}
										case 6:{ //파일저장
											
										}
										case 0:{
											break outerMain;
										}
								
									} // showAdminMenu switch
								} // showAdminMenu while
								
						} //관리자라면
						
					} //일반회원이라면 
					break;
				} //로그인
				case 2:{//2.비회원으로 예매
					//ReservationProcess reservationProcess = new ReservationProcess();
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
	
	public void showRevenue() {
		int revenueSum = 0;
		for(Reservation r : reservations) {
			revenueSum += r.getTheater().getPrice();
		}
		System.out.println("총 판매액 : " + revenueSum);
	}
	
	public void showReservations() {
		for(Reservation r : reservations) {
			System.out.println(r.toString());
		}
	}	
	
	public void showAdminMenu() {
		System.out.println("관리자 메뉴 선택 :");
		System.out.println("[1.상영관 관리]\t[2.회원목록 조회]\t[3.회원목록 수정]\t[4.매출액 조회]");
		System.out.println("[5.예매목록 조회]\t[6.파일저장]\t[0.관리자 메뉴 종료]");
		System.out.print("입력> ");		
	}
	
	public void showTheaterMenu() {
		System.out.println("상영관 관리 메뉴 선택 :");
		System.out.println("[1.상영관 목록 조회]\t[2.상영관 수정] [3.뒤로가기]");
		System.out.print("입력> ");
	}
	
	public void showTheaters() {
		for(int i = 0 ; i < theaters.size() ; i++) {
			System.out.println(i+1+"번 상영관");
			System.out.println(theaters.get(i).toString()+"\n");
		}
	}
	
	public void editTheaters() {
		boolean runState = true;
		while(runState) {
			System.out.println("수정할 상영관 번호를 입력하세요");
			Theater t = theaters.get(getInputValue()+1);
			System.out.println("선택된 상영관의 정보");
			System.out.println(t.toString());
			System.out.println("수정할 내용을 선택하세요.");
			System.out.println("[1.영화] [2.티켓가격] [3.수정종료]");
			switch(getInputValue()) {
				case 1:{
					System.out.println("현재 영화제목 : "+t.getMovie());
					System.out.print("수정할 영화제목을 입력하세요 : ");
					t.setMovie(getStringValue());
					System.out.println("\n정보가 수정되었습니다.");
					System.out.println("수정된 영화제목 : "+t.getMovie());
					break;
				}
				case 2:{
					System.out.println("현재 티켓가격 : "+t.getPrice());
					System.out.print("수정할 티켓가격을 입력하세요 : ");
					t.setMovie(getStringValue());
					System.out.println("\n정보가 수정되었습니다.");
					System.out.println("수정된 티켓가격 : "+t.getPrice());
					break;
				}
				case 3:{
					runState = false;
					break;
				}
				default:{
					System.out.println("잘못된 값을 입력하셨습니다.");
				}
			
			}
		}
		
	}
	
	public void showMembers() {
		for (Member m : members) {
			System.out.println(m.toString());
		}
	}
	
	public void editMember() {
		Member member = null;	
		System.out.println("수정하고자 하는 회원의 아이디를 입력하세요");
		System.out.print("입력>>");
		String name = getStringValue();			
		for(Member m : members) {
			if (m.getUserName().equals(name)) {
				member = m;
				break;
			}
		}
		if(member == null) {
			System.out.println("해당 아이디를 가진 회원이 없습니다.");
			return;
		}
		while(true) {
			System.out.println("선택된 회원 정보 : ");
			System.out.println(member.toString());
			System.out.println("수정하고자 하는 내용을 선택하세요");
			System.out.println("[1.유저아이디] [2.비밀번호] [3.핸드폰번호] [4.회원이름] [5.적립포인트] [6.관리자권한]");
			System.out.println("[0. 종료]");
			switch(getInputValue()) {
				case 1:{ //유저아이디
					System.out.println("현재 유저의 아이디는 : "+member.getUserId());
					System.out.println("수정할 값을 입력하세요");
					member.setUserId(getStringValue());
					System.out.println("수정된 유저의 아이디는 : "+member.getUserId());
					continue;
				}
				case 2:{ //비밀번호
					System.out.println("현재 유저의 비밀번호는 : "+member.getUserPassword());
					System.out.println("수정할 값을 입력하세요");
					member.setUserPassword(name);
					System.out.println("수정된 유저의 비밀번호는 : "+member.getUserPassword());
					continue;
				}
				case 3:{ //핸드폰번호
					System.out.println("현재 유저의 핸드폰번호는 : "+member.getUserPhoneNumber());
					System.out.println("수정할 값을 입력하세요");
					member.setUserPhoneNumber(getStringValue());
					System.out.println("수정된 유저의 비밀번호는 : "+member.getUserPhoneNumber());
					continue;
				}
				case 4:{ //회원이름
					System.out.println("현재 유저의 비밀번호는 : "+member.getUserName());
					System.out.println("수정할 값을 입력하세요");
					member.setUserName(getStringValue());
					System.out.println("수정된 유저의 비밀번호는 : "+member.getUserName());
					continue;
				}
				case 5:{ //적립포인트
					System.out.println("현재 유저의 비밀번호는 : "+member.getUserPoint());
					System.out.println("수정할 값을 입력하세요");
					member.setUserPoint(getInputValue());
					System.out.println("수정된 유저의 비밀번호는 : "+member.getUserPoint());
					continue;
				}
				case 6:{ //관리자 권한
					System.out.println("현재 관리자 권한 보유 여부: "+member.isAdmin());
					System.out.println("현재 권한을 변경하려면 y를 입력하세요");
					if(getStringValue().toLowerCase().equals("y")) {
						member.setAdmin(!member.isAdmin());
						System.out.println("수정된 관리자 권한 보유 여부 : "+member.getUserPassword());
					} else {
						System.out.println("권한 변경이 취소되었습니다.");
					}
					continue;
					
				}	
				case 0:{ //종료
					break;
				}
				default:{
					System.out.println("잘못된 값을 입력하셨습니다.");
				}
			}			
		}
	} // editmember()
	
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

	public ArrayList<Theater> getTheaters() {
		return theaters;
	}

	public void setTheaters(ArrayList<Theater> theaters) {
		this.theaters = theaters;
	}

	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}

	public ArrayList<Member> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}
	

}
