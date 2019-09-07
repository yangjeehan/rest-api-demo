package me.yang.restapi.events;

import org.modelmapper.ModelMapper;
import org.springframework.validation.Errors;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class EventController {

    private final EventRepository eventRepository;

    private final ModelMapper modelMapper;

    private final EventValidator eventValidator;

    public EventController(EventRepository eventRepository, ModelMapper modelMapper, EventValidator eventValidator ) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.eventValidator = eventValidator;
    }

    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid EventDto eventDto, Errors errors) {
        /*
            ModelMapper라는 얘가 DTO를 event로 변환해줌
            하지만 ModelrMapper을 쓸때 리플렉션이 일어나기에 성능의 저하가 발생
            -> 자바버전이 올라감에따라 성능이 올라가긴함 우려됌 직접 만들어 사용
            이런 과정 생략
            Event event = Event.build().name(eventDTo.name)
         */
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        eventValidator.validate(eventDto, errors);
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Event event = modelMapper.map(eventDto, Event.class);
        Event newEvent = this.eventRepository.save(event);
        URI createdUri = linkTo(EventController.class).slash(newEvent.getId()).toUri();
        return ResponseEntity.created(createdUri).body(event);
    }
}
