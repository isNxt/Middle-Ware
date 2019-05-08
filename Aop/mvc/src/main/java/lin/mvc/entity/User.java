package lin.mvc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "alumni") // 定义数据库表名称。
@Table(indexes = { // 定义数据库索引。

        // 唯一索引。
        @Index(name = "id", columnList = "id", unique = true), //

        // 非唯一索引。
        @Index(name = "name", columnList = "name"), //
})

public class User {
    @Id
    @GeneratedValue
    @Column(length = 20)
    private Long id;

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
}
