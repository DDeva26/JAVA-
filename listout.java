Listoutallat.java code:

package memorygame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
public class Listoutallat extends javax.swing.JPanel {
 public Listoutallat(String name) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
          Properties prop = new Properties();
                    InputStream input = null;
                    input = new FileInputStream("config.properties");
               // load a properties file
                 prop.load(input);           
                Class.forName(prop.getProperty("classname"));
                    Connection con=DriverManager.getConnection(prop.getProperty("databasepath"),prop.getProperty("dbuser"),prop.getProperty("dbpassword"));
         String sql="select * from "+prop.getProperty("tablename")+" where name='"+name+"';";
         Statement stmt=con.createStatement();
     ResultSet rs = stmt.executeQuery(sql);
    // It creates and displays the table
    JTable table = new JTable(buildTableModel(rs));
    // Closes the Connection
   JOptionPane.showMessageDialog(null, new JScrollPane(table));
    }
      private static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
         ResultSetMetaData metaData = rs.getMetaData();
          // names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }
            // data of the table
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }
            return new DefaultTableModel(data, columnNames);
    }
}











