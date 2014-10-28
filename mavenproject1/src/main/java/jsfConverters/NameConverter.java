/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfConverters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Класс для преобразования первой буквы фамилии.
 * @author Зебер
 */
@FacesConverter("nameConveter")
public class NameConverter implements Converter{

    /**
     * конструктор NameConverter
     */
    public NameConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if("".equals(value))
        {
            return "";
        }
        String result = value.substring(0, 1).toUpperCase() + value.substring(1);
        return result;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String toConvert = value.toString();
        if("".equals(toConvert))
        {
            return "";
        }
        String result = toConvert.substring(0, 1).toUpperCase() + toConvert.substring(1);
        return result;
    }
}
