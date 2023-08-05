package org.example.web.dto;

import javax.validation.constraints.NotEmpty;

public class RegEx {
    @NotEmpty
    private String expRegEx;

    public String getExpRegEx() {
        return expRegEx;
    }

    public void setExpRegEx(String expRegEx) {
        this.expRegEx = expRegEx;
    }
}
