package com.root.allInOne.LLD.NotificationService.interfaces;

import com.root.allInOne.LLD.NotificationService.dto.HistoryDTO;

public interface INotificationChannel {
    void sendNotification();
    //sendNotificatino and saves in  DB
    HistoryDTO getNotificationHistory(String userId);
}

