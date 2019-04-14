package org.jboss.as.quickstarts.ejb.remote.stateless;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;

public class RandomUtil {
    private static String baseChar = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String numberChar = "0123456789";
    private static final String[] emailChar = "@outlook.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@163.com,@126.net,@gmail.com,@sina.com,@sohu.com".split(",");
    private static final String[] firstnameChar = "Yuze,Yuchen,Yuxuan,Zimo,Yinuo,Zeyu,Zirui,Haoyu,Xingchen,Yuzhe,Yichen,Zichen,Rui,Xuan,Xinran,Xinyi,Yuhan,Kexin,Liyao,Tianyu,Shiqi,Youran,Yuxin,Yue,Yutong,Ranran,Xiaoyi,Yi,Dawei,Xiaofeng,Qiang,Shuxia,Yun,Xiu,Juan,Ying,Hua,Hui,Jun,Bao,Liang,Lei,Haishan,Xiaoming,Xiaotong,Lin,Xiaohua,Haoran".split(",");
    private static final String[] lastnameChar = "Gao,Cao,Wu,Gu,Bao,Cheng,Ai,Mao,Deng,Jiang,Lu,Hua,Quan,Yu,Gu,Meng,Ping,Ying,Pang,Yu,Liu,Feng,Zhuge,Xiahou,Zhenghong,Ma,Wang,Niu,Lin,Zhao,Qian,Sun,Li,Zhou,Wu,Zheng,Feng,Chen,Zhu,Gui,Jiang,Shen,Han,Yang,Qin,Xu,Lv".split(",");
    private static final String[] genderChar = "male,female".split(",");
    private static final String[] birthyearChar = "1997,1998".split(",");
    private static final String[] birthmonthChar = "01,02,03,04,05,06,07,08,09,10,11,12".split(",");
    private static final String[] birthdateChar = "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28".split(",");
    private static String[] phoneFirstChar = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    private static final String[] companyChar = "alibaba,meituan,tencent,xdf,douyin,adobe,microsoft,google,apple,baidu".split(",");
    private static final String[] jobChar = "developer,manager,officer,anaylst,designer,trader,editor,engineer".split(",");
    private static final String[] locationChar = "xiamen,beijing,shanghai,guangzhou,hongkong,hangzhou,qingdao,fuzhou,shijiazhuang,taiyuan,zhengzhou,nanjing,nanchang".split(",");
    private static final String[] entranceChar = "2015,2016,2017".split(",");
    private static final String[] graduationChar = "2018,2019,2011".split(",");

