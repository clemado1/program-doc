package com.mac.doc.domain;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "label")
@Getter
@ToString(exclude = "documentData")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "labelCache")
public class Label extends Base {
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "label")
    private final Set<DocumentData> documentData = new HashSet<>();
    @Id
    @Column(name = "label_id")
    private long labelId;
    private String labelNm;

    @Builder
    public Label(long labelId, String labelNm) {
        this.labelId = labelId;
        this.labelNm = labelNm;
    }

}
