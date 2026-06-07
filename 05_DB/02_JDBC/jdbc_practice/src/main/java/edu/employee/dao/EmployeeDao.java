package edu.employee.dao;

import edu.employee.vo.EmployeeVO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {


    // 조회 기능은 여러 직원을 반환 -> list 사용
    // 급여인상은 수정된 행 개수가 필요하므로 int 반환
    List<EmployeeVO> getDepartmentEmployees(String deptTitle) throws SQLException;

    List<EmployeeVO> getDepartmentAvgSalary() throws SQLException;

    List<EmployeeVO> getWorkingEmployees() throws SQLException;

    int increaseSalary(String deptCode) throws SQLException;

    List<EmployeeVO> getEmployeesWithoutPhone() throws SQLException;


}
