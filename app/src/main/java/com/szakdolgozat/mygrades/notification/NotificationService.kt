package com.szakdolgozat.mygrades.notification

import android.app.Notification
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.database.DatabaseHandler
import com.szakdolgozat.mygrades.model.*
import java.util.*

class NotificationService : FirebaseMessagingService() {

    var newtalking: Talking? = null

    override fun onMessageReceived(p0: RemoteMessage) {
        if (p0.data["type"].equals(getString(R.string.message))) {
            getMessage(p0)
        } else if (p0.data["type"].equals(getString(R.string.grade_type))) {
            getGrade(p0)
        }
    }

    fun getMessage(p0: RemoteMessage) {
        val sender = Diary.getTeacherById(p0.data["sender"] ?: "") ?: Diary.getStudentById(p0.data["sender"] ?: "")
        val message = p0.data["message"]
        val messageId = p0.data["messageId"]
        val talkingId = p0.data["talkingId"]
        if (sender != null && message != null && talkingId != null && messageId != null) {
            newtalking = getTalking(messageId, sender, talkingId)
            val builder = createNotification("New message from : " + sender.getName(), message, R.drawable.comment)
            DatabaseHandler.getMessage(messageId, builder, { showNotification(builder) })
        }
    }

    fun getGrade(p0: RemoteMessage) {
        val subject = Diary.getSubjectById(p0.data["subjectId"] ?: "")
        val grade = p0.data["grade"]
        val gradeId = p0.data["gradeId"]
        if (subject != null && grade != null && gradeId != null) {
            val builder = createNotification("New grade from : " + subject.Name, grade, R.drawable.grade)
            DatabaseHandler.getGrade(gradeId, builder, { showNotification(builder) })


        }
    }

    fun getTalking(messageId: String, sender: Person, talkingId: String): Talking {

        var talking = Chat.getTalkingById(talkingId)
        if (talking == null) {
            talking = Talking((Integer.parseInt(messageId)), sender, User.person!!)
        }
        return talking

    }

    private fun showNotification(builder: NotificationCompat.Builder) {
        with(NotificationManagerCompat.from(this)) {
            notify(Calendar.getInstance().timeInMillis.toInt(), builder.build())
        }
    }

    fun createNotification(title: String, message: String, icon: Int): NotificationCompat.Builder {

        val build = NotificationCompat.Builder(this)
            .setSmallIcon(icon)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setDefaults(Notification.DEFAULT_ALL)

        return build
    }


}