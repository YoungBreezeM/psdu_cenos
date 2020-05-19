package com.fw.mapper;


import com.fw.domain.Entry;
import com.fw.domain.Judges;
import com.fw.domain.UploadFile;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 * @date 2020 下午4:08
 */
@Repository
@Mapper
public interface FileMapper {

    /**
     * 查找所有文件
     * @return list
     * */
    @Select("select * from upload_file")
    List<UploadFile> findAll();



    /**
     * 通过 file id
     * @param id file id
     * @return upload record
     * */
    @Select("select * from upload_file where id =#{id}")
    UploadFile findUploadFileById(@Param("id") Integer id);

    /**
     * 通过fileName 查找 file
     * @param uploadFile
     * @return file record
     * */
    @Select("select * from upload_file where fileName =#{fileName}")
    UploadFile findUploadFileByFileName(UploadFile uploadFile);

    /**
     * find all file by entryId
     * @param entry entity
     * @return list
     * */
    @Select("select * from upload_file where entry_id = #{id}")
    @Results(id = "fileMap",value = {
        @Result(column = "entry_id",property = "entryId")
    })
    List<UploadFile> findAllByEntryId(Entry entry);

    /**
     * 添加文件
     * @param uploadFile
     * */
    @Insert("insert into upload_file (fileName,fileDownloadUrl,fileAccessUrl,size,fileType) values(#{fileName},#{fileDownloadUrl},#{fileAccessUrl},#{size},#{fileType})")
    void addFile(UploadFile uploadFile);

    /**
     * 删除文件
     * @param id file id
     * */
    @Delete("delete from upload_file where id =#{id}")
    void deleteFile(@Param("id") Integer id);

    /**
     * update entry by id
     * @param file entity
     * */
    @Update("update upload_file set entry_id =#{entryId} where id =#{id}")
    void updateEntryIdById(UploadFile file);
}
