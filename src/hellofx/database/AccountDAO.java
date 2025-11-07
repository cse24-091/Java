package hellofx.database;

import java.sql.*;

public class AccountDAO {
    public static void addAccount(int customerId, String accountNumber, String accountType, String branch, double balance) {
        String sql = "INSERT INTO accounts (customer_id, account_number, account_type, branch, balance) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.setString(2, accountNumber);
            pstmt.setString(3, accountType);
            pstmt.setString(4, branch);
            pstmt.setDouble(5, balance);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean updateBalance(String accountNumber, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, accountNumber);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}