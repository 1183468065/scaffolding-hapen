package ${servicePackage};

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.enums.Status;
import com.example.pagination.PageInfo;
import com.example.result.Result;
import com.example.result.ResultPage;
import com.example.result.ResultResponse;
import com.example.result.ResultSupport;
import com.example.utils.CommonUtils;
import com.example.utils.EntityHelper;
import com.example.utils.StringUtil;
import com.example.utils.BeanUtils;


<#list importClass as clazz>
import ${clazz};
</#list>
import ${exampleFullName}.Criteria;
import org.springframework.stereotype.Service;

@Service
public class ${serviceName} implements ${interfaceName} {
	private static final Logger logger = LoggerFactory.getLogger(${serviceName}.class);

	@Autowired 
	private ${mapperName} ${mapperNameLowercase};

	@Override
	public Result<Long> insert${moduleName}(${modelName} ${modelNameLowercase}){
		logger.debug("insert${moduleName} start ${modelNameLowercase}:{}", ${modelNameLowercase});
		if (!this.checkNeccesaryAttribute(${modelNameLowercase})) {
			return ResultSupport.paramError();
		}
		Result<Long> result = new Result<Long>();
		${entityName} ${entityNameLowercase} = BeanUtils.copyProperties(${modelNameLowercase}, ${entityName}.class);
		${entityNameLowercase}.set${primaryKeyName}(Long.parseLong(StringUtil.randomNumber(12)));
		EntityHelper.setCreateStatusFields(${entityNameLowercase}, Status.NORMAL, ${modelNameLowercase}.performerId());
		this.${mapperNameLowercase}.insertSelective(${entityNameLowercase});
		result.setData(${entityNameLowercase}.get${primaryKeyName}());
		logger.debug("insert${moduleName} end : result({})", result);
		return result;
	}
	
	@Override
	public ResultResponse insert${moduleName}Batch(List<${modelName}> ${modelNameLowercase}List){
		logger.debug("insert${moduleName}Batch start : ({})",${modelNameLowercase}List);
		if (!CollectionUtils.isEmpty(${modelNameLowercase}List)) {
			for (${modelName} ${modelNameLowercase} : ${modelNameLowercase}List) {
				this.insert${moduleName}(${modelNameLowercase});
			}
		}
		logger.debug("insert${moduleName}Batch end ");
		return ResultResponse.ok();
	}
	
	@Override
	public ResultResponse delete${moduleName}ById(Long ${primaryKeyNameLowercase}, Long performerId){
		logger.debug("delete${moduleName}ById start{}", ${primaryKeyNameLowercase});
		// 校验传入参数
		if (CommonUtils.valueOf(${primaryKeyNameLowercase}) < 1L || CommonUtils.valueOf(performerId) < 1L) {
			return ResultSupport.paramError();
		}
		// 确认修改字段
		${entityName} ${entityNameLowercase} = new ${entityName}();
		${entityNameLowercase}.set${primaryKeyName}(${primaryKeyNameLowercase});
		EntityHelper.setUpdateStatusFields(${entityNameLowercase}, Status.DELETED, performerId);
		Integer resultCode = ${mapperNameLowercase}.updateByPrimaryKeySelective(${entityNameLowercase});
		logger.debug("delete${moduleName}ById end:{}", resultCode);
		return ResultResponse.ok();
	}

	@Override
	public ResultResponse delete${moduleName}ByCondition(${criteriaName} ${criteriaNameLowercase}, Long performerId) {
		logger.debug("delete${moduleName}ByCondition start : ({})",${criteriaNameLowercase});
		${entityName} ${entityNameLowercase} = new ${entityName}();
		EntityHelper.setUpdateStatusFields(${entityNameLowercase}, Status.DELETED, performerId);
		${exampleName} example = this.createExample(${criteriaNameLowercase});
		${mapperNameLowercase}.updateByExampleSelective(${entityNameLowercase}, example);
		logger.debug("delete${moduleName}ByCondition end");
		return ResultResponse.ok();
	}

	@Override
	public ResultResponse update${moduleName}ById(${modelName} ${modelNameLowercase}) {
		logger.debug("update${moduleName}ById start:{}", ${modelNameLowercase});
		if (null == ${modelNameLowercase} || CommonUtils.valueOf(${modelNameLowercase}.get${primaryKeyName}()) < 1L) {
			return ResultSupport.paramError();
		}
		${entityName} ${entityNameLowercase} = BeanUtils.copyProperties(${modelNameLowercase}, ${entityName}.class);
		EntityHelper.setUpdateFields(${entityNameLowercase}, ${modelNameLowercase}.performerId());
		Integer resultCode = this.${mapperNameLowercase}.updateByPrimaryKeySelective(${entityNameLowercase});
		logger.debug("update${moduleName}ById end:{}", resultCode);
		return ResultResponse.ok();
	}
	
