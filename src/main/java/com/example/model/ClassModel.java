package com.example.model;

import com.example.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;


public class ClassModel {
	private String primaryKeyName;//首字母大写
	private String primaryKeyNameLowercase;
	//package for project name
	private String packageFor;
	//moudle
	private String moduleName;
	//entity
	private String entityPackage;
	private String entityFullName;
	private String entityName;
	private String entityNameLowercase;
	//criteria
	private String criteriaPackage;
	private String criteriaFullName;
	private String criteriaName;
	private String criteriaNameLowercase;
	//Mapper
	private String mapperPackage;
	private String mapperFullName;
	private String mapperName;
	private String mapperNameLowercase;
	//Example
	private String examplePackage;
	private String exampleFullName;
	private String exampleName;
	//serviceInterface;
	private String interfacePackage;
	private String interfaceFullName;
	private String interfaceName;
	private String interfaceNameLowercase;
	//service
	private String servicePackage;
	private String serviceFullName;
	private String serviceName;
	//controller
	private String controllerPackage;
	private String controllerFullName;
	private String controllerName;
	private String controllerNameLowercase;
	//model
	private String modelPackage;//model类名
	private String modelFullName;//model包名+类名
	private String modelName;//model类名
	private String modelNameLowercase;//model小写名
	//webContext
	private String contextFullName;//context全类名
	private String contextName;//context类名
	private String loginSessionFullName;//context泛型类型全名
	private String loginSessionName;//context泛型类型
	private String retUserTypeFullName;//getUser返回类型全名
	private String retUserTypeName;//getUser
	private String retUserTypeNameLowercase;//getUser小写名
	private List<String> importClass;
	private List<String> methodsName;
	
