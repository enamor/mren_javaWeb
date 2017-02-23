/*
 * VersionControlMapper.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2016-11-29 Created
 */
package org.zn.user.dao;

import org.zn.user.entity.VersionControl;

public interface VersionControlDao {
    int deleteByPrimaryKey(Integer id);

    int insert(VersionControl record);

    int insertSelective(VersionControl record);

    VersionControl selectByPrimaryKey(Integer id);
    
    VersionControl selectByAppVersion(String appVersion);

    int updateByPrimaryKeySelective(VersionControl record);

    int updateByPrimaryKey(VersionControl record);
}