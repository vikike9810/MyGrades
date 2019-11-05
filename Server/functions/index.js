const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();

exports.register= functions.https.onCall((data) =>{

    var userid = data.userid;
    var name = data.name;
    var type = data.type;
    var db = admin.database();

try{
    var ref = db.ref("users").child(type).child(userid).child("name").set(name);
    return 'OK';
}
catch(e){
    return e.message.toString();
}
}
);

exports.saveprofile= functions.https.onCall((data) =>{

    var userid = data.userid;
    var name =data.name;
    var type = data.type;
    var birthday = data.birthday;
    var city = data.city;
    var zip = data.zip;
    var street = data.street;
    var number =data.number;
    var db = admin.database();

try{
    var ref = db.ref("users").child(type).child(userid).child("name").set(name);
    var ref = db.ref("users").child(type).child(userid).child("birthday").set(birthday);
    var ref = db.ref("users").child(type).child(userid).child("address").child("city").set(city);
    var ref = db.ref("users").child(type).child(userid).child("address").child("zip").set(zip.toString());
    var ref = db.ref("users").child(type).child(userid).child("address").child("street").set(street);
    var ref = db.ref("users").child(type).child(userid).child("address").child("number").set(number);
    return 'OK';
}
catch(e){
    return e.message.toString();
}
}
);

exports.getProfile = functions.https.onCall((data) => {

    var userid = data.userid;

    var db = admin.database()

    return db.ref('users').once('value').then((snap) => {
        if(snap.child("Student").hasChild(userid)){
            var details = {
                type:"Student",
                name : snap.child("Student").child(userid).child('name').val(),
                birthday : snap.child("Student").child(userid).child('birthday').val(),
                city : snap.child("Student").child(userid).child("address").child("city").val(),
                zip : snap.child("Student").child(userid).child("address").child("zip").val(),
                street : snap.child("Student").child(userid).child("address").child("street").val(),
                number : snap.child("Student").child(userid).child("address").child("number").val(),
            }
            
            return details;
        }
        else {
            if(snap.child("Teacher").hasChild(userid)){
                var details = {
                    type:"Teacher",
                    name : snap.child("Teacher").child(userid).child('name').val(),
                    birthday : snap.child("Teacher").child(userid).child('birthday').val(),
                    city : snap.child("Teacher").child(userid).child("address").child("city").val(),
                    zip : snap.child("Teacher").child(userid).child("address").child("zip").val(),
                    street : snap.child("Teacher").child(userid).child("address").child("street").val(),
                    number : snap.child("Teacher").child(userid).child("address").child("number").val(),
                }
                
                return details;
            }
            else{
                return {
                    name: "Error"
                }
        }
        }
    });
});

exports.getPersons= functions.https.onCall((data) => {

    var type=data.type;
    var db = admin.database();
    
    return db.ref("users").child(type).once("value").then(function(snapshot){
        var list=[];
        snapshot.forEach(function(childSnapshot){
             var data= {
                 name : childSnapshot.child("name").val(),
                 personId: childSnapshot.key
             }
            list.push(data);
        });
        return list;
    });
});


exports.getPersonsSubjects= functions.https.onCall((data) => {
    var db = admin.database();
    
    return db.ref("PeoplesSubjects").once("value").then(function(snapshot){
        var list=[];
        snapshot.forEach(function(childSnapshot){
             var data= {
                subjectId: childSnapshot.child("subjectId").val(),
                peopleId: childSnapshot.child("peopleId").val()
             }
            list.push(data);
        });
        return list;
    });
});


exports.saveSubject= functions.https.onCall((data) =>{

    var name =data.name;
    var subjectId = data.subjectId;
    var Description = data.Description;
    var teacherid= data.teacherId;
    var db = admin.database();

try{
    var ref = db.ref("Subjects").child(subjectId).child("Name").set(name);
    var ref = db.ref("Subjects").child(subjectId).child("Description").set(Description);
    var ref = db.ref("Subjects").child(subjectId).child("Teacher").set(teacherid);
    return 'OK';
}
catch(e){
    return e.message.toString();
}
}
);


exports.getSubjects = functions.https.onCall((data) => {

    var db = admin.database();
    
    return db.ref("Subjects").once("value").then(function(snapshot){
        var list=[];
        snapshot.forEach(function(childSnapshot){
             var data= {
                 name : childSnapshot.child("Name").val(),
                 Description : childSnapshot.child("Description").val(),
                 subjectId: childSnapshot.key,
                 teacherid : childSnapshot.child("Teacher").val()
             }
            list.push(data);
        });
        return list;
    });
});





exports.saveLesson= functions.https.onCall((data) =>{

    var db = admin.database();

try{
    var ref = db.ref();
    ref.update(data);
}
catch(e){
    return e.message.toString();
    }
 return 'OK';
});



