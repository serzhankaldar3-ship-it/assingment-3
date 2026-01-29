import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmRepository {

    public void addFilm(Film film) {
        String sql = "INSERT INTO films (title, price) VALUES (?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, film.getTitle());
            pstmt.setInt(2, film.getPrice());
            pstmt.executeUpdate();
            System.out.println("Film added successfully");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT id, title, price FROM films";

        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Film film = new Film(rs.getString("title"), rs.getInt("price"));
                film.setIdFilm(rs.getInt("id")); // ✅ Базада id
                films.add(film);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return films;
    }

    public void updatePrice(int id, int newPrice) {
        String sql = "UPDATE films SET price = ? WHERE id = ?"; // ✅ id
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newPrice);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Price updated successfully");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteFilm(int id) {
        String sql = "DELETE FROM films WHERE id = ?"; // ✅ id
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Film deleted successfully");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

