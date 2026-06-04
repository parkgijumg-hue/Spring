package org.edu.member.service;

import org.edu.member.dao.MemberDao;
//import org.edu.member.dao.MemberDaoImpl;
import org.edu.member.dao.MemberDaoImpl_pgj;
import org.edu.member.vo.Member;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MemberService {
    private Scanner sc = new Scanner(System.in);

    // 수업
    //private MemberDao dao = new MemberDaoImpl();
    // 숙제
    private MemberDao dao = new MemberDaoImpl_pgj();

    public void displayMenu() {

        int menu = 0; // 메뉴 선택용 변수

        do {
            try {
                System.out.println("[메인 메뉴]");
                System.out.println("1. 회원 등록");
                System.out.println("2. 회원 목록 조회");
                System.out.println("3. 회원 정보 조회");
                System.out.println("4. 회원 수정");
                System.out.println("5. 회원 삭제");
                System.out.println("6. 회원 부서명 조회");
                System.out.println("0. 종료");
                System.out.print("메뉴 선택 >> ");

                menu = sc.nextInt();
                sc.nextLine(); // 입력 버퍼 개행문자 제거
                System.out.println(); // 줄바꿈

                switch (menu) {
                    case 1:
                        create();
                        break;
                    case 2:
                        getList();
                        break;
                    case 3:
                        get();
                        break;
                    case 4:
                        update();
                        break;
                    case 5:
                        delete();
                        break;
                    case 6:
                        getDeptName();
                        break;
                    case 0:
                        System.out.println("[프로그램 종료]");
                        break;
                    default:
                        System.out.println("잘못 입력하셨습니다. 메뉴를 다시 선택해주세요.");
                }

            }/*catch (SQLException e){
                System.out.println("DB 작업중 에러 발생");
                e.printStackTrace();

            }*/ catch (Exception e) {
                sc.nextLine(); // 잘못된 입력 제거
                e.printStackTrace();
            }
        } while (menu != 0);
    }

    // 회원 등록
    private void create() throws SQLException {
        System.out.println("=== 회원 등록 ===");

        // 아이디, 비밀번호, 이름, 권한 입력받아 변수에 저장
        System.out.print("아이디 : ");
        String id = sc.nextLine();

        System.out.print("비밀번호 : ");
        String pw = sc.nextLine();

        System.out.print("이름 : ");
        String name = sc.nextLine();

        System.out.print("역할 : ");
        String role = sc.nextLine();


        // Member 객체 생성 후 전달
        //Member member = new Member(id, pw, name, role);
        Member member = new Member();
        member.setId(id);
        member.setPw(pw);
        member.setName(name);
        member.setRole(role);

        int result = dao.create(member);

        // 회원 등록 성공 시 : "000님의 가입을 환영합니다."
        // 회원 등록 실패 시 : "회원 등록 실패."
        if (result > 0) {
            System.out.println(name + "님의 가입을 환영합니다.");
        } else {
            System.out.println("회원 등록 실패.");
        }
    }

    // 회원 정보 수정
    private void update() throws SQLException {
        System.out.println("=== 회원 정보 수정===");

        // 회원 번호를 입력 받아 일치하는 회원의 이름,권한 수정
        System.out.print("회원 번호 : ");
        int no = sc.nextInt();
        sc.nextLine();
//        if (no.equals("")) {
//            System.out.println("회원 번호를 입력해주세요.");
//            return;
//        }
        int memberNo;
//        try {
//            memberNo = Integer.parseInt(no);
//        }catch (NumberFormatException e){
//            System.out.println("회원 번호는 숫자로 입력해주세요.");
//            return;
//        }
        System.out.print("수정할 이름: ");
        String name = sc.nextLine();

        System.out.print("수정할 권한 : ");
        String role = sc.nextLine();

        Member member = new Member();
        member.setMemberNo(no);
        member.setName(name);
        member.setRole(role);
        int result = dao.update(member);
        if (result != 0) {
            System.out.println("회원 정보가 수정되었습니다.");
        }else{
            System.out.println("일치하는 회원이 없습니다.");
        }
    }
    // getList() : 회원 목록 전체 조회
    private void getList() throws SQLException {
        System.out.println("=== 회원 목록 조회 ===");

        List<Member> memberList = dao.getList();
        if (memberList.isEmpty()) {
            System.out.println("조회된 회원이 없습니다.");
            return;
        }

        for (Member member : memberList) {
            System.out.println(member);
        }
    }

    // get() : 회원 번호가 일치하는 회원 한명 조회
    private void get() throws SQLException {
        System.out.println("=== 회원 정보 조회 ===");

        System.out.print("회원 번호 : ");
        int no = sc.nextInt();
        sc.nextLine();

        Member member = dao.get(no);
        if (member != null) {
            System.out.println(member);
        } else {
            System.out.println("일치하는 회원이 없습니다.");
        }
    }

    // delete() : 회원 번호가 일치하는 회원 삭제
    private void delete() throws SQLException {
        System.out.println("=== 회원 삭제 ===");

        System.out.print("회원 번호 : ");
        int no = sc.nextInt();
        sc.nextLine();

        int result = dao.delete(no);
        if (result != 0) {
            System.out.println("회원 정보가 삭제되었습니다.");
        } else {
            System.out.println("일치하는 회원이 없습니다.");
        }
    }
    // 회원 번호가 일치하는 회원의 번호, 이름, 부서코드, 부서명 조회
    private void getDeptName() throws SQLException {
        System.out.println("=== 회원 부서정보 조회 ===");

        System.out.print("부서 번호 : ");
        int deptno = sc.nextInt();
        sc.nextLine();

        Member member = dao.getDeptName(deptno);
        if (member != null) {
            System.out.println("회원번호 : " + member.getMemberNo());
            System.out.println("이름 : " + member.getName());
            System.out.println("부서코드 : " + member.getDeptCode());
            System.out.println("부서명 : " + member.getDeptName());
        } else {
            System.out.println("일치하는 회원이 없습니다.");
        }
    }
}