	public ClassModel() {
		importClass = new ArrayList<String>();
	}
	public String getEntityPackage() {
		return entityPackage;
	}
	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getMapperPackage() {
		return mapperPackage;
	}
	public void setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
	}
	public String getMapperName() {
		return mapperName;
	}
	public void setMapperName(String mapperName) {
		this.mapperName = mapperName;
	}
	public String getInterfacePackage() {
		return interfacePackage;
	}
	public void setInterfacePackage(String interfacePackage) {
		this.interfacePackage = interfacePackage;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getServicePackage() {
		return servicePackage;
	}
	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getModelPackage() {
		return modelPackage;
	}
	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelNameLowercase() {
		return modelNameLowercase;
	}
	public void setModelNameLowercase(String modelNameLowercase) {
		this.modelNameLowercase = modelNameLowercase;
	}
	public List<String> getImportClass() {
		return importClass;
	}
	public void setImportClass(List<String> importClass) {
		this.importClass = importClass;
	}
	public String getRetUserTypeFullName() {
		return retUserTypeFullName;
	}
	public void setRetUserTypeFullName(String retUserTypeFullName) {
		this.retUserTypeFullName = retUserTypeFullName;
	}
	public List<String> getMethodsName() {
		return methodsName;
	}
	public void setMethodsName(List<String> methodsName) {
		this.methodsName = methodsName;
	}
	public String getCriteriaPackage() {
		return criteriaPackage;
	}
	public void setCriteriaPackage(String criteriaPackage) {
		this.criteriaPackage = criteriaPackage;
	}
	public String getCriteriaName() {
		return criteriaName;
	}
	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getEntityFullName() {
		return entityFullName;
	}
	public void setEntityFullName(String entityFullName) {
		this.entityFullName = entityFullName;
	}
	public String getCriteriaFullName() {
		return criteriaFullName;
	}
	public void setCriteriaFullName(String criteriaFullName) {
		this.criteriaFullName = criteriaFullName;
	}
	public String getMapperFullName() {
		return mapperFullName;
	}
	public void setMapperFullName(String mapperFullName) {
		this.mapperFullName = mapperFullName;
	}
	public String getInterfaceFullName() {
		return interfaceFullName;
	}
	public void setInterfaceFullName(String interfaceFullName) {
		this.interfaceFullName = interfaceFullName;
	}
	public String getServiceFullName() {
		return serviceFullName;
	}
	public void setServiceFullName(String serviceFullName) {
		this.serviceFullName = serviceFullName;
	}
	public String getModelFullName() {
		return modelFullName;
	}
	public void setModelFullName(String modelFullName) {
		this.modelFullName = modelFullName;
	}
	public String getCriteriaNameLowercase() {
		return criteriaNameLowercase;
	}
	public void setCriteriaNameLowercase(String criteriaNameLowercase) {
		this.criteriaNameLowercase = criteriaNameLowercase;
	}
	public String getPackageFor() {
		return packageFor;
	}
	public String getRetUserTypeNameLowercase() {
		return retUserTypeNameLowercase;
	}
	public void setRetUserTypeNameLowercase(String retUserTypeNameLowercase) {
		this.retUserTypeNameLowercase = retUserTypeNameLowercase;
	}
	public void setPackageFor(String packageFor) {
		this.packageFor = packageFor;
	}
	public String getExamplePackage() {
		return examplePackage;
	}
	public void setExamplePackage(String examplePackage) {
		this.examplePackage = examplePackage;
	}
	public String getExampleFullName() {
		return exampleFullName;
	}
	public void setExampleFullName(String exampleFullName) {
		this.exampleFullName = exampleFullName;
	}
	public String getExampleName() {
		return exampleName;
	}
	public void setExampleName(String exampleName) {
		this.exampleName = exampleName;
	}
	public String getInterfaceNameLowercase() {
		return interfaceNameLowercase;
	}
	public void setInterfaceNameLowercase(String interfaceNameLowercase) {
		this.interfaceNameLowercase = interfaceNameLowercase;
	}
	public String getMapperNameLowercase() {
		return mapperNameLowercase;
	}
	public void setMapperNameLowercase(String mapperNameLowercase) {
		this.mapperNameLowercase = mapperNameLowercase;
	}
	public String getEntityNameLowercase() {
		return entityNameLowercase;
	}
	public void setEntityNameLowercase(String entityNameLowercase) {
		this.entityNameLowercase = entityNameLowercase;
	}
	public String getPrimaryKeyName() {
		return primaryKeyName;
	}
	public void setPrimaryKeyName(String primaryKeyName) {
		this.primaryKeyName = primaryKeyName;
	}
	public String getPrimaryKeyNameLowercase() {
		return primaryKeyNameLowercase;
	}
	public void setPrimaryKeyNameLowercase(String primaryKeyNameLowercase) {
		this.primaryKeyNameLowercase = primaryKeyNameLowercase;
	}
	
	public String getControllerPackage() {
		return controllerPackage;
	}
	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}
	public String getControllerFullName() {
		return controllerFullName;
	}
	public void setControllerFullName(String controllerFullName) {
		this.controllerFullName = controllerFullName;
	}
	public String getControllerName() {
		return controllerName;
	}
	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}
	public String getControllerNameLowercase() {
		return controllerNameLowercase;
	}
	public void setControllerNameLowercase(String controllerNameLowercase) {
		this.controllerNameLowercase = controllerNameLowercase;
	}
	public String getContextFullName() {
		return contextFullName;
	}
	public void setContextFullName(String contextFullName) {
		this.contextFullName = contextFullName;
	}
	public String getContextName() {
		return contextName;
	}
	public String getLoginSessionFullName() {
		return loginSessionFullName;
	}
	public void setLoginSessionFullName(String loginSessionFullName) {
		this.loginSessionFullName = loginSessionFullName;
	}
	public void setContextName(String contextName) {
		this.contextName = contextName;
	}
	public String getLoginSessionName() {
		return loginSessionName;
	}
	public void setLoginSessionName(String loginSessionName) {
		this.loginSessionName = loginSessionName;
	}
	public String getRetUserTypeName() {
		return retUserTypeName;
	}
	public void setRetUserTypeName(String retUserTypeName) {
		this.retUserTypeName = retUserTypeName;
	}
	public static void setAtt(String classFullName, ClassType type, ClassModel classModel){
		String className =classFullName.substring(classFullName.lastIndexOf(".")+1);
		String classPackage = classFullName.substring(0,classFullName.lastIndexOf("."));
		ClassModel.addImportClass(classFullName, classModel.getImportClass());
		switch (type) {
		case ENTITY:
			// 根据传入的entity设置模块名
			String moduleName = className.replace("Entity", "");
			classModel.setModuleName(moduleName);
			classModel.setEntityName(className);
			classModel.setEntityFullName(classFullName);
			classModel.setEntityPackage(classPackage);
			classModel.setEntityNameLowercase(StringUtil.firstCharToLowerCase(className));
			// 根据entity设置所属包
			int projectPackageLenth = "gen.uhealth.erp.".length();
			String packageFor = classFullName.substring(projectPackageLenth, classFullName.length());
			packageFor = packageFor.substring(0, packageFor.indexOf("."));
			classModel.setPackageFor(packageFor);
			break;
		case MAPPER:
			classModel.setMapperName(className);
			classModel.setMapperFullName(classFullName);
			classModel.setMapperPackage(classPackage);
			classModel.setMapperNameLowercase(StringUtil.firstCharToLowerCase(className));
			break;
		case EXAMPLE:
			classModel.setExampleName(className);
			classModel.setExampleFullName(classFullName);
			classModel.setExamplePackage(classPackage);
			break;
		case SERVICE_INTERFACE:
			classModel.setInterfaceName(className);
			classModel.setInterfaceFullName(classFullName);
			classModel.setInterfacePackage(classPackage);
			classModel.setInterfaceNameLowercase(StringUtil.firstCharToLowerCase(className));
//			//添加接口需要的包
//			ClassModel.addImportClass(classModel.getInterfaceFullName(), classModel.getImportClass());
			break;
		case SERVICE:
			classModel.setServiceName(className);
			classModel.setServiceFullName(classFullName);
			classModel.setServicePackage(classPackage);
			//添加接口需要的包
//			ClassModel.addImportClass(classModel.getServiceFullName(), classModel.getImportClass());
			break;
		case CONTROLLER:
			classModel.setControllerName(className);
			classModel.setControllerFullName(classFullName);
			classModel.setControllerPackage(classPackage);
//			//添加接口需要的包
			ClassModel.addImportClass(classModel.getServiceFullName(), classModel.getImportClass());
			break;
		case CRITERIA:
			classModel.setCriteriaName(className);
			// 首字母小写
			classModel.setCriteriaNameLowercase(StringUtil.firstCharToLowerCase(className));
			classModel.setCriteriaFullName(classFullName);
			classModel.setCriteriaPackage(classPackage);
			break;
		case MODEL:
			classModel.setModelNameLowercase(StringUtil.firstCharToLowerCase(className));
			classModel.setModelFullName(classFullName);
			classModel.setModelName(className);
			classModel.setModelPackage(classPackage);
			break;
		/*case WEB_CONTEXT:
			//返回webContext的泛型
			Class<?> loginSessionClass = (Class<?>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0]; 
			classModel.setContextFullName(classFullName);
			classModel.setContextName(className);
			//添加loginsession信息
			ClassModel.addImportClass(loginSessionClass.getName(), classModel.getImportClass());
			classModel.setLoginSessionFullName(loginSessionClass.getName());
			classModel.setLoginSessionName(loginSessionClass.getSimpleName());
			//添加loginsession返回的用户类
			Class<?> getUserRetClazz = loginSessionClass.getMethod("getUserInfo").getReturnType();
			ClassModel.addImportClass(getUserRetClazz.getName(), classModel.getImportClass());
			classModel.setRetUserTypeFullName(getUserRetClazz.getName());
			classModel.setRetUserTypeName(getUserRetClazz.getSimpleName());
			classModel.setRetUserTypeNameLowercase(StringUtil.firstCharToLowerCase(getUserRetClazz.getSimpleName()));
			break;*/
		default:
			break;
		}
	}

	public static void addImportClass(String className, List<String> importClass) {
		if (!importClass.contains(className)) {
			importClass.add(className);
		}
	}
}
