package individual.controller;

import individual.test.DatabaseTest;
import indiviudal.domain.PageBean;
import indiviudal.domain.Resource;
import indiviudual.service.ResourceService;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		String name = arg0.getParameter("v");
		try {
			Method method = ControllerServlet.class.getMethod(name, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, arg0, arg1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		/*List<Resource> l = ResourceService.findAll();
		request.setAttribute("list", l);
		request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);*/
		
		int pc = getPac(request);
		int ps = 4;
		PageBean<Resource> pb = ResourceService.newFindAll(pc, ps);
		request.setAttribute("pageBean", pb);
		request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
	}
	
	public int getPac(HttpServletRequest request){
		String value = request.getParameter("pc");
		if(value==null || value.trim().equals(""))
			return 1;
		return Integer.parseInt(value);
	}
	
	public void toUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/jsp/upload.jsp").forward(request, response);
	}
	
	

}
