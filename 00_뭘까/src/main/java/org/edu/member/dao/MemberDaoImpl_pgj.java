package org.edu.member.dao;

import org.edu.member.common.JDBCUtil;
import org.edu.member.vo.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl_pgj implements MemberDao {

    // JDBCUtil을 통해 Connection 객체 가져오기
    private Connection conn = JDBCUtil.getConnection();

    // 회원 등록
    @Override
    public int create(Member member) throws SQLException {

        // Statement를 사용하는 경우 sql문
        /*
        String sql = "INSERT INTO members VALUES (DEFAULT, "
                + member.getMemberId() + ", "
                + member.getMemberPw() +", "
                + member.getMemberName()+ ", "
                + member.getMemberRole()+", 'N');";
         */

        // PreparedStatement
        // -> Statement 의 자식으로 좀 더 향상된 기능을 제공
        // -> ?(위치 홀더)를 이용하여 SQL에 작성되어지는 리터럴을 동적으로 제어
        // -> 오타 위험 감소, 가독성 상승

        // sql문 작성 시 세미콜론(;)은 안쓰는 것이 관례
        String sql = "insert into members values(default,?,?,?,?,'N')";

        // try-with-resources문을 사용하여 작업이 끝나면 close()가 자동 호출됨
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPw());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getRole());

            // SELECT : executeQuery(); -> ResultSet 반환
            // DML    : executeUpdate(); -> 성공한 행의 개수 반환
            int result = pstmt.executeUpdate();

            // 성공 시 커밋
            if (result != 0) conn.commit();

            return result; // 성공한 행의 개수 반환
        }
    }

    @Override
    public List<Member> getList() throws SQLException {
        String sql = "select no, id, password, name, role, deleted_yn from members where deleted_yn='N' order by no";
        List<Member> memberList = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Member member = new Member();
                member.setMemberNo(rs.getInt("no"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("password"));
                member.setName(rs.getString("name"));
                member.setRole(rs.getString("role"));
                member.setDeletedYn(rs.getString("deleted_yn").charAt(0));

                memberList.add(member);
            }
        }

        return memberList;
    }

    @Override
    public Member get(int memberNo) throws SQLException {
        String sql = "select no, id, password, name, role, deleted_yn from members where no=? and deleted_yn='N'";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberNo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Member member = new Member();
                    member.setMemberNo(rs.getInt("no"));
                    member.setId(rs.getString("id"));
                    member.setPw(rs.getString("password"));
                    member.setName(rs.getString("name"));
                    member.setRole(rs.getString("role"));
                    member.setDeletedYn(rs.getString("deleted_yn").charAt(0));

                    return member;
                }
            }
        }

        return null;
    }

    @Override
    public int update(Member member) throws SQLException {
        String sql = "update members set name=?,role=? where no=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getRole());
            pstmt.setInt(3, member.getMemberNo());
            int result = pstmt.executeUpdate();
            if (result != 0) conn.commit();
            return result;
        }
    }

    @Override
    public int delete(int memberNo) throws SQLException {
        String sql = "update members set deleted_yn='Y' where no=? and deleted_yn='N'";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberNo);
            int result = pstmt.executeUpdate();
            if (result != 0) conn.commit();
            return result;
        }
    }

    @Override
    public Member getDeptName(int deptNo) throws SQLException {

        Member member = null;
        String sql = """

        select m.no, m.name, m.dept_no, d.dept_name
        from members m
        join departments d on m.dept_no = d.dept_no
        where m.dept_no = ?
          and m.deleted_yn = 'N'
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, deptNo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    member = new Member();
                    member.setMemberNo(rs.getInt("no"));
                    member.setName(rs.getString("name"));
                    member.setDeptCode(rs.getString("dept_no"));
                    member.setDeptName(rs.getString("dept_name"));
                }
            }

        }

        return member; // 존재하면 member , 없으면 null
    }
}
