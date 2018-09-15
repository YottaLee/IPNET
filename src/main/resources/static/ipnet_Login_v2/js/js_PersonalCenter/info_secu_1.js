
function modifyNickname() {
    var storage = window.localStorage;
    var userID = storage.getItem("user_id");
    var nickname = document.getElementById("nickname");

    if(nickname == "" || nickname == null){
        document.getElementById("msg_nickname").innerHTML = "<p style='color: #9f9f9f; font-size: small;'>尚未设置用户名</p>";
    }
    else{
        $.ajax({
            url: "/userInfo/setUsername",
            type: "POST",
            async: false,
            dataType: "json",
            data: {userId: userID, username: nickname},
            success: function (data) {
                if(data == "Success"){
                    alert("设置成功！");
                    window.location.href = "/ipnet/pc_info_security";
                }
                else{
                    alert("设置失败！");
                }
            },
            error: function () {
                alert("Error!");
            }
        });
    }
}