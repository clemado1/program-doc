package com.mac.doc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private String passwd;

    // TO-DO: 사용자 권한

    @Builder
    public User(String userId, String userNm) {
        Assert.hasText(userId, "userId must not be empty");

        this.userId = userId;
        this.userNm = userNm;
    }

}
