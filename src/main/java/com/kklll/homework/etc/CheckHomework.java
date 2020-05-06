package com.kklll.homework.etc;


import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author DeepBlue
 * @date 2020/3/18 19:45
 */
public class CheckHomework {
    private static Pattern NAME_PATTERN = Pattern.compile("[\u4e00-\u9fa5]+");

    public static ArrayList<String> getFilesName(File dir) {
        ArrayList<String> filenames = new ArrayList<>();
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                filenames.add(files[i].getName());
            }
        } else {
            throw new RuntimeException("不是路径");
        }
        return filenames;
    }

    public static ArrayList<String> getSqlNames() throws Exception {
        ArrayList<String> names = new ArrayList<>();
        Connection connect = DButil.getConnect();
        PreparedStatement statement = connect.prepareStatement("select  sname from student where sclass='17070143'");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            names.add(resultSet.getString("sname"));
        }
        return names;
    }

    public static ArrayList<String> check(ArrayList<String> files, ArrayList<String> names) {
        Iterator<String> iterator = files.iterator();
        while (iterator.hasNext()) {
            Matcher matcher = NAME_PATTERN.matcher(iterator.next());
            if (matcher.find()) {
                System.out.print(matcher.group()+",");
                if (names.contains(matcher.group())) {
                    names.remove(matcher.group());
                }
            }
        }
        System.out.println();
        return names;
    }

    public static void main(String[] args) throws Exception {
//        File dir = new File("E:\\文档\\网络安全\\RSA");
        File dir = new File("E:\\oneDriver\\OneDrive - st.nuc.edu.cn\\文档\\网络安全\\论文");
        ArrayList<String> filesNames = getFilesName(dir);
        System.out.println(filesNames);
        System.out.println("文件总个数:"+filesNames.size());
        ArrayList<String> sqlNames = getSqlNames();
        System.out.println(sqlNames);
        System.out.println("总人数:"+sqlNames.size());
        ArrayList<String> check = check(filesNames, sqlNames);
        System.out.println("\n未交作业:\n"+check);
        System.out.println(check.size());

//        String name="1607064120孙城虎.doc";
//        Matcher matcher = NAME_PATTERN.matcher(name);
//        boolean b = matcher.find();
//        System.out.println(b);
//        System.out.println(matcher.group());
//        abdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerfabdasdwerf
    }
}
