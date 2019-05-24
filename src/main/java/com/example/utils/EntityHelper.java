package com.example.utils;

import com.example.enums.Status;
import com.example.model.BaseEntity;


public class EntityHelper {
    /**
     * Set the status and createXXX field
     * @param entity
     * @param status
     * @return the entity
     */
    public static <T extends BaseEntity> T setCreateStatusFields(T entity, Status status, Long userId) {
        entity.setStatus(status);
        return setCreateFields(entity, userId);
    } 
    public static <T extends BaseEntity> T setCreateFields(T entity, Long userId) {
        setUpdateFields(entity, userId);
		entity.setStatus(Status.NORMAL);
        entity.setCreateBy(userId == null ? 0 : userId);
        entity.setCreateDate(DateUtil.getNowTime());
        return entity;
    } 
    /**
     * Set the status and updateXXX filed
     * @param entity
     * @param status
     * @return the entity
     */
    public static <T extends BaseEntity> T setUpdateStatusFields(T entity, Status status, Long userId) {
        entity.setStatus(status);
        return setUpdateFields(entity, userId);
    } 
    public static <T extends BaseEntity> T setUpdateFields(T entity, Long userId) {
    	// 更新时不更新创建信息
    	entity.setCreateBy(null);
    	entity.setCreateDate(null);
        entity.setLastModifiedBy(userId == null ? 0 : userId);
        entity.setLastModifiedDate(DateUtil.getNowTime());
        return entity;
    } 
}
