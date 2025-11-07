package hellofx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private static final String DB_URL = "jdbc:sqlite:bank.db";

    public static Connection getConnection() throws SQLException {
        try {
            // ✅ Explicitly load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found", e);
        }
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeSchema() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS customers (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL,
                    password TEXT NOT NULL,
                    customer_type TEXT,
                    tax_id TEXT,
                    source_of_income TEXT,
                    signatory TEXT,
                    id_number TEXT,
                    created_date TEXT
                );
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS accounts (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    customer_id INTEGER,
                    account_number TEXT,
                    account_type TEXT,
                    branch TEXT,
                    balance REAL,
                    FOREIGN KEY (customer_id) REFERENCES customers(id)
                );
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS transactions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    account_number TEXT,
                    date TEXT,
                    type TEXT,
                    amount REAL,
                    balance REAL
                );
            """);

            System.out.println("✅ Database schema initialized successfully.");
        } catch (SQLException e) {
            System.err.println("❌ Failed to initialize database schema:");
            e.printStackTrace();
        }
    }

    // Optional: helper to get current timestamp in standard format
    public static String getFormattedDateTime() {
        return java.time.LocalDateTime.now().toString();
    }
}