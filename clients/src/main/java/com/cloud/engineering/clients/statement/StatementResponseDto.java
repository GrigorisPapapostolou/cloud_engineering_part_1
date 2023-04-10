package com.cloud.engineering.clients.statement;

import lombok.Data;

@Data

public class StatementResponseDto {
    private final String title = "Declaration Statement";
    private final String paragraph = "At my own risk and knowing the sanctions, provided by the provisions of par. 6 of article 22 of Law 7845/65978, I declare that: ";
    private String declaration;

    public StatementResponseDto() {}
    public StatementResponseDto(String doc) {
        this.declaration = doc;
    }

    public String getTitle() {
        return title;
    }

    public String getParagraph() {
        return paragraph;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StatementResponseDto{");
        sb.append("title=").append(title);
        sb.append(", paragraph=").append(paragraph);
        sb.append(", declaration=").append(declaration);
        return sb.toString();
    }
}
