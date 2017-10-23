package individual.test;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.junit.Test;

public class ListTest {
	@Test
	public void listTest(){
		/*String url = "jdbc:mysql://localhost:3306/MYTEST?useSSL=false";
		String driverName = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "root";
		try {
			Class.forName(driverName);
			Connection con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM TEST ORDER BY TIME";
			String sql2 = "INSERT INTO TEST VALUES('2017-07-25')";
			stmt.executeUpdate(sql2);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}catch (SQLException e1) {
			throw new RuntimeException(e1);
		}*/
	}
}
