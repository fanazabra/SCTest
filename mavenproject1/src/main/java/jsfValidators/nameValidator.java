/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfValidators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


/**
 *
 * @author Зебер
 */
@FacesValidator("nameValidator")
public class nameValidator implements Validator{  
    
    public static final String nameRusPattern = "[А-Яа-я]+";
    public static final String nameLatPattern = "[A-Za-z]+";
    
    private Pattern patternRus;
    private Matcher matcherRus;
    private Pattern patternLat;
    private Matcher matcherLat;

 
    public nameValidator(){
        patternRus = Pattern.compile(nameRusPattern);
        patternLat = Pattern.compile(nameLatPattern);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String name = value.toString();
        if( value == null || "".equals(value.toString().trim()))
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Имя не должно быть пустым", "Имя не должно быть пустым");
            throw new ValidatorException(msg);
        }

        

        matcherRus = patternRus.matcher(name);
        matcherLat = patternLat.matcher(name);
        if(!matcherRus.matches() && !matcherLat.matches())
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Имя должно состоять только из русских/латинских букв", "Имя должно состоять только из русских/латинских букв");
            throw new ValidatorException(msg);
        }
    }
    
}
