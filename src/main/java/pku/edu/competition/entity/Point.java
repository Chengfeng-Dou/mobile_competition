package pku.edu.competition.entity;

import javax.persistence.*;

@Entity
@Table(name = "points")
@IdClass(PointPk.class)
public class Point {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Column(name = "num")
    private int num;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public enum Type {
        none, week, month, year
    }
}
