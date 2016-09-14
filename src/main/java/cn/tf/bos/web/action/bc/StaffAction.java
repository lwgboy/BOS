package cn.tf.bos.web.action.bc;

import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.tf.bos.domain.bc.Staff;
import cn.tf.bos.domain.bc.Standard;
import cn.tf.bos.domain.user.User;
import cn.tf.bos.page.PageRequestBean;
import cn.tf.bos.page.PageResponseBean;
import cn.tf.bos.web.action.BaseAction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

//标准管理
public class StaffAction extends BaseAction  implements ModelDriven<Staff>{
	
	private Staff staff=new Staff();
	
	public Staff getModel() {
		return staff;
	}
	
	public String saveOrUpdate(){
		staffService.saveOrUpdate(staff);
		return "saveOrUpdate";
	}
	
	//分页查询
	public String findByPage(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		PageRequestBean pageRequestBean = initPageRequestBean(detachedCriteria);

		// 调用业务层，进行查询 结果PageResponseBean
		PageResponseBean pageResponseBean = staffService.findByPage(pageRequestBean);

		// 将结果 转换 json
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);

		return "findByPage";
	}
	
	public String deleteOrRestom(){
		

		String value="1";
		String[] ids=staff.getId().split(", ");

		staffService.delete(ids,value);
		return "delete";
	}
	
	

}