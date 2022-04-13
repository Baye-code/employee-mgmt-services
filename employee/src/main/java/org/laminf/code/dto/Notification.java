package org.laminf.code.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Notification {

    private Integer notificationId;
    private Integer toEmployeeId;
    private String toEmployeeEmail;
    private String sender;
    private String message;
    private LocalDateTime sentAt;

}