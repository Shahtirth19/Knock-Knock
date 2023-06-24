package com.example.mygate.ui.SendNotificationPack;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=\tAAAAiQaGVCc:APA91bGGqM9z6xdjwXGicv0z9wEswGd--_GDRkCCkgKV_F1QUfXyUuI5DmWQ9FgKhUMA9thnMwUeiTV3E5cMS6EJBqSKZj5ux65FzdV-5NbJ9MkvdShzTtRMOqlyGMfQN1S2uTXfnnRd"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotifcation(@Body NotificationSender body);
}
