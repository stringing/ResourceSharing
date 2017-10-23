package individual.dao;

import individual.utils.JDBCUtil;
import individual.utils.TimeUtil;
import indiviudal.domain.PageBean;
import indiviudal.domain.Resource;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ResourceDao {
	
	/*public static List findAll(){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Resource> rList = new ArrayList<Resource>();
		String sql = "SELECT NAME,DESCRIPTION FROM LIST";
		Map<Integer, List> map = new HashMap<Integer, List>();
		try {
			con = JDBCUtil.getCon();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			for(int i = 0; rs.next(); i++){
				List<String> list = new ArrayList();
				list.add(rs.getString(1));
				list.add(rs.getString(2));
				System.out.println("查找到的文件名和文件描述："+rs.getString(1)+" ，"+rs.getString(2));
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
			System.out.println((String)me.getValue().get(0)+":entryset里的文件名");
			Resource r = new Resource();
			r.setName((String)me.getValue().get(0));
			r.setDescription((String)me.getValue().get(1));
			rList.add(r);
			System.out.println("文件和描述： "+me.getValue().get(0)+":"+me.getValue().get(1));
		}
		return rList;
	}*/

	public static void upload(HttpServletRequest request)throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		
		Connection con = null;
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);
			FileItem fi1 = fileItemList.get(1);
			System.out.println(fi1.getFieldName()+":"+fi1.getString("UTF-8"));
			String des = fi1.getString("UTF-8");
			FileItem fi2 = fileItemList.get(0);
			String name = fi2.getName();
			System.out.println(fi2.getContentType()+" "+fi2.getSize()+" "+fi2.getName());
			/*File f = new File(path);
			fi2.write(f);*/
			InputStream in = fi2.getInputStream();
			
			if(fi2.getSize()==0)
				throw new Exception();
			
			
			//InputStream in = new FileInputStream(path);
			Timestamp ts = Timestamp.valueOf(TimeUtil.getTime());
			con = JDBCUtil.getCon();
			con.setAutoCommit(false);
			String sql = "INSERT INTO LIST VALUES(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, des);
			pstmt.setBlob(3, in);
			pstmt.setTimestamp(4, ts);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			con.close();
			in.close();
		} catch (Exception e) {
			con.rollback();
			throw new RuntimeException(e);
		}
		
	}

	/*public static void download(int id, String path) throws Exception{
		Connection con = null;
		String sql = "SELECT * FROM LIST";
		Blob sb = null;
		List<Resource> list = new ArrayList();
		System.out.print("下载的路径："+path);
		String realPath = path.replaceAll("\\\\", "\\\\\\\\");
		
		try {
			con = JDBCUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Resource r = new Resource();
				sb = new SerialBlob(rs.getBlob(3));
				r.setFile(sb);
				r.setName(rs.getString(1));
				list.add(r);
			}
			FileOutputStream fos = new FileOutputStream(realPath+"\\"+list.get(id-1).getName());
			fos.write(sb.getBytes(1, (int)sb.length()), 0, (int)sb.length());
			fos.close();
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}*/
	
	public static void download2(int id, String path, HttpServletResponse response){
		Connection con = null;
		String sql = "SELECT * FROM LIST ORDER BY TIME DESC";
		Blob sb = null;
		List<Resource> list = new ArrayList<Resource>();
		System.out.print("下载的路径："+path);
		
		try {
			con = JDBCUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Resource r = new Resource();
				sb = new SerialBlob(rs.getBlob(3));
				r.setFile(sb);
				r.setName(rs.getString(1));
				list.add(r);
			}
			OutputStream out = response.getOutputStream();
			out.write(list.get(id-1).getFile().getBytes(1, (int)list.get(id-1).getFile().length()), 0, (int)list.get(id-1).getFile().length());
			//FileOutputStream fos = new FileOutputStream(realPath+"\\"+list.get(id-1).getName());
			//fos.write(sb.getBytes(1, (int)sb.length()), 0, (int)sb.length());
			//fos.close();
			out.close();
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static PageBean<Resource> newFindAll(int pc, int ps) {
		Connection con = null;
		try{
			PageBean<Resource> pb = new PageBean<Resource>();
			pb.setPc(pc);
			pb.setPs(ps);
			String sql = "SELECT COUNT(*) FROM LIST";
			con = JDBCUtil.getCon();
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int tr;
			while(rs.next()){
				tr = rs.getInt(1);
				System.out.println("----------------> "+tr);
				pb.setTr(tr);
			}
			String sql2 = "SELECT * FROM LIST ORDER BY TIME DESC LIMIT ?,? ";
			PreparedStatement pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, (pc - 1)*ps);
			pstmt.setInt(2, ps);
			ResultSet rs2 = pstmt.executeQuery();
			List<Resource> beanList = new ArrayList<Resource>();
			while(rs2.next()){
				Resource r = new Resource();
				r.setName(rs2.getString(1));
				r.setDescription(rs2.getString(2));
				String time = rs2.getString(4);
				r.setTime(time.substring(0, time.lastIndexOf(".")));
				beanList.add(r);
			}
			pb.setBeanList(beanList);
			con.commit();
			rs2.close();
			pstmt.close();
			con.close();
			return pb;
			
		}catch(SQLException e){
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}
	}
}
