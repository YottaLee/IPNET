window.onload = function () {
    if(!window.localStorage){
        alert("浏览器不支持localStorage");
        return false;
    } else{   //主逻辑业务
        var storage = window.localStorage;
        var userID = storage.getItem("userid");
        console.log(userID);
        showBalance(userID);
        //showPoint("");
    }
}

function showBalance(userID) {
    //alert("call showBalance");

    $.ajax({
        url: "/userInfo/getAccountBalance",
        type: "POST",
        dataType: "json",
        data: {userId: userID},
        success: function (data) {
            document.getElementById("Ew_balance").innerHTML = "<span class=\"t1\"><i><img class=\"imgMid\" src=\"/ipnet_Login_v2/images/balance.png\" alt=\"Balance\" height=\"10%\" width=\"10%\"></i>" + data + "</span>";
        },
        error: function () {
            alert("Error!");
        }
    });
}

function showPoint(userID) {
    $.ajax({
        url: "/userInfo/getPoint",
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        data: {userId: userID},
        success: function (data) {
            document.getElementById("Ew_point").innerHTML = "<i><img class=\"imgMid\" src=\"/ipnet_Login_v2/images/icon-gift.jpg\" alt=\"Gift\" height=\"5%\" width=\"5%\" style=\"margin-left: 8px;margin-right: 5px\"></i><span class=\"t1\" style=\"margin-left: 5px;\">" + data + "积分</span>";
        },
        error: function () {
            alert("Error!");
        }
    });
}