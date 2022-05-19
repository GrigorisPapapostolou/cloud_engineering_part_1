package com.cloud.engineering.citizen.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OptionDto {
    private String opt;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }
}
