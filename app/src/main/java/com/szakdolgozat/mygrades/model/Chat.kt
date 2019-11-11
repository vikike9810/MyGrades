package com.szakdolgozat.mygrades.model

object Chat {
    var talkings =ArrayList<Talking>()

    fun getTalkingsByUser(): ArrayList<Talking>{
        var newTalkings=ArrayList<Talking>()
        if(User.person!=null) {
            for (talking: Talking in talkings) {
                if (talking.person1.equals(User.person) || talking.person2.equals(User.person)) {
                    newTalkings.add(talking)
                }
            }
        }
        return  newTalkings
    }

    fun getTalkingById(id: String): Talking?{
            for (talking: Talking in talkings) {
                if (talking.Id.toString().equals(id)) {
                    return talking
                }
            }
        return null
    }
}