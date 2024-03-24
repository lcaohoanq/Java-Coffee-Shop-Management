package services;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.EnvUtils;

public class DatabaseConnection {
    public static void main(String[] args) {
        String sql = "SELECT * FROM Ingredient";

        SQLServerDataSource ds = new SQLServerDataSource();

        ds.setUser(EnvUtils.get("DB_USER"));
        ds.setPassword(EnvUtils.get("DB_PASSWORD"));
        ds.setServerName(EnvUtils.get("DB_HOST"));
        ds.setPortNumber(Integer.parseInt(EnvUtils.get("DB_PORT")));
        ds.setDatabaseName(EnvUtils.get("DB_TABLE"));

        ds.setTrustServerCertificate(true); // Set trustServerCertificate to true to bypass certificate validation

        try(Connection conn = ds.getConnection()){
            System.out.println("Connection successful!");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                float quantity = rs.getFloat("quantity");
                String unit = rs.getString("unit");
                String price = rs.getString("price");
                System.out.printf("ID: %s, Name: %-20s, Quantity: %-5.2f, Unit: %-5s, Price: %-10s\n", id, name, quantity, unit, price);
            }
            System.out.println(conn.getMetaData());
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}