package lin.mvc.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "user_log")
@Table(indexes = {
        // 唯一索引。
        @Index(name = "id", columnList = "id", unique = true)
})

public class UserLog {
    @Id
    @GeneratedValue
    @Column(length = 20)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date loginDate;

    @Column(length = 16)
    private String operation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
