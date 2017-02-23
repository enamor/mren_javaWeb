/*
 * PhotoAlbumMapper.java
 * Copyright(C) 20xx-2015 xxxxxx公司
 * All rights reserved.
 * -----------------------------------------------
 * 2016-11-17 Created
 */
package org.zn.photos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zn.photos.entity.PhotoAlbum;

public interface PhotoAlbumDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PhotoAlbum record);

    int insertSelective(PhotoAlbum record);

    PhotoAlbum selectByPrimaryKey(Integer id);
    

    int updateByPrimaryKeySelective(PhotoAlbum record);

    int updateByPrimaryKey(PhotoAlbum record);
    
    List<PhotoAlbum> getLatestPhotoAlbum();
    
    /**
     * 分页查询
     * @param categoryId 分类
     * @param start   开始位置
     * @param rows	  行数
     * @return
     */
    List<PhotoAlbum> getPhotoAlbumList(
    		@Param("categoryId") String categoryId ,
    		@Param("start") Integer start ,
    		@Param("rows") Integer rows , 
    		@Param("state") Integer state);
}