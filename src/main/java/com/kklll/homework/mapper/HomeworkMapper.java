package com.kklll.homework.mapper;

import com.kklll.homework.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author: DeepBlue
 * @Date: 2020/4/21 16:05
 * @Description:
 */
@Mapper
public interface HomeworkMapper {
    @Select("select * from functions")
    List<String> selectHomework();

    @Select("select * from qiban where name=#{name} and xuehao=#{xuehao}")
    List<Student> check(String name, String xuehao);

    @Update("update qiban  set ${kind}=1 where name=#{name}")
    void update(String kind,String name);
}
