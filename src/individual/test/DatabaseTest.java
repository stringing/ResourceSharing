package individual.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import individual.utils.JDBCUtil;
import indiviudal.domain.Resource;

import org.junit.Test;

public class DatabaseTest {
	@Test
	public void upload() throws IOException{//上传功能的最初模型
		Connection con = null;
		try {
			InputStream in = new FileInputStream("G:\\git-push-command.txt");
			con = JDBCUtil.getCon();
			String sql = "INSERT INTO LIST VALUES(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "TestFile");
			pstmt.setString(2, "This is a file for testing");
			pstmt.setBlob(3, in);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			in.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Test
	public void download() throws SerialException, SQLException, IOException{//下载功能的最初模型
		Connection con = null;
		String sql = "SELECT * FROM LIST WHERE NAME = ?";
		Blob sb = null;
		try {
			con = JDBCUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "TestFile");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				sb = new SerialBlob(rs.getBlob(3));
			}
			FileOutputStream fos = new FileOutputStream("G:\\1.bat");
			fos.write(sb.getBytes(1, (int)sb.length()), 0, (int)sb.length());
			fos.close();
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List findAll(){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Resource> rList = new ArrayList();
		Resource r = new Resource();
		String sql = "SELECT NAME,DESCRIPTION FROM LIST";
		Map<Integer, List> map = new HashMap<Integer, List>();
		try {
			con = JDBCUtil.getCon();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			for(int i = 0; rs.next(); i++){
				List list = new ArrayList();
				list.add(rs.getString(1));
				list.add(rs.getString(2));
				map.put(i, list);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					throw new RuntimeException("rs关闭失败！");
				}
			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new RuntimeException("stmt关闭失败！");
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					throw new RuntimeException("con关闭失败！");
				}
		}
		Set<Map.Entry<Integer, List>> set = map.entrySet();
		Iterator<Map.Entry<Integer, List>> it = set.iterator();
		while(it.hasNext()){
			Map.Entry<Integer, List> me = it.next();
			r.setName((String)me.getValue().get(0));
			r.setDescription((String)me.getValue().get(1));
			rList.add(r);
			System.out.println("File "+me.getValue().get(0)+":"+me.getValue().get(1));
		}
		return rList;
	}
}
