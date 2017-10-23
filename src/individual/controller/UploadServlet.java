package individual.controller;

import indiviudual.service.ResourceService;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			ResourceService.upload(request);
			request.setAttribute("message", "上传成功！");
			request.setAttribute("page", request.getContextPath()+"/servlet/ControllerServlet?v=toUpload");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "上传失败！");
			request.setAttribute("page", request.getContextPath()+"/servlet/ControllerServlet?v=toUpload");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);
		}
		
		/*DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);
			FileItem fi1 = fileItemList.get(1);
			System.out.println(fi1.getFieldName()+":"+fi1.getString("UTF-8"));
			FileItem fi2 = fileItemList.get(0);
			System.out.println(fi2.getContentType()+" "+fi2.getSize()+" "+fi2.getName());
			ServletContext servletContext = this.getServletContext();
			String path = servletContext.getRealPath("/WEB-INF/rsc/"+fi2.getName());
			File f = new File(path);
			fi2.write(f);
		} catch (FileUploadException e) {
			throw new RuntimeException(e);
		}catch (Exception e1) {
			throw new RuntimeException(e1);
		}
		
		*/
		
		
		//------------------------老方法 不管用-------------------------------------------------
		/*
		String path = request.getParameter("filer");
		System.out.println("用户的文件路径："+path);
		String des = request.getParameter("description");
		System.out.println("用户的文件描述："+des);
		
		try {
			ResourceService.upload(path, des);
			request.setAttribute("message", "上传成功！");
			request.setAttribute("page", request.getContextPath()+"/servlet/ControllerServlet?v=toUpload");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "上传失败！");
			request.setAttribute("page", request.getContextPath()+"/servlet/ControllerServlet?v=toUpload");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);
		}*/

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
