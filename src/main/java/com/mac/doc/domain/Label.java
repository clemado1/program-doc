package com.mac.doc.domain;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "label")
@Getter
@ToString(exclude = "document")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "labelCache")
public class Label extends Base {
    @Id
    @Column(name = "label_id")
    private long labelId;

    private String labelNm;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "label")
    private Set<Document> document = new HashSet<>();

    @Builder
    public Label(long labelId, String labelNm) {
        this.labelId = labelId;
        this.labelNm = labelNm;
    }

}
