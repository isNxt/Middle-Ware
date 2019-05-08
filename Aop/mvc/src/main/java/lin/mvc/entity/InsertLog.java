package lin.mvc.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "insert_log")
@Table(indexes = {
        // 唯一索引。
        @Index(name = "id", columnList = "id", unique = true)
})

public class InsertLog {
    @Id
    @GeneratedValue
    @Column(length = 20)
    private Long id;

    @Column(length = 100, nullable = false)
    private String operator;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date operationTime;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 16)
    private String gender;

    @Column(length = 100)
    private String company;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }
}
