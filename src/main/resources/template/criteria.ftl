package  ${criteriaPackage};

import java.util.List;

public class ${criteriaName} extends PageCriteria{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//TODO:根据需要添加验证属性
	
	private List<Long> ${primaryKeyNameLowercase}s;
	
	//TODO:根据需要添加验证属性
	
	public void set${primaryKeyName}s(List<Long> ${primaryKeyNameLowercase}s){
		this.${primaryKeyNameLowercase}s = ${primaryKeyNameLowercase}s;
	}

	public List<Long> get${primaryKeyName}s(){
		return this.${primaryKeyNameLowercase}s;
	}
	
}
