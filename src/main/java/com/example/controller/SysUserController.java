package com.example.controller;

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

import com.example.criteria.SysUserCriteria;
import com.example.model.SysUser;
import com.example.service.SysUserService;



@Controller
@RequestMapping(value = "/sysUser")
public class SysUserController {
	private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);
	

	@Autowired
	private SysUserService sysUserService;

	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public Result<Long> save(@RequestBody SysUser sysUser) {
		logger.info("insert SysUser start:{}", sysUser);
		sysUser.setPerformerId(((CustomerSession) CustomerContext.getSessionUser()).getSysUser().getId());
		Result<Long> result = sysUserService.insertSysUser(sysUser);
		logger.info("insert SysUser end:{}", result);
		return result;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Result<Long> update(@RequestBody SysUser sysUser) {
		logger.info("update SysUser start:{}",sysUser);
		sysUser.setPerformerId(((CustomerSession) CustomerContext.getSessionUser()).getSysUser().getId());
		ResultResponse resultResponse =  sysUserService.updateSysUserById(sysUser);
		logger.info("update SysUser resultResponse:{}", resultResponse);
		Result<Long> result = new Result<>();
		if (!resultResponse.isSuccess()) {
			result.setResultCode(resultResponse.resultCode());
		} else {
			result.setData(sysUser.getId());
		}
		logger.info("update SysUser end:{}", result);
		return result;
	}
	
	
	
	@RequestMapping(value="", method = RequestMethod.GET)
    @ResponseBody
    public ResultPage<SysUser> list (SysUserCriteria sysUserCriteria) {
		logger.info("listSysUser start:{}", sysUserCriteria);
		ResultPage<SysUser> result = sysUserService.findSysUserForPage(sysUserCriteria);
		logger.info("listSysUser end:{}", result);
		return result;		
	}

	@RequestMapping(value="/list", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<SysUser>> list1 (SysUserCriteria sysUserCriteria) {
		logger.info("listSysUser start:{}", sysUserCriteria);
		Result<List<SysUser>> result = sysUserService.findSysUserList(sysUserCriteria);
		logger.info("listSysUser end:{}", result);
		return result;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result<SysUser> detail (@PathVariable Long id) {
		logger.info("SysUser detail start:{}", id);
		//取值
		Result<SysUser> result = sysUserService.getSysUser(id);
		logger.info("SysUser detail end:{}", result);
		return result;		
	}
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResultResponse delete(@RequestBody List<Long> ids) {
		logger.info("delete SysUser start:{}", ids.toString());

		SysUserCriteria sysUserCriteria = new SysUserCriteria();
		sysUserCriteria.setIds(ids);
		ResultResponse resultResponse = sysUserService.deleteSysUserByCondition(sysUserCriteria,
				((CustomerSession) CustomerContext.getSessionUser()).getSysUser().getId());
		logger.info("delete SysUser end:{}", resultResponse);
		return resultResponse;
	}
}
