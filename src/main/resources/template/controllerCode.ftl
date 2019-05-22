package ${controllerPackage};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.result.Result;
import com.example.result.ResultPage;
import com.example.result.ResultResponse;
import com.example.session.CustomerContext;
import com.example.session.CustomerSession;

import java.util.List;

<#list importClass as clazz>
import ${clazz};
</#list>



@Controller
@RequestMapping(value = "/${modelNameLowercase}")
public class ${controllerName} {
	private static final Logger logger = LoggerFactory.getLogger(${controllerName}.class);
	

	@Autowired
	private ${interfaceName} ${interfaceNameLowercase};

	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Long> save(@RequestBody ${modelName} ${modelNameLowercase}) {
		logger.info("insert ${moduleName} start:{}", ${modelNameLowercase});
		${modelNameLowercase}.setPerformerId(((CustomerSession) CustomerContext.getSessionUser()).getSysUser().getId());
		Result<Long> result = ${interfaceNameLowercase}.insert${moduleName}(${modelNameLowercase});
		logger.info("insert ${moduleName} end:{}", result);
		return result;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Result<Long> update(@RequestBody ${modelName} ${modelNameLowercase}) {
		logger.info("update ${moduleName} start:{}",${modelNameLowercase});
		${modelNameLowercase}.setPerformerId(((CustomerSession) CustomerContext.getSessionUser()).getSysUser().getId());
		ResultResponse resultResponse =  ${interfaceNameLowercase}.update${moduleName}ById(${modelNameLowercase});
		logger.info("update ${moduleName} resultResponse:{}", resultResponse);
		Result<Long> result = new Result<>();
		if (!resultResponse.isSuccess()) {
			result.setResultCode(resultResponse.resultCode());
		} else {
			result.setData(${modelNameLowercase}.get${primaryKeyName}());
		}
		logger.info("update ${moduleName} end:{}", result);
		return result;
	}
	
	
	
	@RequestMapping(value="", method = RequestMethod.GET)
    @ResponseBody
    public ResultPage<${modelName}> list (${criteriaName} ${criteriaNameLowercase}) {
		logger.info("list${moduleName} start:{}", ${criteriaNameLowercase});
		ResultPage<${modelName}> result = ${interfaceNameLowercase}.find${moduleName}ForPage(${criteriaNameLowercase});
		logger.info("list${moduleName} end:{}", result);
		return result;		
	}
	
	@RequestMapping(value="/{${primaryKeyNameLowercase}}", method = RequestMethod.GET)
    @ResponseBody
    public Result<${modelName}> detail (@PathVariable Long ${primaryKeyNameLowercase}) {
		logger.info("${moduleName} detail start:{}", ${primaryKeyNameLowercase});
		//取值
		Result<${modelName}> result = ${interfaceNameLowercase}.get${moduleName}(${primaryKeyNameLowercase});
		logger.info("${moduleName} detail end:{}", result);
		return result;		
	}
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResultResponse delete(@RequestBody List<Long> ${primaryKeyNameLowercase}s) {
		logger.info("delete ${moduleName} start:{}", ${primaryKeyNameLowercase}s.toString());

		${criteriaName} ${criteriaNameLowercase} = new ${criteriaName}();
		${criteriaNameLowercase}.set${primaryKeyName}s(${primaryKeyNameLowercase}s);
		ResultResponse resultResponse = ${interfaceNameLowercase}.delete${moduleName}ByCondition(${criteriaNameLowercase},
				((CustomerSession) CustomerContext.getSessionUser()).getSysUser().getId());
		logger.info("delete ${moduleName} end:{}", resultResponse);
		return resultResponse;
	}
}
