package com.mac.doc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CompareDto {
    private DocumentDto document;
    private DocumentDto firstDocumentData;
    private DocumentDto secondDocumentData;

    public CompareDto(DocumentDto document, DocumentDto firstDocumentData, DocumentDto secondDocumentData) {
        this.document = document;
        this.firstDocumentData = firstDocumentData;
        this.secondDocumentData = secondDocumentData;
    }
}
