package com.example.service;

import java.util.List;

import com.example.result.Result;
import com.example.result.ResultPage;
import com.example.result.ResultResponse;
import com.example.criteria.SysUserCriteria;
import com.example.model.SysUser;

public interface SysUserService {
	/**
	 * 插入一条信息
	 * 
	 * @param sysUser
	 * @return
	 */
	Result<Long> insertSysUser(SysUser sysUser);
	
	/**
	 * 批量插入信息
	 * @param sysUser
	 * @return
	 */
	ResultResponse insertSysUserBatch(List<SysUser> sysUserList);

	/**
	 * 根据主键删除，根据输入sysUserId去删除，performerId为当前登录用户的Id
	 * 
	 * @param sysUserId
	 * @param performerId
	 * @return
	 */
	ResultResponse deleteSysUserById(Long sysUserId, Long performerId);

	/**
	 * 根据输入的条件批量删除，performerId为当前登录用户的Id
	 * @param sysUserCriteria
	 * @param performerId
	 * @return
	 */
	ResultResponse deleteSysUserByCondition(SysUserCriteria sysUserCriteria,Long performerId);

	/**
	 * 根据输入的sysUser去更改SysUser信息
	 * 
	 * @param sysUser
	 * @return
	 */
	ResultResponse updateSysUserById(SysUser sysUser);
	
	/**
	 * 根据sysUserId得到一条信息
	 * 
	 * @param sysUserId
	 * @return
	 */
	Result<SysUser> getSysUser(Long sysUserId);
	
	/**
	 * 根据输入的SysUserCriteria条件分页查询
	 * @param sysUserCriteria
	 * @return
	 */
	ResultPage<SysUser> findSysUserForPage(SysUserCriteria sysUserCriteria);
	
	/**
	 * 根据输入的SysUserCriteria条件查询
	 * @param sysUserCriteria
	 * @return
	 */
	Result<List<SysUser>> findSysUserList(SysUserCriteria sysUserCriteria);
	
	/**
	 * 根据输入的SysUserCriteria条件统计信息的总数 
	 * @param sysUserCriteria
	 * @return
	 */
	public Result<Long> countSysUserByCondition(SysUserCriteria sysUserCriteria);
}
