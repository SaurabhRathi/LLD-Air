package com.root.allInOne.LLD.NotificationService.service;

import com.root.allInOne.LLD.NotificationService.dto.HistoryDTO;
import com.root.allInOne.LLD.NotificationService.interfaces.INotificationChannel;
import org.springframework.stereotype.Service;

@Service
class PushChannel implements INotificationChannel {

    @Override
    public void sendNotification() {

    }

    @Override
    public HistoryDTO getNotificationHistory(String userId) {
        return null;
    }
}