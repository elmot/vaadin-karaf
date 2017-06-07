package com.vaadin.test.osgi.myapplication1;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RequiredConstraints implements Serializable {
    @NotNull
    @Max(10)
    private String firstname;
}