import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BirthdayDAO {
    private Connection getConnection() {
        try {
            return Database.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed", e);
        }
    }

    public void addBirthday(Birthday b) {
        String sql = "INSERT INTO birthday (name, birthdate) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getName());
            ps.setDate(2, Date.valueOf(b.getBirthdate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            handleSQLException("Error adding birthday", e);
        }
    }

    public void deleteBirthday(int id) {
        String sql = "DELETE FROM birthday WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            handleSQLException("Error deleting birthday", e);
        }
    }

    public List<Birthday> getAllBirthdays() {
        List<Birthday> list = new ArrayList<>();
        String sql = "SELECT * FROM birthday ORDER BY MONTH(birthdate), DAY(birthdate)";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Birthday(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("birthdate").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            handleSQLException("Error getting birthdays", e);
        }
        return list;
    }

    public List<Birthday> searchByNameOrMonth(String query) {
        List<Birthday> list = new ArrayList<>();
        String sql = "SELECT * FROM birthday WHERE name LIKE ? OR MONTHNAME(birthdate) LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Birthday(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDate("birthdate").toLocalDate()
                    ));
                }
            }
        } catch (SQLException e) {
            handleSQLException("Error searching birthdays", e);
        }
        return list;
    }

    public List<Birthday> getTodaysBirthdays() {
        List<Birthday> list = new ArrayList<>();
        String sql = "SELECT * FROM birthday " +
                "WHERE MONTH(birthdate) = MONTH(CURRENT_DATE) " +
                "AND DAY(birthdate) = DAY(CURRENT_DATE)";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Birthday(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("birthdate").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            handleSQLException("Error getting today's birthdays", e);
        }
        return list;
    }

    private void handleSQLException(String message, SQLException e) {
        System.err.println(message + ": " + e.getMessage());
        e.printStackTrace();
    }
}
