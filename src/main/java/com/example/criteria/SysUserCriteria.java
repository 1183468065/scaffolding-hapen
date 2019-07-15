package  com.example.criteria;

import java.util.List;

public class SysUserCriteria extends PageCriteria{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Long> ids;
	
	//TODO:根据需要添加验证属性

	/**
	 * 电话
	 */
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setIds(List<Long> ids){
		this.ids = ids;
	}

	public List<Long> getIds(){
		return this.ids;
	}
	
}
