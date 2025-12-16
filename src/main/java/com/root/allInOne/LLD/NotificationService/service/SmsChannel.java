package com.root.allInOne.LLD.NotificationService.service;

import com.root.allInOne.LLD.NotificationService.dto.HistoryDTO;
import com.root.allInOne.LLD.NotificationService.interfaces.INotificationChannel;

class SmsChannel implements INotificationChannel {

    @Override
    public void sendNotification() {

    }

    @Override
    public HistoryDTO getNotificationHistory(String userId) {
        return null;
    }
}