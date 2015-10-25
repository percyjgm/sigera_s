package com.sigera.jsf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.log4j.Logger;

/**
 *
 * @author Luis Alonso Ballena Garcia
 */
public class ValidatePhase implements PhaseListener {

    private static final long serialVersionUID = 209569552202639216L;
    private static final Logger log = Logger.getLogger(ValidatePhase.class.getPackage().getName());

    @Override
    public void afterPhase(PhaseEvent event) {
        log.debug("[ValidatePhase/afterPhase] finalización de la Fase de PROCESS_VALIDATIONS");
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        log.debug("[ValidatePhase/beforePhase] comienzo de la Fase de PROCESS_VALIDATIONS");
        FacesContext context = event.getFacesContext();
        UIComponent component = context.getViewRoot();
        Iterator<String> iter = context.getExternalContext().getRequestParameterNames();
        boolean esValido = true;
        for (Iterator<String> i = iter; i.hasNext();) {
            UIComponent children = component.findComponent(i.next());
            if (children instanceof UIInput) {
                UIInput input = (UIInput) children;
                log.debug(String.format("El id del input es %s", input.getClientId()));
                log.debug(String.format("El valor del input es %s", input.getSubmittedValue()));
                if (!esValido) {
                    input.setValid(false);
                    continue;
                }
                esValido = executeValidateInput(input, context);
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.PROCESS_VALIDATIONS;
    }

    private boolean executeValidateInput(UIInput input, FacesContext context) {
        boolean esValido = true;
        if (input != null) {
            try {
                executeValidators(input.getValidators(), context, input, input.getSubmittedValue());
            } catch (ValidatorException e) {
                log.debug("Valido la excepcion");
                context.validationFailed();
                input.setValid(false);
                esValido = false;
                context.addMessage(null, e.getFacesMessage());
            }
        }
        return esValido;
    }

    private void executeValidators(Validator[] validators, FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        log.debug(String.format("validadores %d", validators.length));
        for (Validator validator : validators) {
            validator.validate(context, component, value);
        }
    }

}
