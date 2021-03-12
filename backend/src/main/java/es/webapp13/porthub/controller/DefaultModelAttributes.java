package es.webapp13.porthub.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class DefaultModelAttributes {

    private boolean logued;

    public DefaultModelAttributes() {
        this.logued = false;
    }

    @ModelAttribute("logued")
    public Boolean userLogued() {
        return logued;
    }

    public void setLogued(boolean logued) {
        this.logued = logued;
    }



}
