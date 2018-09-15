window.onload = function () {
    var storage = window.localStorage;
    var userID = storage.getItem("user_id");
    isEmailSet(userID);
    isPhoneSet(userID);
}

function isEmailSet(userID) {
    $.ajax({
        url: "/userInfo/isEmailValidate",
        type: "POST",
        async: false,
        dataType: "json",
        data: {userId: userID},
        success: function (data) {
            if(data == "Success"){
                document.getElementById("btnSetEmail").innerHTML = "<a class=\"btn-colLeft\" href=\"javascript:void(0);\" style=\"background-color: #757575; border: none;\"><span style=\"color: #FFFFFF\">已绑定</span></a>";
            }
        },
        error: function () {
            alert("Error!");
        }
    });
}

function isPhoneSet(userID) {
    $.ajax({
        url: "/userInfo/isPhone",
        type: "POST",
        async: false,
        dataType: "json",
        data: {userId: userID},
        success: function (data) {
            if(data == "Success"){
                document.getElementById("btnSetPhone").innerHTML = "<a class=\"btn-colLeft\" href=\"javascript:void(0);\" style=\"background-color: #757575; border: none;\"><span style=\"color: #FFFFFF\">已绑定</span></a>";
            }
        },
        error: function () {
            alert("Error!");
        }
    });
}