    private String getString(String base, int length) { //length表示生成字符串的长度
        Random random = new Random();
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            strBuffer.append(base.charAt(number));
        }
        return strBuffer.toString();
    }
    private static String getPhone() {
        int length = 8;
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(phoneFirstChar[(int) (Math.random() * phoneFirstChar.length)]);
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * numberChar.length());
            strBuffer.append(numberChar.charAt(number));
        }
        return strBuffer.toString();
    }
    private static String getEmail() {
        int length = (int) (Math.random() * 5) + 3;
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * baseChar.length());
            strBuffer.append(baseChar.charAt(number));
        }
        strBuffer.append(emailChar[(int) (Math.random() * emailChar.length)]);
        return strBuffer.toString();
    }
    private static String getWechat() {
        int length = (int) (Math.random() * 5) + 8;
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * baseChar.length());
            strBuffer.append(baseChar.charAt(number));
        }
        return strBuffer.toString();
    }
    private static String getFirstname() {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(firstnameChar[(int) (Math.random() * firstnameChar.length)]);
        return strBuffer.toString();
    }
    private static String getLastname() {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(lastnameChar[(int) (Math.random() * lastnameChar.length)]);
        return strBuffer.toString();
    }
    private static String getBirthyear() {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(birthyearChar[(int) (Math.random() * birthyearChar.length)]);
        return strBuffer.toString();
    }
    private static String getBirthmonth() {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(birthmonthChar[(int) (Math.random() * birthmonthChar.length)]);
        return strBuffer.toString();
    }
    private static String getBirthdate() {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(birthdateChar[(int) (Math.random() * birthdateChar.length)]);
        return strBuffer.toString();
    }
    private static String getGender() {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(genderChar[(int) (Math.random() * genderChar.length)]);
        return strBuffer.toString();
    }
    private static String getCompany() {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(companyChar[(int) (Math.random() * companyChar.length)]);
        return strBuffer.toString();
    }
    private static String getJob() {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(jobChar[(int) (Math.random() * jobChar.length)]);
        return strBuffer.toString();
    }
    private static String getLocation() {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(locationChar[(int) (Math.random() * locationChar.length)]);
        return strBuffer.toString();
    }
    private static String getEntrance() {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(entranceChar[(int) (Math.random() * entranceChar.length)]);
        return strBuffer.toString();
    }
    private static String getGraduation() {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(graduationChar[(int) (Math.random() * graduationChar.length)]);
        return strBuffer.toString();
    }

    private List<String> getNameList(int size) { //size表示生成字符串的个数
        String str = "";
        Set<String> set1 = new HashSet<String>();
        while (set1.size() < size) {
            str = getLastname() + getFirstname();
            set1.add(str);
        }
        List list = new ArrayList();
        for (String s : set1) {
            list.add(s);
        }
        return list;
    }
    private List<String> getIdList(int size) { //size表示生成字符串的个数
        String str = "";
        Set<String> set1 = new HashSet<String>();
        while (set1.size() < size) {
            str = getString(letterChar, 5);
            set1.add(str);
        }
        List list = new ArrayList();
        for (String s : set1) {
            list.add(s);
        }
        return list;
    }

    public void initRandomAlumni(int size) { //size表示生成字符串的个数
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:jboss/datasources/ExampleDS");
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            //生成校友信息
            statement.executeUpdate("create table alumni(name char (20) not null, gender char (10), birthday char (20), " +
                    "entrance int (4), graduation int (4), location char (20), company char (20), job char (20)," +
                    "phone char(11) not null, email char (20), wechat char (20));");
            List<String> listId = new ArrayList();
            listId = getNameList(size);
            FileWriter fw = new FileWriter("./alumni.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            String name = "";
            String gender = "";
            String birthday = "";
            String entrance = "";
            String graduation = "";
            String location = "";
            String company = "";
            String job = "";
            String phone = "";
            String email = "";
            String wechat = "";
            bw.write("name,gender,birthday,entrance,graduation,location,company,job,phone,email,wechat,\n");
            for (String s : listId) {
                name = s;
                gender = getGender();
                email = getEmail();
                wechat = getWechat();
                birthday = getBirthyear() + getBirthmonth() + getBirthdate();
                phone = getPhone();
                company = getCompany();
                job = getJob();
                location = getLocation();
                entrance = getEntrance();
                graduation = getGraduation();
                bw.write("" + name);
                bw.write("," + gender);
                bw.write("," + birthday);
                bw.write("," + entrance);
                bw.write("," + graduation);
                bw.write("," + location);
                bw.write("," + company);
                bw.write("," + job);
                bw.write("," + phone);
                bw.write("," + email);
                bw.write("," + wechat + ",\n");
                statement.executeUpdate("insert into alumni(name,gender,birthday,entrance,graduation,location,company,job,phone,email,wechat)" +
                        "values('" + name + "','" + gender + "','" + birthday + "','" + entrance + "','" + graduation + "','" + location + "','" + company + "','" + job + "','" + phone + "','" + email + "','" + wechat + "')");
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void initRandomAdmin(int size) { //size表示生成字符串的个数
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:jboss/datasources/ExampleDS");
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            //生成录入者信息
            statement.executeUpdate("create table admin(id char (20) not null, password char (20));");
            statement.executeUpdate("insert into admin(id,password)" +
                    "values('nxt','nxt')");
            String password = "";
            List<String> listId = new ArrayList();
            listId = getIdList(size);
            FileWriter fw = new FileWriter("./admin.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("id,password,\n");
            bw.write("nxt,nxt,\n");
            for (String s : listId) {
                password = getString(allChar, 5);
                bw.write("" + s);
                bw.write("," + password + ",\n");
                statement.executeUpdate("insert into admin(id,password)" +
                        "values('" + s + "','" + password + "')");
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
