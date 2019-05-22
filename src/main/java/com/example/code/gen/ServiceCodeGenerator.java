package com.example.code.gen;

import com.example.model.ClassModel;
import com.example.utils.Configure;
import com.example.utils.FileUtils;
import com.example.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class ServiceCodeGenerator {
	public static void main(String[] args) {
		Configure instance = Configure.getInstance("/code/sys_user.properties");

		List<ClassModel> classModels = instance.generatorClassModelAtt();
		for (ClassModel classModel : classModels) {
			// 生成criteria
			FileUtils.gen(classModel, "criteria.ftl",
					FileUtils.getFullPath(instance.getApiFolder(), classModel.getCriteriaFullName()));
			// 生成model
			FileUtils.gen(classModel, "model.ftl",
					FileUtils.getFullPath(instance.getApiFolder(), classModel.getModelFullName()));
			// 生成service接口
			FileUtils.gen(classModel, "interfaceCode.ftl",
					FileUtils.getFullPath(instance.getApiFolder(), classModel.getInterfaceFullName()));
			// 生成service实现类,先移除对controller层的引用
			List<String> serviceImportClass = new ArrayList<String>();
			for (String importClass : classModel.getImportClass()) {
				// controller不需要引用Entity、Mapper、Example与Impl
				if (StringUtil.hasSpecifiedString(importClass, "Controller")) {
					continue;
				}
				if (StringUtil.hasSpecifiedString(importClass, "ServiceImpl")) {
					continue;
				}
				serviceImportClass.add(importClass);
			}
			classModel.setImportClass(serviceImportClass);
			FileUtils.gen(classModel, "serviceCode.ftl",
					FileUtils.getFullPath(instance.getProviderFolder(), classModel.getServiceFullName()));

			// 生成controller实现类，移除对entity等的引用
			List<String> controllerImportClass = new ArrayList<String>();
			for (String importClass : classModel.getImportClass()) {
				// controller不需要引用Entity、Mapper、Example与Impl
				if (StringUtil.hasSpecifiedString(importClass, "Entity")
						|| StringUtil.hasSpecifiedString(importClass, "Mapper")
						|| StringUtil.hasSpecifiedString(importClass, "Example")
						|| StringUtil.hasSpecifiedString(importClass, "Impl")) {
					continue;
				}
				controllerImportClass.add(importClass);
			}
			classModel.setImportClass(controllerImportClass);
			FileUtils.gen(classModel, "controllerCode.ftl",
					FileUtils.getFullPath(instance.getControllerFolder(), classModel.getControllerFullName()));

		}
	}
}
