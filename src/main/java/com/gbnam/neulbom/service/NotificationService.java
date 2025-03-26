package com.gbnam.neulbom.service;

import com.gbnam.neulbom.entity.Notice;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    // 공지사항 등록 후 알림 전송
    public void sendNewNoticeNotification(Notice notice) {
        String notificationTitle = "[" + notice.getRegion() + "] " + notice.getTitle();
        String notificationBody = notice.getContent();

        // Notification 객체 생성 (Builder 사용)
        Notification notification = Notification.builder()
                .setTitle(notificationTitle)
                .setBody(notificationBody)
                .build();

        // 메시지 구성 (토픽 방식: 모든 앱 사용자에게 전송)
        Message message = Message.builder()
                .setNotification(notification)
                .putData("noticeId", notice.getId().toString())
                .setTopic("notices")
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("푸시 알림 전송 성공: " + response);
        } catch (FirebaseMessagingException e) {
            System.err.println("푸시 알림 전송 실패: " + e.getMessage());
        }
    }
}