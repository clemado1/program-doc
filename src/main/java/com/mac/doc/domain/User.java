package com.mac.doc.domain;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "doc_user")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "userCache")
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    private String userId;

    private String userNm;

    private String passwd;

    @Builder
    public User(String userId, String userNm) {
        Assert.hasText(userId, "userId must not be empty");

        this.userId = userId;
        this.userNm = userNm;
    }

}
