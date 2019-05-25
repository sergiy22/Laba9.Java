
package com.company;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DBInstance {

    private static Connection dbSingleAntone;

    static void addParameter(PreparedStatement stat, Integer num, Object param) throws SQLException {
        if (param instanceof String) {
            String value = (String) param;
            stat.setString(num, value);
        }
        if (param instanceof Integer) {
            Integer value = (Integer) param;
            stat.setInt(num, value);

        }
        if (param instanceof Boolean) {
            Boolean value = (Boolean) param;
            stat.setBoolean(num, value);

        }
        if (param instanceof Date) {
            Date value = (Date) param;
            stat.setDate(num, value);

        }

    }

    static Integer createRecord(Connection con, String tabName, ArrayList<String> fields, ArrayList<Object> values) throws SQLException {
        int recordID = 0;
        int counter = 0;
        String query = " insert into " + tabName + " (";
//      query += "id";
        for (String field : fields) {
            query += (counter > 0) ? ", " + field : field;
            ++counter;
        }
        query += ") values (? ";
        for (int i = 0; i < counter - 1; i++) {
            query += ", ?";
        }
        query += ")";
        System.out.println(query);
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(query);
        for (int i = 0; i < values.size(); i++) {
            addParameter(preparedStmt, i + 1, values.get(i));
        }

        // execute the preparedstatement
        preparedStmt.execute();
        ResultSet rs = preparedStmt.getGeneratedKeys();
        if (rs.next()) {
            recordID = rs.getInt(1);
            System.out.println(String.valueOf(recordID));
        }

        return recordID;
    }

    public DBInstance() {

    }

    public static Connection getInstance() {
        if (dbSingleAntone == null) {

            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String databaseURL = "jdbc:ucanaccess://С://java/MusicDB_2.0.accdb";

            try (Connection connection = DriverManager.getConnection(databaseURL)) {
                dbSingleAntone = connection;

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return dbSingleAntone;
    }

}
