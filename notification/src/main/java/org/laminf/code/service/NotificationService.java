package org.laminf.code.service;

import org.laminf.code.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.laminf.code.model.Notification;
import org.laminf.code.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toEmployeeId(notificationRequest.toEmployeeId())
                        .toEmployeeEmail(notificationRequest.toEmployeeName())
                        .sender("Baye-Code")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }

}