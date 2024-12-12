package com.example.strechingstudio.controller;

import com.example.strechingstudio.utility.DateFormatProvider;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalAttributes {
    @ModelAttribute("dateFormats")
    public DateFormatProvider dateFormats() {
        return new DateFormatProvider();
    }
}