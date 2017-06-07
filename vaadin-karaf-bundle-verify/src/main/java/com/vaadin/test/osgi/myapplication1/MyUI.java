package com.vaadin.test.osgi.myapplication1;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.BeanValidator;
import org.osgi.service.component.annotations.Component;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() + ", it works!"));
        });

        layout.addComponents(name, button);

        setContent(layout);
    }

    private static void checkBeanValidation() {
        runValidator(null);
        runValidator("0123456789abcde");
    }

    private static void runValidator(String value) {
        BeanValidator beanValidator = new BeanValidator(RequiredConstraints.class, "firstName");
        ValidationResult result = beanValidator.apply(value,
                new ValueContext());
        if (!result.isError()) throw new RuntimeException("Validation should fail!");
    }

    @Component(service = VaadinServlet.class)
    @WebServlet(urlPatterns = "/myapp2/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
        @Override
        public void init(ServletConfig servletConfig) throws ServletException {
            super.init(servletConfig);
            checkBeanValidation();

        }
    }

}
