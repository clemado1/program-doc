package com.mac.doc.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Base implements Serializable {

    @CreatedBy
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rgsn_user_id", referencedColumnName = "user_id")
    private User rgsnUser;

    @CreationTimestamp
    private ZonedDateTime rgsnDttm;

    @LastModifiedBy
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modi_user_id", referencedColumnName = "user_id")
    private User modiUser;

    @UpdateTimestamp
    private ZonedDateTime modiDttm;

    public void setRgsnUser(User rgsnUser) {
        this.rgsnUser = rgsnUser;
    }

    public void setModiUser(User modiUser) {
        this.modiUser = modiUser;
    }

}
