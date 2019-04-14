package org.jboss.as.quickstarts.ejb.remote.stateless;

import java.util.List;

public interface RemoteAdmin {
    void init(); //初始化，创建Admin和Alumni表格,并随机生成数据
    List Login(String sql); //登陆,检测登陆的合法性
    void UpdateAlumni(String sql); //查询校友信息
    List SearchAlumni(String sql); //修改校友信息
}
