package com.kklll.homework.controller;

import com.kklll.homework.mapper.HomeworkMapper;
import com.kklll.homework.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author: DeepBlue
 * @Date: 2020/4/21 16:05
 * @Description:
 */
@RestController
public class MainController {
    @Autowired
    private HomeworkMapper homeworkMapper;

    @PostMapping("list")
    public List<String> getHomework() {
        return homeworkMapper.selectHomework();
    }

    @PostMapping("send")
    public String send(MultipartFile file, String name, String xuehao, String homework) {
        if (file == null || xuehao == null ||
                homework == null || name.isEmpty()
                || xuehao.isEmpty() || homework.isEmpty()) {
            return "您未全部填写字段,请检查！";
        }
        List<Student> check = homeworkMapper.check(name, xuehao);
        if (check.size() == 0) {
            return "学号姓名无法匹配,请重试。";
        } else {
            //upload
            try {
                File path = new File("");
                File f = new File(path.getAbsolutePath() + File.separatorChar + homework);
                if (!f.exists()) {
                    f.mkdir();
                }
                file.transferTo(new File(path.getAbsolutePath() + File.separatorChar + homework + File.separatorChar + xuehao + '-' + name + "."+file.getOriginalFilename().split("\\.")[1]));
                homeworkMapper.update(homework, name);
            } catch (IOException e) {
                return "上传失败,请重试!";
            }
            return "上传成功!";
        }
    }

}