exports.getLesson = functions.https.onCall((data) => {

    var db = admin.database();
    
    return db.ref("Lessons").once("value").then(function(snapshot){
        var list=[];
        snapshot.forEach(function(childSnapshot){
             var data= {
                 end : childSnapshot.child("end").val(),
                 begin: childSnapshot.child("begin").val(),
                 subjectId: childSnapshot.child("subjectId").val(),
                 id : childSnapshot.key
             }
            list.push(data);
        });
        if(list.length==0){
            list.add(data={
                id :"" ,
                end: "",
                begint: ""    
            })
        }
        return list;
    });
});

exports.savePersonSubject= functions.https.onCall((data) =>{

    var subjectId = data.subjectId;
    var userId = data.userId;
    var id=data.subjectId.concat(data.userId);
    var db = admin.database();

try{
    var ref = db.ref("PeoplesSubjects").child(id).child("peopleId").set(userId)
    ref = db.ref("PeoplesSubjects").child(id).child("subjectId").set(subjectId);
    return 'OK';
}
catch(e){
    return e.message.toString();
}
}
);


exports.saveGrade = functions.https.onCall((data) =>{

    var subjectId = data.subjectId;
    var studentId = data.studentId;
    var teacherId= data.teacherId;
    var grade = data.grade;
    var comment = data.comment;
    var date = data.date;
    var gradeId = data.gradeId;
    var db = admin.database();

try{

    var ref = db.ref("Grades").child(gradeId).child("grade").set(grade.toString());
    var ref = db.ref("Grades").child(gradeId).child("studentId").set(studentId);
    var ref = db.ref("Grades").child(gradeId).child("teacherId").set(teacherId);
    var ref = db.ref("Grades").child(gradeId).child("subjectId").set(subjectId);
    var ref = db.ref("Grades").child(gradeId).child("comment").set(comment);
    var ref = db.ref("Grades").child(gradeId).child("date").set(date);
    return 'OK';
}
catch(e){
    return e.message.toString();
}
}
);

exports.getGrades= functions.https.onCall((data) => {

    var db = admin.database();
    
    return db.ref("Grades").once("value").then(function(snapshot){
        var list=[];
        snapshot.forEach(function(childSnapshot){
             var data= {
                subjectId : childSnapshot.child("subjectId").val(),
                studentId: childSnapshot.child("studentId").val(),
                teacherId: childSnapshot.child("teacherId").val(),
                gradeId: childSnapshot.key,
                grade: childSnapshot.child("grade").val(),
                date: childSnapshot.child("date").val(),
                comment: childSnapshot.child("comment").val()
             }
            list.push(data);
        });
        return list;
    });
});

exports.saveTalking= functions.https.onCall((data) =>{

    var userId2 = data.userId2;
    var userId1 = data.userId1;
    var Id= data.Id;
    var lastMessage = data.lastMessage
    var db = admin.database();

try{
    var ref = db.ref("Talkings").child(Id).child("userId1").set(userId1);
    var ref = db.ref("Talkings").child(Id).child("userId2").set(userId2);
    var ref = db.ref("Talkings").child(Id).child("lastMessage").set(lastMessage);
    return 'OK';
}
catch(e){
    return e.message.toString();
}
}
);

exports.getTalkings= functions.https.onCall((data) => {

    var db = admin.database();
    
    return db.ref("Talkings").once("value").then(function(snapshot){
        var list=[];
        snapshot.forEach(function(childSnapshot){
             var data= {
                talkingId : childSnapshot.key.toString(),
                lastMessage: childSnapshot.child("lastMessage").val(),
                userId1: childSnapshot.child("userId1").val(),
                userId2: childSnapshot.child("userId2").val()
             }
            list.push(data);
        });
        return list;
    });
});

exports.saveMessage= functions.https.onCall((data) =>{

    var sender = data.sender;
    var Id= data.Id;
    var talkingId= data.talkingId
    var date= data.date
    var message = data.message
    var db = admin.database();

try{
    var ref = db.ref("Messages").child(Id).child("sender").set(sender);
    var ref = db.ref("Messages").child(Id).child("message").set(message);
    var ref = db.ref("Messages").child(Id).child("date").set(date);
    var ref = db.ref("Messages").child(Id).child("talkingId").set(talkingId);
    return 'OK';
}
catch(e){
    return e.message.toString();
}
}
);

exports.getMessages= functions.https.onCall((data) => {

    var db = admin.database();
    
    return db.ref("Messages").once("value").then(function(snapshot){
        var list=[];
        snapshot.forEach(function(childSnapshot){
             var data= {
                messageId : childSnapshot.key.toString(),
                talkingId : childSnapshot.child("talkingId").val().toString(),
                message: childSnapshot.child("message").val(),
                date: childSnapshot.child("date").val(),
                senderId: childSnapshot.child("sender").val()
             }
            list.push(data);
        });
        return list;
    });
});








