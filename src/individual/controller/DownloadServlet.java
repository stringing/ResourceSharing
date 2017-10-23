package individual.controller;

import individual.dao.ResourceDao;
import indiviudual.service.ResourceService;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String path = request.getParameter("path");
		String realname = request.getParameter("name");
		//--------------------开始测试改造------------------------------------------------
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
		ResourceDao.download2(id, path, response);
		/*try {
			//ResourceService.download(id, path);
			request.setAttribute("page", request.getContextPath()+"/servlet/ControllerServlet?v=findAll");
			request.setAttribute("message", "下载成功！");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("page", request.getContextPath()+"/servlet/ControllerServlet?v=findAll");
			request.setAttribute("message", "下载失败！");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);
		}*/
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
