package entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class PointPk implements Serializable {
    @Column(name = "user_id")
    private String userId;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private Point.Type type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Point.Type getType() {
        return type;
    }

    public void setType(Point.Type type) {
        this.type = type;
    }
}
