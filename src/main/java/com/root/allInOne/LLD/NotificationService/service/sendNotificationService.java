package com.root.allInOne.LLD.NotificationService.service;

import com.root.allInOne.LLD.NotificationService.commmmon.NotificationChannel;
import com.root.allInOne.LLD.NotificationService.interfaces.ISendNotification;
import com.root.allInOne.LLD.NotificationService.interfaces.INotificationChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.root.allInOne.LLD.NotificationService.dto.HistoryDTO;
import com.root.allInOne.LLD.NotificationService.dto.NotificationDTO;

import java.util.*;


@Service
public class sendNotificationService implements ISendNotification {
    private INotificationChannel smsChannel;
    private INotificationChannel emailChannel;
    private INotificationChannel pushChannel;

    private UserDetailsService userDetailsService;

    //mujhe interview ke vakt @Qualifier yaad hi nahi aaraha tha bc
    public sendNotificationService(
                            @Autowired UserDetailsService userDetailsService) {
        this.emailChannel = new EmailChannel();
        this.smsChannel = new SmsChannel();
        this.pushChannel = new PushChannel();
    }


    @Override
    public void sendNotificaton(NotificationDTO dto) {
        String userDetails = userDetailsService.getUserDetails(dto.userId);
        dto.channelList.forEach(notificationChannel -> {
            //switch
            if(notificationChannel.equals(NotificationChannel.SMS)) {
                this.smsChannel.sendNotification();
            } else if (notificationChannel.equals(NotificationChannel.EMAIL)) {
                this.emailChannel.sendNotification();
            } else {
                this.pushChannel.sendNotification();
            }
        });
    }


    public List<HistoryDTO> getNotificationHistory(String userId) {
        return List.of(smsChannel.getNotificationHistory(userId),
                emailChannel.getNotificationHistory(userId),
                pushChannel.getNotificationHistory(userId));
    }
}
