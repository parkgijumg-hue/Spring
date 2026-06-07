package edu.employee.dao;

import edu.common.JDBCUtil;
import edu.employee.vo.EmployeeVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    private Connection conn = JDBCUtil.getConnection();


    @Override
    public List<EmployeeVO> getDepartmentEmployees(String deptTitle) throws SQLException {
        String sql = """
                SELECT E.EMP_NAME,
                       D.DEPT_TITLE,
                       J.JOB_NAME,
                       IFNULL(CAST(E.BONUS AS CHAR), '보너스 없음') AS BONUS,
                       CASE
                           WHEN E.ENT_YN = 'N' THEN '재직'
                           WHEN E.ENT_YN = 'Y' THEN '퇴사'
                       END AS ENT_YN
                FROM employee E
                JOIN department D ON E.DEPT_CODE = D.DEPT_ID
                JOIN job J ON E.JOB_CODE = J.JOB_CODE
                WHERE D.DEPT_TITLE = ?
                ORDER BY E.BONUS DESC
                """;

        List<EmployeeVO> employeeList = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, deptTitle);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    EmployeeVO employee = new EmployeeVO();

                    employee.setEmpName(rs.getString("EMP_NAME"));
                    employee.setDeptTitle(rs.getString("DEPT_TITLE"));
                    employee.setJobName(rs.getString("JOB_NAME"));
                    employee.setBonus(rs.getString("BONUS"));
                    employee.setEntYn(rs.getString("ENT_YN"));

                    employeeList.add(employee);
                }
            }
        }

        return employeeList;
    }

    @Override
    public List<EmployeeVO> getDepartmentAvgSalary() throws SQLException {
        String sql = """
                SELECT D.DEPT_TITLE,
                               J.JOB_NAME,
                               COUNT(*) AS EMPLOYEE_COUNT,
                               ROUND(AVG(E.SALARY)) AS AVG_SALARY
                        FROM employee E
                        JOIN department D ON E.DEPT_CODE = D.DEPT_ID
                        JOIN job J ON E.JOB_CODE = J.JOB_CODE
                        WHERE E.ENT_YN = 'N'
                        GROUP BY D.DEPT_TITLE, J.JOB_NAME
                        HAVING AVG(E.SALARY) >= 3000000
                        ORDER BY AVG_SALARY DESC
        """;
        List<EmployeeVO> employeeList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    EmployeeVO employee = new EmployeeVO();
                    employee.setDeptTitle(rs.getString("DEPT_TITLE"));
                    employee.setJobName(rs.getString("JOB_NAME"));
                    employee.setEmployeeCount(rs.getInt("EMPLOYEE_COUNT"));
                    employee.setAvgSalary(rs.getDouble("AVG_SALARY"));
                    employeeList.add(employee);
                }
            }
        }
         return employeeList;
    }

    @Override
    public List<EmployeeVO> getWorkingEmployees() throws SQLException {
        String sql = """
                SELECT IFNULL(D.DEPT_TITLE, '부서 없음') AS DEPT_TITLE,
                       J.JOB_NAME,
                       E.EMP_NAME,
                       E.SALARY
                FROM employee E
                LEFT JOIN department D ON E.DEPT_CODE = D.DEPT_ID
                JOIN job J ON E.JOB_CODE = J.JOB_CODE
                WHERE E.ENT_YN = 'N'
                ORDER BY J.JOB_NAME ASC
                LIMIT 10
                """;

        List<EmployeeVO> employeeList = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                EmployeeVO employee = new EmployeeVO();
                employee.setDeptTitle(rs.getString("DEPT_TITLE"));
                employee.setJobName(rs.getString("JOB_NAME"));
                employee.setEmpName(rs.getString("EMP_NAME"));
                employee.setSalary(rs.getInt("SALARY"));

                employeeList.add(employee);
            }
        }

        return employeeList;
    }

    @Override
    public int increaseSalary(String deptCode) throws SQLException {
        String sql = """
                UPDATE employee
                SET SALARY = ROUND(SALARY * 1.1)
                WHERE DEPT_CODE = ?
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, deptCode);
            return pstmt.executeUpdate();
        }
    }

    @Override
    public List<EmployeeVO> getEmployeesWithoutPhone() throws SQLException {
        String sql = """
                SELECT E.EMP_NAME,
                       IFNULL(E.PHONE, '없음') AS PHONE,
                       IFNULL(D.DEPT_TITLE, '부서 없음') AS DEPT_TITLE
                FROM employee E
                LEFT JOIN department D ON E.DEPT_CODE = D.DEPT_ID
                WHERE E.PHONE IS NULL
                ORDER BY E.EMP_NAME DESC
                """;

        List<EmployeeVO> employeeList = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                EmployeeVO employee = new EmployeeVO();
                employee.setEmpName(rs.getString("EMP_NAME"));
                employee.setPhone(rs.getString("PHONE"));
                employee.setDeptTitle(rs.getString("DEPT_TITLE"));

                employeeList.add(employee);
            }
        }

        return employeeList;
    }
}
