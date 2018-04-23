package cn.hdu.usv.action;

import java.util.List;

import cn.hdu.usv.dao.ShipInforDao;
import cn.hdu.usv.dao.impl.ShipInforImpl;
import cn.hdu.usv.mavlink.mavlinkjava.common.ShipInformation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListAction extends ActionSupport{
	
	private static final long serialVersionUID = 12L;
	private ShipInforDao shiDao = new ShipInforImpl();
	
	public String list()
	{
		try {
			
			List<ShipInformation> list  = shiDao.getAll();
			ActionContext.getContext().getSession().put("shipdata", list);
			return "list";
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
		
	}
	

}
