package me.yang.restapi.events;

import lombok.*;

import java.time.LocalDateTime;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id")
// EqualsAndHashCode 을 비교할 때 모든 필드를 사용하는데
// Entity간 연관관계가 있을 때, EqualsAndHashCode을 통해하면 StackOverFlow가 발생 가능성이 있다.
// 서로간 계속 호출 가능성이 있기 때문에
public class Event {

    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; //(optional)이게 없으면 온라인 모임
    private int basePrice; //(optional)
    private int maxPrice; //(optional)
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;
    private EventStatus eventStatus;

}
