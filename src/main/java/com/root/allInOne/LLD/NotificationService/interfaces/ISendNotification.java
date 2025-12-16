package com.root.allInOne.LLD.NotificationService.interfaces;

import com.root.allInOne.LLD.NotificationService.dto.HistoryDTO;
import com.root.allInOne.LLD.NotificationService.dto.NotificationDTO;
import java.util.*;
public interface ISendNotification {
    void sendNotificaton(NotificationDTO dto);

    List<HistoryDTO> getNotificationHistory(String userId);
}
