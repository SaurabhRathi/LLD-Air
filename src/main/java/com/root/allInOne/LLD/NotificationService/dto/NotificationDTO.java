package com.root.allInOne.LLD.NotificationService.dto;


import com.root.allInOne.LLD.NotificationService.commmmon.NotificationChannel;
import com.root.allInOne.LLD.NotificationService.commmmon.Status;
import java.util.*;

public class NotificationDTO {
    public Date dateTime;
    public Status status;

    public String userId;

    public List<NotificationChannel> channelList;
}