	@Override
	public Result<${modelName}> get${moduleName}(Long ${primaryKeyNameLowercase}) {
		logger.debug("get${moduleName} start : ${primaryKeyNameLowercase}({})", ${primaryKeyNameLowercase});
		${modelName} ${modelNameLowercase} = null;
		if(CommonUtils.valueOf(${primaryKeyNameLowercase}) > 0L){
			${entityName} ${entityNameLowercase} = ${mapperNameLowercase}.selectByPrimaryKey(${primaryKeyNameLowercase});
			if (null != ${entityNameLowercase} && ${entityNameLowercase}.getStatus().equals(Status.NORMAL)) {
				${modelNameLowercase} = BeanUtils.copyProperties(${entityNameLowercase}, ${modelName}.class);
				this.build${moduleName}Detail(${modelNameLowercase});
			}
		}
		logger.debug("get${moduleName} end");
		return ResultSupport.ok(${modelNameLowercase});
	}

	@Override
	public ResultPage<${modelName}> find${moduleName}ForPage(${criteriaName} ${criteriaNameLowercase}) {
		logger.debug("find${moduleName}ForPage start : ({})",${criteriaNameLowercase});
		ResultPage<${modelName}> resultPage = new ResultPage<${modelName}>();
		${exampleName} example = this.createExample(${criteriaNameLowercase});
		PageInfo pageInfo = ${criteriaNameLowercase}.toPageInfo();
		List<${entityName}> ${entityNameLowercase}List = this.${mapperNameLowercase}.selectByExampleWithRowbounds(example, pageInfo);
		List<${modelName}> ${modelNameLowercase}List = BeanUtils.copyListProperties(${entityNameLowercase}List, ${modelName}.class);
		for(${modelName} ${modelNameLowercase} : ${modelNameLowercase}List){
			this.build${moduleName}Detail(${modelNameLowercase});
		}
		resultPage.setPageInfo(pageInfo);
		resultPage.setData(${modelNameLowercase}List);
		logger.debug("find${moduleName}ForPage end : result ({})",resultPage);
		return resultPage;
	}

	@Override
	public Result<List<${modelName}>> find${moduleName}List(${criteriaName} ${criteriaNameLowercase}) {
		logger.debug("find${moduleName}List start : ({})",${criteriaNameLowercase});
		Result<List<${modelName}>> result = new Result<List<${modelName}>>();
		${exampleName} example = this.createExample(${criteriaNameLowercase});
		List<${entityName}> ${entityNameLowercase}List = ${mapperNameLowercase}.selectByExample(example);
		List<${modelName}> ${modelNameLowercase}List = BeanUtils.copyListProperties(${entityNameLowercase}List, ${modelName}.class);
		for(${modelName} ${modelNameLowercase} : ${modelNameLowercase}List){
			this.build${moduleName}Detail(${modelNameLowercase});
		}
		result.setData(${modelNameLowercase}List);
		logger.debug("find${moduleName}List end result : ({})",result);
		return result;
	}

	@Override
	public Result<Long> count${moduleName}ByCondition(${criteriaName} ${criteriaNameLowercase}) {
		logger.debug("count${moduleName}ByCondition start : ({})",${criteriaNameLowercase});
		Result<Long> result = new Result<Long>();
		${exampleName} example = this.createExample(${criteriaNameLowercase});
		long count = ${mapperNameLowercase}.countByExample(example);
		result.setData(count);
		logger.debug("count${moduleName}ByCondition end result : ({})",result);
		return result;
	}

	/**
	 * 检查非空属性
	 * 
	 * @param ${modelNameLowercase}
	 */
	private Boolean checkNeccesaryAttribute(${modelName} ${modelNameLowercase}) {
		if (null == ${modelNameLowercase}) {
			return false;
		}
		//TODO:添加验证方法
		return true;
	}
	
	/**
	 * 查询条件
	 * @param ${criteriaNameLowercase}
	 * @return
	 */
	private ${exampleName} createExample(${criteriaName} ${criteriaNameLowercase}){
		${exampleName} example = new ${exampleName}();
		Criteria criteria = example.createCriteria().andStatusEqualTo(Status.NORMAL);
		//TODO:添加创建条件方法
		return example;
	}
	
	/**
	 * 根据传入的${modelNameLowercase},添加其他关联信息
	 * @param ${modelNameLowercase}
 	 * @param boolean，确定是否添加该项附属信息
	 * @return
	 */
	private  void build${moduleName}Detail (${modelName} ${modelNameLowercase}/**,Boolean withExtendsInformation*/) {
		//TODO:根据具体情况进行分析，便于灵活的进行附属条件的添加
		/**
		if(withExtendsInformation){
			this.withExtendsInformation(${modelNameLowercase});
		}
		*/
	};
	
	
}
