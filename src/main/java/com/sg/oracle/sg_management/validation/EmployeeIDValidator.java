package com.sg.oracle.sg_management.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Collection;

public class EmployeeIDValidator implements Validator
{
    private static final FacesMessage ERROR_EMPLOYEE_ID_FORMAT = new FacesMessage("Çalışanın ID'si sayı(int) olmalıdır.");
    private static final FacesMessage ERROR_EMPLOYEE_ID_NEGATIVE = new FacesMessage("Çalışanın ID'si negatif olamaz.");

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
    {
        String strEmployeeID = value.toString();

        try
        {
            int employeeID = Integer.parseInt(strEmployeeID);

            if(employeeID < 0)
            {
                throw new ValidatorException(ERROR_EMPLOYEE_ID_NEGATIVE);
            }
        }
        catch (NumberFormatException e)
        {
            throw new ValidatorException(ERROR_EMPLOYEE_ID_FORMAT);
        }
    }
}

