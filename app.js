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
	    var d = new Date();
            var _date = d.getTime();
            var mime = "";
            if(pic.files[x].name.split(".").length>0){
                mime = "." + pic.files[x].name.split(".")[1];
            }
	        var name = _date.toString()+mime;
            var storageRef = storage.ref();
            var picRef = storageRef.child(name); 
            picRef.put(pic.files[x]).then(function(snapshot){
                if(snapshot.state!="success"){
                    flag = 1;
                }
            });
            
            urls.push(name);
        }
        if(flag == 0){
            firebaseWrite(urls);

        }
        else{alert("Image Upload failed");}

        
    
}
});
