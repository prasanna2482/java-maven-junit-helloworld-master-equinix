public class UnsafeSQL {
    public void runQuery(String userInput) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "user", "pass");
        Statement stmt = conn.createStatement();
        // BAD: User input directly in SQL query
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE name = '" + userInput + "'");
        while (rs.next()) {
            System.out.println(rs.getString("email"));
        }
    }
}
