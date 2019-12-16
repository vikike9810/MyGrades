package com.szakdolgozat.mygrades.sortingtests

import com.szakdolgozat.mygrades.model.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import kotlin.collections.ArrayList

@RunWith(MockitoJUnitRunner::class)
class SortingTests {

    @Mock
    lateinit var testPerson: Person

    @Mock
    lateinit var talking: Talking


    var listOfMessages=ArrayList<Message>()

    @Before
    fun setUp(){

        for(i : Int in 1..10){
            var date=Calendar.getInstance()
            date.set(2019,1,1, 10, i)
            listOfMessages.add(Message(i,"",testPerson, date.clone() as Calendar))
        }

        `when`(talking.getTalkingMessages()).thenReturn(listOfMessages)
    }

    @Test
    fun messagesSortedInCorrectOrder(){
        talking.getTalkingMessages().sortWith(Message.MessageComparator)
        assert(checkMessagesOrder(listOfMessages))
    }

    fun checkMessagesOrder(list: ArrayList<Message>): Boolean {
        for (index: Int in 1 until list.size) {
            if (list[index - 1].sendDate.compareTo(list[index].sendDate)>0) {
                return false
            }
        }
        return true
    }



    @Test
    fun talkingsSortedInCorrectOrdertalkingsSortedInCorrectOrder(){

        var list=ArrayList<Talking>()
        for(i : Int in 1..10){
            list.add(Talking(testPerson, testPerson,talking.getTalkingMessages()))
        }
        list.sortWith(Talking.TalkingComparator)

        assert(checkTalkingsOrder(list))

    }

    fun checkTalkingsOrder(list: ArrayList<Talking>): Boolean {
        for (index: Int in 1 until list.size) {
            if (list[index - 1].getLastMessage()!!.sendDate
                    .compareTo(list[index].getLastMessage()!!.sendDate)
                <0) {
                return false
            }
        }
        return true
    }

    @Test
    fun subjectsSortedInCorrectOrder(){

        var list=ArrayList<Subject>()
        for(i : Int in 1..10){
            val subject=Subject(12)
            subject.Name= "Sub$i"
            list.add(subject)
        }
        list.sortWith(Subject.SubjectComparator)

        assert(checkSubjectsOrder(list))

    }

    fun checkSubjectsOrder(list: ArrayList<Subject>): Boolean {
        for (index: Int in 1 until list.size) {
            if (list[index - 1].Name.compareTo(list[index].Name)
                >0) {
                return false
            }
        }
        return true
    }




}