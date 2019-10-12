const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();

exports.register= functions.https.onCall((data) =>{

    var userid = data.userid;
    var name = data.name;
    var type = data.type;
    var db = admin.database();

try{
    var ref = db.ref("users").child(userid).child("name").set(name);
    ref = db.ref("users").child(userid).child("type").set(type);
    return 'OK';
}
catch(e){
    return e.message.toString();
}
}
);
