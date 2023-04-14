public class MainEntry {

	public static void main(String[] args) {

		CinemaSystem cs = new CinemaSystem();
		cs.run();
		/*
		 최초실행시 
		 1. FileIO.java에 경로 수정 & 해당 경로에 파일 비우기
		 2. CinemaSystem.java에서 line:30 //		setDatasets();// 초기데이터 셋팅 : 데이터파일 최초 생성시에만
		 	주석 해제후 시스템 가동
	 	 3. 데이터 저장(회원가입, 영화관 추가, 예약내역 추가 등) 후에는 관리자 메뉴에 가서 6. 파일저장 꼭 하기
	 	 4. 2번의 코드 다시 주석하고 저장
	 	 	*setDatasets(); 함수는 저장된 파일들(Theater.txt, Member.txt, Reservation.txt)을 리셋해버리기 때문에 꼭 주석해주세요.
	 	 5. 1~4 시행 후부터는 이 주석을 무시하시면 됩니다.
		 */
	}

}
