
function modifyPassword() {
    var storage = window.localStorage;
    var userID = storage.getItem("user_id");
    var pwd = document.getElementById("login_code");
    var pwdsec = document.getElementById("login_code_sec");

    if(pwd == "" || pwd == null || pwdsec == "" || pwdsec == null){
        document.getElementById("msg_pwdSet").innerHTML = "<p style='color: #9f9f9f; font-size: small;'>尚未完成密码重置步骤</p>";
    }
    else if(pwd != pwdsec){
        document.getElementById("msg_pwdSet").innerHTML = "<p style='color: #9f9f9f; font-size: small;'>输入的密码不一致</p>";
    }
    else{
        $.ajax({
            url: "/userInfo/setPassword",
            type: "POST",
            async: false,
            dataType: "json",
            data: {userId: userID, password: pwd},
            success: function (data) {
                if(data == "Success"){
                    alert("修改成功！");
                    window.location.href = "/ipnet/pc_info_security";
                }
                else{
                    alert("修改失败！");
                }
            },
            error: function () {
                alert("Error!");
            }
        });
    }
}