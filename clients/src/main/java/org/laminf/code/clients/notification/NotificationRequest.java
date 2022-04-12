package org.laminf.code.clients.notification;

public record NotificationRequest(
        Integer toEmployeeId,
        String toEmployeeName,
        String message
) {
}
