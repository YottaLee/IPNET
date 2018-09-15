
function setEmailAddress() {
    var storage = window.localStorage;
    var userID = storage.getItem("user_id");
    var email = document.getElementById("email");

    if(email == "" || email == null){
        document.getElementById("msg_email").innerHTML = "<p style='color: #9f9f9f; font-size: small;'>尚未设置邮箱地址</p>";
    }
    else{
        $.ajax({
            url: "/userInfo/setEmail",
            type: "POST",
            async: false,
            dataType: "json",
            data: {userId: userID, email: email},
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