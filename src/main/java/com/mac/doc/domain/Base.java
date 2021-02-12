package com.mac.doc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @CreatedBy
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rgsn_user_id", referencedColumnName = "user_id")
    private User rgsnUser;

    @CreationTimestamp
    private ZonedDateTime rgsnDttm;

    @JsonIgnore
    @LastModifiedBy
    @OneToOne(fetch = FetchType.LAZY)
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
