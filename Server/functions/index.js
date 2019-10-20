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
