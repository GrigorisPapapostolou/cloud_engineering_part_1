package com.cloud.engineering.statement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StatementDto {
    @NotBlank(message = "Statement is required")
    @Size(min = 4, message = "Sorry, but the given statement is too short. Statement must be at least 15 characters long.")
    private String statement;

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
