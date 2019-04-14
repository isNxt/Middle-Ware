package org.jboss.as.quickstarts.ejb.remote.client;

import org.jboss.as.quickstarts.ejb.remote.stateless.RemoteAdmin;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RemoteEJBClient {

    public static void main(String[] args) throws Exception {
        invokeStatelessBean();
    }

    private static void invokeStatelessBean() throws NamingException {
        final RemoteAdmin statefulRemoteAdmin = lookupRemoteStatelessAdmin();
        String id;
        String password;
        String value = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入录入人员id:");
        id = scanner.nextLine();
        System.out.println("请输入密码:");
        password = scanner.nextLine();
        statefulRemoteAdmin.init();
        //登陆
        List<Map<String, String>> result = statefulRemoteAdmin.Login("select password from admin where id ='" + id + "'");
        Map<String, String> map = result.get(0);
        for (String s : map.keySet()) value = map.get(s);
        if (value.equals(password)) {
            System.out.println("登陆成功!");
            while (true) {
                System.out.println("请输入您要进行的操作：\n1. 查询校友\n2. 新建校友\n3. 删除校友\n4. 修改校友信息\n5. 退出");
                int choice;
                choice = scanner.nextInt();
                if (choice == 1) {
                    System.out.println("请输入校友的名称：");
                    Scanner aa = new Scanner(System.in);
                    String name = aa.nextLine();
                    List listResult = statefulRemoteAdmin.SearchAlumni("select * from alumni where name ='" + name + "'");
                    if (listResult.size()==0)
                        System.out.println("查无此人");
                    else System.out.println(listResult);
                }
                else if (choice == 2) {
                    System.out.println("请依次输入新建校友的：姓名, 性别, 生日, 入学年份, 毕业年份, 工作城市, 工作单位, 职务, 手机, 邮箱, 微信");
                    String name, gender, birthday, entrance, graduation, location, company, job, phone, email, wechat;
                    Scanner nn = new Scanner(System.in);
                    System.out.println("请输入姓名：");
                    name = nn.nextLine();
                    System.out.println("请输入性别：");
                    gender = nn.nextLine();
                    System.out.println("请输入生日：");
                    birthday = nn.nextLine();
                    System.out.println("请输入入学年份：");
                    entrance = nn.nextLine();
                    System.out.println("请输入毕业年份：");
                    graduation = nn.nextLine();
                    System.out.println("请输入工作城市：");
                    location = nn.nextLine();
                    System.out.println("请输入工作单位：");
                    company = nn.nextLine();
                    System.out.println("请输入职务：");
                    job = nn.nextLine();
                    System.out.println("请输入手机：");
                    phone = nn.nextLine();
                    System.out.println("请输入邮箱：");
                    email = nn.nextLine();
                    System.out.println("请输入微信：");
                    wechat = nn.nextLine();
                    statefulRemoteAdmin.UpdateAlumni("insert into alumni(name,gender,birthday,entrance,graduation,location,company,job,phone,email,wechat)values(" +
                            "'" + name + "'," + "'" + gender + "','" + birthday + "','" + entrance + "','" + graduation + "','" + location + "','" + company + "','" + job + "','" + phone + "','" + email + "','" + wechat + "'" +
                            ");");
                } else if (choice == 3) {
                    System.out.println("请输入要删除的校友姓名：");
                    String name;
                    Scanner dd = new Scanner(System.in);
                    name = dd.nextLine();
                    statefulRemoteAdmin.UpdateAlumni("delete from alumni where name ='" + name + "';");
                } else if (choice == 4) {
                    String name;
                    System.out.println("请输入要更新的校友姓名");
                    Scanner rr = new Scanner(System.in);
                    name = rr.nextLine();
                    System.out.println(statefulRemoteAdmin.SearchAlumni("select * from alumni where name ='" + name + "'"));
                    System.out.println("请输入要更新的信息：不需要更新的请输入no");
                    String gender, birthday, entrance, graduation, location, company, job, phone, email, wechat;
                    System.out.println("请输入性别：");
                    Scanner n = new Scanner(System.in);
                    gender = n.nextLine();
                    if (!gender.equals("no")) {
                        statefulRemoteAdmin.UpdateAlumni("update alumni set gender='" + gender + "'where name='" + name + "';");
                    }
                    System.out.println("请输入生日：");
                    Scanner scanner1 = new Scanner(System.in);
                    birthday = scanner1.nextLine();
                    if (!birthday.equals("no")) {
                        statefulRemoteAdmin.UpdateAlumni("update alumni set birthday='" + birthday + "'where name='" + name + "';");
                    }
                    System.out.println("请输入入学年份：");
                    entrance = scanner1.nextLine();
                    if (!entrance.equals("no")) {
                        statefulRemoteAdmin.UpdateAlumni("update alumni set entrance='" + entrance + "'where name='" + name + "';");
                    }
                    System.out.println("请输入毕业年份：");
                    graduation = scanner1.nextLine();
                    if (!graduation.equals("no")) {
                        statefulRemoteAdmin.UpdateAlumni("update alumni set graduation='" + graduation + "'where name='" + name + "';");
                    }
                    System.out.println("请输入工作城市：");
                    location = scanner1.nextLine();
                    if (!location.equals("no")) {
                        statefulRemoteAdmin.UpdateAlumni("update alumni set location='" + location + "'where name='" + name + "';");
                    }
                    System.out.println("请输入工作单位：");
                    company = scanner1.nextLine();
                    if (!company.equals("no")) {
                        statefulRemoteAdmin.UpdateAlumni("update alumni set company='" + company + "'where name='" + name + "';");
                    }
                    System.out.println("请输入职务：");
                    job = scanner1.nextLine();
                    if (!job.equals("no")) {
                        statefulRemoteAdmin.UpdateAlumni("update alumni set job='" + job + "'where name='" + name + "';");
                    }
                    System.out.println("请输入手机：");
                    phone = scanner1.nextLine();
                    if (!phone.equals("no")) {
                        statefulRemoteAdmin.UpdateAlumni("update alumni set phone='" + phone + "'where name='" + name + "';");
                    }
                    System.out.println("请输入邮箱：");
                    email = scanner1.nextLine();
                    if (!email.equals("no")) {
                        statefulRemoteAdmin.UpdateAlumni("update alumni set email='" + email + "'where name='" + name + "';");
                    }
                    System.out.println("请输入微信：");
                    wechat = scanner1.nextLine();
                    if (!wechat.equals("no")) {
                        statefulRemoteAdmin.UpdateAlumni("update alumni set wechat='" + wechat + "'where name='" + name + "';");
                    }
                } else if (choice == 5) {
                    System.out.println("您已退出！");
                    break;
                }
            }
        } else {
            System.out.println("id或密码错误!");
        }
    }

    private static RemoteAdmin lookupRemoteStatelessAdmin() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        return (RemoteAdmin) context.lookup("ejb:/wildfly-ejb-remote-server-side/AdminBean!"
                + RemoteAdmin.class.getName());
    }
}
