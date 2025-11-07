package hellofx.database;

import java.sql.*;

public class CustomerDAO {
    public static int addCustomer(String username, String password, String customerType,
                                  String taxId, String sourceOfIncome, String signatory,
                                  String idNumber, String createdDate) {
        String sql = "INSERT INTO customers (username, password, customer_type, tax_id, source_of_income, signatory, id_number, created_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, customerType);
            pstmt.setString(4, taxId);
            pstmt.setString(5, sourceOfIncome);
            pstmt.setString(6, signatory);
            pstmt.setString(7, idNumber);
            pstmt.setString(8, createdDate);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getCustomerId(String username, String password) {
        String sql = "SELECT id FROM customers WHERE username = ? AND password = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getInt("id") : -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}