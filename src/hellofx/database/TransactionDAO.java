package hellofx.database;

import java.sql.*;

public class TransactionDAO {
    public static void logTransaction(String accountNumber, String date, String type, double amount, double balance) {
        String sql = "INSERT INTO transactions (account_number, date, type, amount, balance) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            pstmt.setString(2, date);
            pstmt.setString(3, type);
            pstmt.setDouble(4, amount);
            pstmt.setDouble(5, balance);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}