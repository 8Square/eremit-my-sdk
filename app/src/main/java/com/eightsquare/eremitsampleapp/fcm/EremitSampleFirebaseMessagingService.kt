package com.eightsquare.eremitsampleapp.fcm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.app.mtaeremit.firebase.FCMIdRegistration
import com.app.mtaeremit.model.NotificationData
import com.app.mtaeremit.utils.notification.SdkNotification
import com.eightsquare.eremitsampleapp.BuildConfig
import com.eightsquare.eremitsampleapp.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.library.eightsquarei.model.EnvType
import com.library.eightsquarei.model.UserInfo
import com.library.eightsquarei.utils.NetworkConstants
import java.net.HttpURLConnection
import java.net.URL


/**
 * Created by anp01 on 7/21/17.
 */
class EremitSampleFirebaseMessagingService : FirebaseMessagingService() {

    var bitmap: Bitmap? = null

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        if (remoteMessage.data.isNotEmpty()) {
            val data = remoteMessage.data

            val title = data[Constants.TITLE]
            val message = data[Constants.MESSAGE]
            var image: String? = ""
            if (data.containsKey(Constants.IMAGE)) {
                image = data[Constants.IMAGE]
                if (!image.isNullOrEmpty())
                    bitmap = getBitmapFromUrl(image)
            }

            val notification = NotificationData()
            notification.messageTitle = title
            notification.messageBody = message
            notification.bitmap = bitmap
            SdkNotification.createNotification(
                this,
                notification,
                getString(R.string.api_key),
                EnvType.SIT
            ) //todo://Need to change for different EnvType
            bitmap = null//reset bitmap
        }
    }


    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        if (BuildConfig.DEBUG)
            Log.e("test", "Fcm token: " + token)
        
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        FCMIdRegistration.sendFcmToken(
            this,
            null,
            token,
            EnvType.SIT
        ) //todo://Need to change for different EnvType
    }

    /**
     * Get Bitmap from url
     */
    private fun getBitmapFromUrl(imageUrl: String?): Bitmap? {
        try {
            val url = URL(NetworkConstants.BASE_URL + imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            return BitmapFactory.decodeStream(input)

        } catch (e: Exception) {
            e.printStackTrace()
            return null

        }
    }
}