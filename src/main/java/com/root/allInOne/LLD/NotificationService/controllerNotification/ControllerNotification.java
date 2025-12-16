package com.root.allInOne.LLD.NotificationService.controllerNotification;

import com.root.allInOne.LLD.NotificationService.interfaces.ISendNotification;
import com.root.allInOne.LLD.NotificationService.dto.HistoryDTO;
import com.root.allInOne.LLD.NotificationService.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ControllerNotification {

    public ISendNotification sendNotification;

    public ControllerNotification(@Autowired ISendNotification sendNotification) {
        this.sendNotification = sendNotification;
    }
    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody  NotificationDTO notificationDTO) {
        this.sendNotification.sendNotificaton(notificationDTO);
        //handling errors // catching errors //using try catch
        //sending proper response code
        return ResponseEntity.ok("Success");
    }

    @GetMapping
    public ResponseEntity<List<HistoryDTO>> getNotificationHistory(@RequestParam String userId) {
        return ResponseEntity.ok(sendNotification.getNotificationHistory(userId));
    }

    //userId - [SMS - time, FAILURE]

}



/*
*
*    notificationchannel sendNotificaiton   -> SMS channel, emial channel
*
*  channelmap
*
*  enum -> smschannel bean
*
*
*
*
* */