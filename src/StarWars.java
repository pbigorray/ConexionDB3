import java.sql.*;

public class StarWars {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost/starwars?" +
                "user=star&password=wars";
        try {
            Connection c = DriverManager.getConnection(url);
//            getPlanets(c);
//            insertCapi(c);
//            getJedis(c);
            getDeath(c);

            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getDeath(Connection c) {
        String sql = "SELECT c.name,f.episode,k.name FROM characters c " +
                "INNER JOIN deaths d on c.id=d.id_character " +
                "INNER JOIN characters k on d.id_killer=k.id " +
                "INNER JOIN films f on f.id=d.id_film " +
                "WHERE f.episode=\"Episode III\";";
        try (Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("c.name") + "\t" +
                        rs.getString("f.episode") + "\t" +
                        "Killer: "+rs.getString("k.name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getJedis(Connection c) {
        String sql = "SELECT name, c.id, a.affiliation FROM `characters` c " +
                "INNER JOIN character_affiliations ca on c.id = ca.id_character " +
                "INNER JOIN affiliations a on ca.id_affiliation=a.id " +
                "WHERE a.affiliation=\"Jedi Order\";";
        try (Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("c.id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("a.affiliation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertCapi(Connection c) {
        String sql = "insert into films(id,episode,title) values(?,?,?);";

        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setInt(1, 7);
            pstmt.setString(2, "Episode VII");
            pstmt.setString(3, "The Force Awakens");
            pstmt.executeUpdate();
            System.out.println("se han añadido");


            pstmt.setInt(1, 8);
            pstmt.setString(2, "Episode VIII");
            pstmt.setString(3, "The Last Jedi");
            pstmt.executeUpdate();
            System.out.println("se han añadido");


            pstmt.setInt(1, 9);
            pstmt.setString(2, "Episode IX");
            pstmt.setString(3, "The Rise of Skywalker");
            pstmt.executeUpdate();
            System.out.println("se han añadido");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getPlanets(Connection c) {
        String sql = "SELECT * FROM planets;";
        try (Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("climate") + "\t" +
                        rs.getString("created_date") + "\t" +
                        rs.getString("diameter") + "\t" +
                        rs.getString("gravity") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("orbital_period") + "\t" +
                        rs.getString("population") + "\t" +
                        rs.getString("rotation_period") + "\t" +
                        rs.getString("surface_water") + "\t" +
                        rs.getString("terrain") + "\t" +
                        rs.getString("updated_date") + "\t" +
                        rs.getString("url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
