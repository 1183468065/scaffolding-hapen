package com.example.service.impl;

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


import gen.example.entity.SysUserEntity;
import com.example.criteria.SysUserCriteria;
import com.example.model.SysUser;
import gen.example.mapper.SysUserEntityMapper;
import gen.example.entity.SysUserEntityExample;
import com.example.service.SysUserService;
import gen.example.entity.SysUserEntityExample.Criteria;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
	private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

	@Autowired
	private SysUserEntityMapper sysUserEntityMapper;

	@Override
	public Result<Long> insertSysUser(SysUser sysUser){
		logger.debug("insertSysUser start sysUser:{}", sysUser);
		if (!this.checkNeccesaryAttribute(sysUser)) {
			return ResultSupport.paramError();
		}
		Result<Long> result = new Result<Long>();
		SysUserEntity sysUserEntity = BeanUtils.copyProperties(sysUser, SysUserEntity.class);
		sysUserEntity.setId(Long.parseLong(StringUtil.randomNumber(12)));
		EntityHelper.setCreateStatusFields(sysUserEntity, Status.NORMAL, sysUser.performerId());
		this.sysUserEntityMapper.insertSelective(sysUserEntity);
		result.setData(sysUserEntity.getId());
		logger.debug("insertSysUser end : result({})", result);
		return result;
	}

	@Override
	public ResultResponse insertSysUserBatch(List<SysUser> sysUserList){
		logger.debug("insertSysUserBatch start : ({})",sysUserList);
		if (!CollectionUtils.isEmpty(sysUserList)) {
			for (SysUser sysUser : sysUserList) {
				this.insertSysUser(sysUser);
			}
		}
		logger.debug("insertSysUserBatch end ");
		return ResultResponse.ok();
	}

	@Override
	public ResultResponse deleteSysUserById(Long id, Long performerId){
		logger.debug("deleteSysUserById start{}", id);
		// 校验传入参数
		if (CommonUtils.valueOf(id) < 1L || CommonUtils.valueOf(performerId) < 1L) {
			return ResultSupport.paramError();
		}
		// 确认修改字段
		SysUserEntity sysUserEntity = new SysUserEntity();
		sysUserEntity.setId(id);
		EntityHelper.setUpdateStatusFields(sysUserEntity, Status.DELETED, performerId);
		Integer resultCode = sysUserEntityMapper.updateByPrimaryKeySelective(sysUserEntity);
		logger.debug("deleteSysUserById end:{}", resultCode);
		return ResultResponse.ok();
	}

	@Override
	public ResultResponse deleteSysUserByCondition(SysUserCriteria sysUserCriteria, Long performerId) {
		logger.debug("deleteSysUserByCondition start : ({})",sysUserCriteria);
		SysUserEntity sysUserEntity = new SysUserEntity();
		EntityHelper.setUpdateStatusFields(sysUserEntity, Status.DELETED, performerId);
		SysUserEntityExample example = this.createExample(sysUserCriteria);
		sysUserEntityMapper.updateByExampleSelective(sysUserEntity, example);
		logger.debug("deleteSysUserByCondition end");
		return ResultResponse.ok();
	}

	@Override
	public ResultResponse updateSysUserById(SysUser sysUser) {
		logger.debug("updateSysUserById start:{}", sysUser);
		if (null == sysUser || CommonUtils.valueOf(sysUser.getId()) < 1L) {
			return ResultSupport.paramError();
		}
		SysUserEntity sysUserEntity = BeanUtils.copyProperties(sysUser, SysUserEntity.class);
		EntityHelper.setUpdateFields(sysUserEntity, sysUser.performerId());
		Integer resultCode = this.sysUserEntityMapper.updateByPrimaryKeySelective(sysUserEntity);
		logger.debug("updateSysUserById end:{}", resultCode);
		return ResultResponse.ok();
	}

	@Override
	public Result<SysUser> getSysUser(Long id) {
		logger.debug("getSysUser start : id({})", id);
		SysUser sysUser = null;
		if(CommonUtils.valueOf(id) > 0L){
			SysUserEntity sysUserEntity = sysUserEntityMapper.selectByPrimaryKey(id);
			if (null != sysUserEntity && sysUserEntity.getStatus().equals(Status.NORMAL)) {
				sysUser = BeanUtils.copyProperties(sysUserEntity, SysUser.class);
				this.buildSysUserDetail(sysUser);
			}
		}
		logger.debug("getSysUser end");
		return ResultSupport.ok(sysUser);
	}

	@Override
	public ResultPage<SysUser> findSysUserForPage(SysUserCriteria sysUserCriteria) {
		logger.debug("findSysUserForPage start : ({})",sysUserCriteria);
		ResultPage<SysUser> resultPage = new ResultPage<SysUser>();
		SysUserEntityExample example = this.createExample(sysUserCriteria);
		PageInfo pageInfo = sysUserCriteria.toPageInfo();
		List<SysUserEntity> sysUserEntityList = this.sysUserEntityMapper.selectByExampleWithRowbounds(example, pageInfo);
		List<SysUser> sysUserList = BeanUtils.copyListProperties(sysUserEntityList, SysUser.class);
		for(SysUser sysUser : sysUserList){
			this.buildSysUserDetail(sysUser);
		}
		resultPage.setPageInfo(pageInfo);
		resultPage.setData(sysUserList);
		logger.debug("findSysUserForPage end : result ({})",resultPage);
		return resultPage;
	}

	@Override
	public Result<List<SysUser>> findSysUserList(SysUserCriteria sysUserCriteria) {
		logger.debug("findSysUserList start : ({})",sysUserCriteria);
		Result<List<SysUser>> result = new Result<List<SysUser>>();
		SysUserEntityExample example = this.createExample(sysUserCriteria);
		List<SysUserEntity> sysUserEntityList = sysUserEntityMapper.selectByExample(example);
		List<SysUser> sysUserList = BeanUtils.copyListProperties(sysUserEntityList, SysUser.class);
		for(SysUser sysUser : sysUserList){
			this.buildSysUserDetail(sysUser);
		}
		result.setData(sysUserList);
		logger.debug("findSysUserList end result : ({})",result);
		return result;
	}

	@Override
	public Result<Long> countSysUserByCondition(SysUserCriteria sysUserCriteria) {
		logger.debug("countSysUserByCondition start : ({})",sysUserCriteria);
		Result<Long> result = new Result<Long>();
		SysUserEntityExample example = this.createExample(sysUserCriteria);
		long count = sysUserEntityMapper.countByExample(example);
		result.setData(count);
		logger.debug("countSysUserByCondition end result : ({})",result);
		return result;
	}

	/**
	 * 检查非空属性
	 *
	 * @param sysUser
	 */
	private Boolean checkNeccesaryAttribute(SysUser sysUser) {
		if (null == sysUser) {
			return false;
		}
		//TODO:添加验证方法
		return true;
	}

	/**
	 * 查询条件
	 * @param sysUserCriteria
	 * @return
	 */
	private SysUserEntityExample createExample(SysUserCriteria sysUserCriteria){
		SysUserEntityExample example = new SysUserEntityExample();
		Criteria criteria = example.createCriteria().andStatusEqualTo(Status.NORMAL);
		//TODO:添加创建条件方法
		if (StringUtil.isNotEmptyOrBlank(sysUserCriteria.getPhone())) {
			criteria.andPhoneEqualTo(sysUserCriteria.getPhone());
		}
		return example;
	}

	/**
	 * 根据传入的sysUser,添加其他关联信息
	 * @param sysUser
 	 * @param boolean，确定是否添加该项附属信息
	 * @return
	 */
	private  void buildSysUserDetail (SysUser sysUser/**,Boolean withExtendsInformation*/) {
		//TODO:根据具体情况进行分析，便于灵活的进行附属条件的添加
		/**
		if(withExtendsInformation){
			this.withExtendsInformation(sysUser);
		}
		*/
	};


}
