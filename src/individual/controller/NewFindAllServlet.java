package individual.controller;

import indiviudal.domain.PageBean;
import indiviudal.domain.Resource;
import indiviudual.service.ResourceService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewFindAllServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int pc = getPac(request);
		int ps = 5;
		PageBean<Resource> pb = ResourceService.newFindAll(pc, ps);
		request.setAttribute("pageBean", pb);
		request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public int getPac(HttpServletRequest request){
		String value = request.getParameter("pc");
		if(value==null || value.trim().equals(""))
			return 1;
		return Integer.parseInt(value);
	}

}
