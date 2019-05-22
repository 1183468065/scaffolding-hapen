package  ${modelPackage};

public class ${modelName} extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long ${primaryKeyNameLowercase};
	
	//TODO:根据需要添加验证属性
	
	public void set${primaryKeyName}(Long ${primaryKeyNameLowercase}){
		this.${primaryKeyNameLowercase}=${primaryKeyNameLowercase};
	}

	public Long get${primaryKeyName}(){
		return this.${primaryKeyNameLowercase};
	}
}
