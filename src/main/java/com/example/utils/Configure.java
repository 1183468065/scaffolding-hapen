package com.example.utils;

import com.example.code.gen.ServiceCodeGenerator;
import com.example.model.ClassModel;
import com.example.model.ClassType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Configure {
	private static Configure instance;
	private Properties pros = new Properties();
	private List<String> entityList;
	private List<String> entityPrimaryIdList;
	private String entityPackage;
	private String criteriaPackage;
	private String modelPackage;
	private String serviceImplPackage;
	private String serviceInterfacePackage;
	private String controllerPackage;
	private String providerFolder;
	private String apiFolder;
	private String examplePackage;
	private String mapperPackage;
	private String controllerFolder;

	private Configure() {
		this("/service/conf.properties");
	}

	private Configure(String location) {
		super();
		try {
			pros.load(ServiceCodeGenerator.class.getResourceAsStream(location));
			this.entityList = Arrays.asList(pros.getProperty("entityList").split(","));
			this.entityPrimaryIdList = Arrays.asList(pros.getProperty("entityPrimaryIdList").split(","));
			this.entityPackage = pros.getProperty("entityPackage");
			this.criteriaPackage = pros.getProperty("criteriaPackage");
			this.modelPackage = pros.getProperty("modelPackage");
			this.serviceImplPackage = pros.getProperty("serviceImplPackage");
			this.providerFolder = pros.getProperty("providerFolder");
			this.apiFolder = pros.getProperty("apiFolder");
			this.examplePackage = pros.getProperty("entityPackage");
			this.mapperPackage = pros.getProperty("mapperPackage");
			this.serviceInterfacePackage = pros.getProperty("serviceInterfacePackage");
			this.controllerPackage = pros.getProperty("controllerPackage");
			this.controllerFolder = pros.getProperty("controllerFolder");
		} catch (Exception e) {
			System.err.println("加载配置文件出错");
			System.exit(0);
		}
	}

	public static Configure getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return new Configure();
		}
	}

	public static Configure getInstance(String location) {
		if (instance != null) {
			return instance;
		} else {
			return new Configure(location);
		}
	}

	public List<ClassModel> generatorClassModelAtt() {
		List<ClassModel> classModels = new ArrayList<>();
		for (int i = 0; i < this.entityList.size(); i++) {
			String entity = StringUtil.firstCharToUpperCase(entityList.get(i));
			String primaryKeyName = this.entityPrimaryIdList.get(i);
			ClassModel classModel = new ClassModel();

			// 判断是否有自定义主键
			if (StringUtils.isEmpty(primaryKeyName)) {
				primaryKeyName = "id";
			}
			// 转换主键大小写
			classModel.setPrimaryKeyName(StringUtil.firstCharToUpperCase(primaryKeyName));
			classModel.setPrimaryKeyNameLowercase(StringUtil.firstCharToLowerCase(primaryKeyName));

			// 根据entity的包名和entity对应的类名拼接全名
			String genEntity = this.entityPackage.concat(".").concat(entity).concat("Entity");
			String crietria = this.criteriaPackage.concat(".").concat(entity).concat("Criteria");
			String model = this.modelPackage.concat(".").concat(entity).concat("");
			String example = this.examplePackage.concat(".").concat(entity).concat("EntityExample");
			String mapper = this.mapperPackage.concat(".").concat(entity).concat("EntityMapper");
			String serviceImpl = this.serviceImplPackage.concat(".").concat(entity).concat("ServiceImpl");
			String service = this.serviceInterfacePackage.concat(".").concat(entity).concat("Service");
			String controller = this.controllerPackage.concat(".").concat(entity).concat("Controller");

			ClassModel.setAtt(genEntity, ClassType.ENTITY, classModel);
			ClassModel.setAtt(crietria, ClassType.CRITERIA, classModel);
			ClassModel.setAtt(model, ClassType.MODEL, classModel);
			ClassModel.setAtt(mapper, ClassType.MAPPER, classModel);
			ClassModel.setAtt(example, ClassType.EXAMPLE, classModel);
			ClassModel.setAtt(service, ClassType.SERVICE_INTERFACE, classModel);
			ClassModel.setAtt(serviceImpl, ClassType.SERVICE, classModel);
			ClassModel.setAtt(controller, ClassType.CONTROLLER, classModel);
			// ClassModel.setAtt(entity, ClassType.CONTROLLER, classModel);

			classModels.add(classModel);
		}

		return classModels;
	}

	public List<String> getEntityList() {
		return entityList;
	}

	public List<String> getEntityPrimaryIdList() {
		return entityPrimaryIdList;
	}

	public String getEntityPackage() {
		return entityPackage;
	}

	public String getCriteriaForder() {
		return criteriaPackage;
	}

	public String getModelPackage() {
		return modelPackage;
	}

	public String getServiceImplPackage() {
		return serviceImplPackage;
	}

	public String getProviderFolder() {
		return providerFolder;
	}

	public String getApiFolder() {
		return apiFolder;
	}

	public String getExamplePackage() {
		return examplePackage;
	}

	public String getMapperPackage() {
		return mapperPackage;
	}

	public String getServiceInterfacePackage() {
		return serviceInterfacePackage;
	}

	public String getControllerPackage() {
		return controllerPackage;
	}

	public String getControllerFolder() {
		return controllerFolder;
	}

}
