$(document).ready(function(){
    $("#submit").click(function(){
        imageUpload();
    });
        // Initialize Firebase
          var config = {
            apiKey: "AIzaSyCCOjnLbML2FXx-YRcK7_uVORWSMYmtFAc",
            authDomain: "ayb1-b0261.firebaseapp.com",
            databaseURL: "https://ayb1-b0261.firebaseio.com",
            projectId: "ayb1-b0261",
            storageBucket: "ayb1-b0261.appspot.com",
            messagingSenderId: "700631630670"
          };
          firebase.initializeApp(config);
        var database = firebase.database();
        var storage = firebase.storage();
    function firebaseWrite(URLS){
        title = document.getElementById("title").value;
        caseType = document.getElementById("caseType").value;
        description = document.getElementById("description").value;
        alert("HelloWorld");
        var _case = {
            Title: title,
            CaseType: caseType,
            Description: description,
            urls: URLS
        };
        var newPostKey = firebase.database().ref().child('cases').push().key;
        var update={};
        update['/cases/'+newPostKey]= _case;
        firebase.database().ref().update(update);
        alert("Done");
        document.getElementById("title").value="";
        document.getElementById("caseType").value="";
        document.getElementById("description").value="";
    }
    var pic = document.getElementById("pic1");
    function imageUpload()
    {
        var flag = 0;
        var urls =[];
        for(var x=0;x<pic.files.length;x++){
            var storageRef = storage.ref();
            var picRef = storageRef.child(pic.files[x].name); 
            picRef.put(pic.files[x]).then(function(snapshot){
                if(snapshot.state!="success"){
                    flag = 1;
                }
            });
            
            urls.push(pic.files[x].name);
        }
        if(flag == 0){
            firebaseWrite(urls);

        }
        else{alert("Image Upload failed");}

        
    
}
});

//function write()
//{
//    // Initialize Firebase
//      var config = {
//        apiKey: "AIzaSyCCOjnLbML2FXx-YRcK7_uVORWSMYmtFAc",
//        authDomain: "ayb1-b0261.firebaseapp.com",
//        databaseURL: "https://ayb1-b0261.firebaseio.com",
//        projectId: "ayb1-b0261",
//        storageBucket: "ayb1-b0261.appspot.com",
//        messagingSenderId: "700631630670"
//      };
//      firebase.initializeApp(config);
//
//    var database = firebase.database();
//    title = document.getElementById("title").value;
//    caseType = document.getElementById("caseType").value;
//    description = document.getElementById("description").value;
//    alert("HelloWorld");
//    var _case = {
//        Title: title,
//        CaseType: caseType,
//        Description: description
//    };
//    var newPostKey = firebase.database().ref().child('cases').push().key;
//    var update={};
//    update['/cases/'+newPostKey]= _case;
//    firebase.database().ref().update(update);
//    alert("Done");
//  }
//$document.ready(function(){
//    $("#submit").click(function(){
//        
//    });
//})