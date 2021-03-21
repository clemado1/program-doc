package com.mac.doc.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "document")
@Getter
@ToString(exclude = {"function"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "docCache")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "docId")
public class Document extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Long docId;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "function_cd")
    private Function function = new Function();

    private Long docSn;

    @Builder
    public Document(Long docId, Function function, String functionCd, Long docSn, String title) {
        this.docId = docId;
        this.title = title;
        this.function = function;
        this.docSn = docSn;
    }

}
