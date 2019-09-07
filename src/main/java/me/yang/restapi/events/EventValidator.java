package me.yang.restapi.events;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class EventValidator {
    public void validate(EventDto eventDto, Errors erros) {
        if(eventDto.getBasePrice() > eventDto.getMaxPrice() && eventDto.getMaxPrice() > 0) {
            erros.rejectValue("basePrice", "WrongValue", "BasePrice is wrong");
            erros.rejectValue("maxPrice", "WrongValue", "maxPrice is wrong");
        }
        LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
        if (endEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime()) ||
                endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime()) ) {
            erros.rejectValue("endEventDateTime", "WrongValue", "endEventDateTime is wrong");
        }

        // TODO beginEventTime
        // TODO CloseEnrollmentDateTime

    }

}
