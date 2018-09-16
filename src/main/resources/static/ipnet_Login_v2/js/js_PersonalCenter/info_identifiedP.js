window.onload = function () {
    var storage = window.localStorage;
    var userID = storage.getItem("user_id");
    //showAccountInfo(userID);
    showPersonInfo(userID);
}

function showAccountInfo(userID) {
    $.ajax({
        url: "/userInfo/getAccount",
        type: "POST",
        async: false,
        dataType: "json",
        data: {userId: userID},
        success: function (data) {
            var accountInfoVo = data;
            if(data != null){
                /*document.getElementById("name").innerHTML = "姓名：" + data["name"];
                document.getElementById("phone").innerHTML = "手机号：" + data["phone"];
                document.getElementById("company").innerHTML = "所在企业：" + data["company"];
                document.getElementById("region").innerHTML = "所在地区：" + data["region"];
                document.getElementById("gender").innerHTML = "性别：" + data["gender"];
                document.getElementById("profession").innerHTML = "从事行业：" + data["profession"];
                document.getElementById("statement").innerHTML = "自我描述：" + data["statement"];*/
            }
        },
        error: function () {
            alert("Error!");
        }
    });
}

function showPersonInfo(userID) {
    $.ajax({
        url: "/userInfo/getUser",
        type: "POST",
        async: false,
        dataType: "json",
        data: {userid: userID},
        success: function (data) {
            var userInfoVo = data;
            if(data != null){
                document.getElementById("name").innerHTML = "姓名：" + data["name"];
                document.getElementById("phone").innerHTML = "手机号：" + data["phone"];
                document.getElementById("company").innerHTML = "所在企业：" + data["company"];
                document.getElementById("region").innerHTML = "所在地区：" + data["region"];
                document.getElementById("gender").innerHTML = "性别：" + data["gender"];
                document.getElementById("profession").innerHTML = "从事行业：" + data["profession"];
                document.getElementById("statement").innerHTML = "自我描述：" + data["statement"];
            }
        },
        error: function () {
            alert("Error!");
        }
    });
}