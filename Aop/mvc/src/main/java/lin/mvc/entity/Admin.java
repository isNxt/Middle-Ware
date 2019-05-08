package lin.mvc.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "admin")
@Table(indexes = {
        @Index(name = "id", columnList = "id", unique = true), //
        @Index(name = "name", columnList = "name", unique = true)
})

public class Admin {
    @Id
    @GeneratedValue
    @Column(length = 20)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date loginDate;

    @Column(length = 3)
    private Integer lastTime;

    @Column(length = 3)
    private Integer loginCount;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getLastTime() {
        return lastTime;
    }

    public void setLastTime(Integer lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }
}
