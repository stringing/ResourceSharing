package indiviudual.service;

import individual.dao.ResourceDao;
import indiviudal.domain.PageBean;
import indiviudal.domain.Resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ResourceService {

	public static void upload(HttpServletRequest request) throws Exception {
		try {
			ResourceDao.upload(request);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}


	public static PageBean<Resource> newFindAll(int pc, int ps) {
		return ResourceDao.newFindAll(pc, ps);
	}
}
