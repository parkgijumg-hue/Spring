package org.edu.member.vo;

import lombok.*;

// VO(Value Object) : 값 자체를 표현하고 의미를 갖는 객체
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {

    private int memberNo;
    private String id;
    private String pw;
    private String name;
    private String role;
    private String deptCode;
    private String deptName;
    private char deletedYn;

    public Member(String id, String pw, String name, String role) {
    }


}
