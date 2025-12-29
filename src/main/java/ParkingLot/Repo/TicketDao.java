package ParkingLot.Repo;

import java.sql.*;
import java.time.Instant;
import java.util.*;


public class TicketDao {
    public static void initializeDB() throws SQLException {
        Connection con = null;
        try {
            // Code to establish connection and create tables if they don't exist

            String query = "CREATE TABLE IF NOT EXISTS Tickets(Ticket_id SERIAL PRIMARY KEY," +
                    "Creation_time BIGINT NOT NULL," +
                    "STATUS VARCHAR(50) NOT NULL," +
                    "vehicle_number VARCHAR(20) NOT NULL," +
                    "vehicle_type VARCHAR(20) NOT NULL," +
                    "floor_number INT NOT NULL," +
                    "slot_number INT NOT NULL);";
            System.out.println("query is "+ query);
            con = getConnection();
            Statement st = con.createStatement();
            st.execute(query);
            con.close();
        } catch (Exception e){
            e.printStackTrace();
            con.close();
        }
    }

    public static int addRow(String status, String vehicleNumber, String vehicleType, int floorNumber, int slotNumber) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        try {
            String insertQuery = "INSERT INTO Tickets (STATUS, vehicle_number, vehicle_type, floor_number, slot_number, Creation_time ) VALUES ('"
                    + status + "','" + vehicleNumber + "','" + vehicleType + "'," + floorNumber + "," + slotNumber + "," + Instant.now().toEpochMilli() + ");";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.execute();
            ResultSet generatedKeys =stmt.getGeneratedKeys();
            if(generatedKeys.next()) {
                int ticketId = generatedKeys.getInt(1);
                System.out.println("Inserted row with Ticket ID: " + ticketId);
                connection.close();
                return ticketId;
            } else {
                System.out.println("Inserting row failed, no ID obtained.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
        throw new RuntimeException();
    }

    public static Map<String, Object> fetchRow(long ticketId){
        Map<String, Object> rowMap = new HashMap<>();
        try {
            Connection connection = getConnection();
            String fetchQuery = "SELECT * FROM Tickets where ticket_id = '"+ticketId+"';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(fetchQuery);
            int columnCount = resultSet.getMetaData().getColumnCount();
            if(resultSet.next()){
                for(int i=1;i<=columnCount;i++){
                    String columnName = resultSet.getMetaData().getColumnLabel(i);
                    rowMap.put(columnName, resultSet.getObject(columnName));
                }
            }
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rowMap;
    }

    public static boolean updateStatus(long ticketId, String newStatus) {
        try {
            Connection connection = getConnection();
            String updateQuery = "UPDATE Tickets SET STATUS = '"+newStatus+"' WHERE ticket_id = "+ticketId+";";
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateQuery);
            connection.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/sasdb";
            String userName = "postgres";//Add your postgres username
            String password = "nP2_enwctwBu";//Add your postgres password
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            System.out.println("Exception while getting connection: "+ e.getMessage());
            return null;
        }
    }
}
