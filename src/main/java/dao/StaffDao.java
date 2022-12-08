package dao;

import model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StaffDao implements IStaffDao{
    private String jdbcURL = "jdbc:mysql://localhost:3306/staff?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345678";
    private static final String INSERT_STAFF_SQL = "INSERT INTO new_table (name, email, address , phonenumber , salary , department) VALUES (?, ?, ? , ? , ? , ?);";
    private static final String SELECT_STAFF_BY_ID = "select id,name,email,address , phonenumber , salary, department from users where id =?";
    private static final String SELECT_ALL_STAFF = "select * from new_table;";
    private static final String DELETE_STAFF_SQL = "delete from new_table where id = ?;";
    private static final String UPDATE_STAFF_SQL = "update new_table set name = ?,email= ?, address =? , phonenumber = ? , salary = ? , department = ? where id = ?;";
    public StaffDao(){

    }
    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public void insertStaff(Staff staff) {
        System.out.println(INSERT_STAFF_SQL);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STAFF_SQL)) {
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getEmail());
            preparedStatement.setString(3, staff.getAddress());
            preparedStatement.setString(4, String.valueOf(staff.getPhoneNumber()));
            preparedStatement.setString(5, String.valueOf(staff.getSalary()));
            preparedStatement.setString(6, staff.getDepartment());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }

    }
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    @Override
    public Staff selectStaff(int id) {
        Staff staff = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STAFF_BY_ID);) {
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id1 = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phonenumber = rs.getString("phonenumer");
                String salary = rs.getString("salary");
                String department = rs.getString("department");
                staff = new Staff(id, name, email, address , phonenumber , salary ,department );
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return staff;
    }

    @Override
    public List<Staff> selectAllStaff() {
        List<Staff> staffs = new ArrayList<>();
        try (
                Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STAFF)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phonenumber = rs.getString("phonenumber");
                String salary = rs.getString("salary");
                String department = rs.getString("department");
                staffs.add(new Staff(id, name, email, address , phonenumber , salary , department));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return staffs;
    }


    @Override
    public boolean deleteStaff(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_STAFF_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateStaff(Staff staff) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_STAFF_SQL);) {
            statement.setString(1, staff.getName());
            statement.setString(2, staff.getEmail());
            statement.setString(3, staff.getAddress());
            statement.setInt(4, staff.getId());
            statement.setInt(5, Integer.parseInt(String.valueOf(staff.getPhoneNumber())));
            statement.setInt(6, Integer.parseInt(String.valueOf(staff.getSalary())));
            statement.setInt(7, Integer.parseInt(staff.getDepartment()));

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    }
