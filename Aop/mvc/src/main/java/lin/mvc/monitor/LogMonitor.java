package lin.mvc.monitor;

import lin.mvc.entity.InsertLog;
import lin.mvc.entity.UserLog;
import lin.mvc.repository.InsertLogRepository;
import lin.mvc.repository.UserLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Aspect
@Component
public class LogMonitor {

    @Autowired
    private UserLogRepository userLogRepository;

    @Autowired
    private InsertLogRepository insertLogRepository;

    @After("execution(public * lin.mvc.controller.*.login(..))")
    public void loginServiceAccess(JoinPoint joinPoint)
    {
        HttpSession session= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        String s = String.valueOf(session.getAttribute("name"));
        if(!s.equals("null"))
        {
            UserLog userLog = new UserLog();
            userLog.setName(s);
            userLog.setLoginDate(new Date());
            userLog.setOperation("login");
            userLogRepository.save(userLog);
        }
    }

    @Before("execution(public * lin.mvc.controller.*.logout(..))")
    public void logoutServiceAccess(JoinPoint joinPoint)
    {
        HttpSession session= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        String s = String.valueOf(session.getAttribute("name"));
        if(!s.equals("null"))
        {
            UserLog userLog = new UserLog();
            userLog.setName(s);
            userLog.setLoginDate(new Date());
            userLog.setOperation("logout");
            userLogRepository.save(userLog);
        }
    }

    @After("execution(public * lin.mvc.controller.*.insert(..))")
    public void insertServiceAccess(JoinPoint joinPoint)
    {
        HttpServletRequest request= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session= request.getSession();

        InsertLog insertLog = new InsertLog();
        insertLog.setOperator(String.valueOf(session.getAttribute("name")));
        insertLog.setOperationTime(new Date());
        insertLog.setName(request.getParameter("textName"));
        insertLog.setGender(request.getParameter("textGender"));
        insertLog.setCompany(request.getParameter("textCompany"));

        insertLogRepository.save(insertLog);
    }
}

