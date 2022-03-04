package com.sg.oracle.sg_management.validation;

import com.sg.oracle.sg_management.utility.Util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Collection;

public class TCKNValidator implements Validator
{
    private static final int TCKN_DIGIT = 11;
    private static final FacesMessage ERROR_TCKN_DIGIT = new FacesMessage("TCKN "+  TCKN_DIGIT + " haneli olmalıdır.");
    private static final FacesMessage ERROR_TCKN_FORMAT = new FacesMessage("TCKN sayı(int) olmalıdır.");
    private static final FacesMessage ERROR_TCKN_NEGATIVE = new FacesMessage("TCKN negatif olamaz.");
    private static final FacesMessage ERROR_TCKN_ALGORITHM = new FacesMessage("TCKN algoritmasına uygun olmayan TCKN girilemez.");

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
    {
        String strTCKN = value.toString();

        try
        {
            long TCKN = Long.parseLong(strTCKN);

            if(TCKN < 0)
            {
                throw new ValidatorException(ERROR_TCKN_NEGATIVE);
            }
            else if(strTCKN.length() != TCKN_DIGIT)
            {
                throw new ValidatorException(ERROR_TCKN_DIGIT);
            }
            else if(!Util.isValidTCKN(strTCKN))
            {
                throw new ValidatorException(ERROR_TCKN_ALGORITHM);
            }
        }
        catch (NumberFormatException e)
        {
            throw new ValidatorException(ERROR_TCKN_FORMAT);
        }
    }
}

