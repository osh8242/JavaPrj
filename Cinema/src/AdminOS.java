import java.util.ArrayList;

public class AdminOS {
	
	public CinemaSystem cs;
	public ArrayList<Theater> theaters;
	public ArrayList<Reservation> reservations; 
	public ArrayList<Member> members;
	
	public AdminOS(CinemaSystem cinemaSystem) {
		this.cs = cinemaSystem;
		this.theaters = cinemaSystem.theaters;
		this.reservations = cinemaSystem.reservations;
		this.members = cinemaSystem.members;
	}
	
	public void run() {
		outerAdmin:while(true) {
			showAdminMenu();
			switch(cs.getInputValue()) {
				case 1:{ //상영관 관리
					outer1:while(true) {
						showTheaterMenu();
						switch(cs.getInputValue()) {
							case 1:{ //상영관 정보 조회
								showTheaters();
								break;
							}
							case 2:{ //상영관 정보 수정
								editTheaters();
								break;
							}
							case 3:{ //돌아가기
								break outer1;
							}
						}
					}
					break;
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
				case 0:{ //관리자모드 끝내기
					break outerAdmin;
				}
		
			} // showAdminMenu switch
		} // showAdminMenu while
		
	} //run()

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
			System.out.println(cs.theaters.get(i).toString()+"\n");
		}
	}
	
	public void editTheaters() {
		boolean runState = true;
		System.out.println("수정할 상영관 번호를 입력하세요");
		Theater t = theaters.get(cs.getInputValue()-1);
		System.out.println("선택된 상영관의 정보");
		System.out.println(t.toString());
		System.out.println("수정할 내용을 선택하세요.");
		System.out.println("[1.영화] [2.티켓가격] [3.수정종료]");
		switch(cs.getInputValue()) {
			case 1:{
				System.out.println("현재 영화제목 : "+t.getMovie());
				System.out.print("수정할 영화제목을 입력하세요 : ");
				t.setMovie(cs.getStringValue());
				System.out.println("\n정보가 수정되었습니다.");
				System.out.println("수정된 영화제목 : "+t.getMovie());
				break;
			}
			case 2:{
				System.out.println("현재 티켓가격 : "+t.getPrice());
				System.out.print("수정할 티켓가격을 입력하세요 : ");
				t.setMovie(cs.getStringValue());
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
	
	public void showMembers() {
		for (Member m : members) {
			System.out.println(m.toString());
		}
		System.out.println("총 회원수 : " + members.size());
	}
	
	public void editMember() {
		Member member = null;	
		System.out.println("수정하고자 하는 회원의 아이디를 입력하세요");
		System.out.print("입력>>");
		String name = cs.getStringValue();			
		for(Member m : members) {
			if (m.getUserId().equals(name)) {
				member = m;
				break;
			}
		}
		if(member == null) {
			System.out.println("해당 아이디를 가진 회원이 없습니다.");
			return;
		}
		outer:while(true) {
			System.out.println("선택된 회원 정보 : ");
			System.out.println(member.toString());
			System.out.println("수정하고자 하는 내용을 선택하세요");
			System.out.println("[1.유저아이디] [2.비밀번호] [3.핸드폰번호] [4.회원이름] [5.적립포인트] [6.관리자권한]");
			System.out.println("[0. 종료]");
			switch(cs.getInputValue()) {
				case 1:{ //유저아이디
					System.out.println("현재 유저의 아이디는 : "+member.getUserId());
					System.out.println("수정할 값을 입력하세요");
					member.setUserId(cs.getStringValue());
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
					member.setUserPhoneNumber(cs.getStringValue());
					System.out.println("수정된 유저의 핸드폰번호는 : "+member.getUserPhoneNumber());
					continue;
				}
				case 4:{ //회원이름
					System.out.println("현재 유저의 회원이름은 : "+member.getUserName());
					System.out.println("수정할 값을 입력하세요");
					member.setUserName(cs.getStringValue());
					System.out.println("수정된 유저의 회원이름은 : "+member.getUserName());
					continue;
				}
				case 5:{ //적립포인트
					System.out.println("현재 유저의 적립포인트는 : "+member.getUserPoint());
					System.out.println("수정할 값을 입력하세요");
					member.setUserPoint(cs.getInputValue());
					System.out.println("수정된 유저의 적립포인트는 : "+member.getUserPoint());
					continue;
				}
				case 6:{ //관리자 권한
					System.out.println("현재 관리자 권한 보유 여부: "+member.isAdmin());
					System.out.println("현재 권한을 변경하려면 y를 입력하세요");
					if(cs.getStringValue().toLowerCase().equals("y")) {
						member.setAdmin(!member.isAdmin());
						System.out.println("수정된 관리자 권한 보유 여부 : "+member.getUserPassword());
					} else {
						System.out.println("권한 변경이 취소되었습니다.");
					}
					continue;
					
				}	
				case 0:{ //종료
					break outer;
				}
				default:{
					System.out.println("잘못된 값을 입력하셨습니다.");
				}
			}			
		}
	} // editmember()

} //class
