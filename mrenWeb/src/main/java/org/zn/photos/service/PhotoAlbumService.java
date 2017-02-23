package org.zn.photos.service;

import java.util.List;

import org.zn.photos.entity.PhotoAlbum;

public interface PhotoAlbumService {
	int deleteByPrimaryKey(Integer id);

    int insert(PhotoAlbum record);

    int insertSelective(PhotoAlbum record);

    PhotoAlbum selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(PhotoAlbum record);

    int updateByPrimaryKey(PhotoAlbum record);
    
    /*
     * 获取最新创建的相册
     */
    List<PhotoAlbum> getLatestPhotoAlbum();
    
    List<PhotoAlbum> getPhotoAlbumList(String categoryId ,Integer start ,Integer rows , Integer state);
    
    
    PhotoAlbum createPhotoAlbum(String imageUrl,String title,String source ,Integer state ,String style);
}
