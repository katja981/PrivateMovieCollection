package DAL;
public class MovieDAO {
    public Movie add(Movie movie) throws PlayerException {
        String sql = "INSERT INTO movies (title, genres, imdbRating, personalRating) VALUES (?, ?, ?, ?)";
        try (Connection c = con.DBConnection();
             PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenres());
            stmt.setDouble(3, movie.getImdbRating());
            stmt.setDouble(4, movie.getPersonalRating());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    movie.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to retrieve generated ID.");
                }
            }
        } catch (SQLException e) {
            throw new PlayerException(e);
        }
        return movie;
    }

    public Movie delete(Movie movie) throws PlayerException {
        try {
            Connection c = con.DBConnection();
            String sql = "DELETE FROM movies WHERE id = ?";
            System.out.println(movie.getId());
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, movie.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movie;
    }
}
