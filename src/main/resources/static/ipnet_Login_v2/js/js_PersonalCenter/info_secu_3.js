var n_valid = 0;
var oPhoneNumber = "";

window.onload = function () {
    verifyPhoneNumber();
}

function verifyPhoneNumber() {
    var phone = document.getElementById("phone");
    var verification = document.getElementById("verification");
    var send=document.getElementById("send");
    var msg = document.getElementById("msg_setPhone");

    n_valid = 0;

    var times = 60;
    var timer = null;

    function getVerificationCode(tele){
        var result = false;
        $.ajax({
            url: "/user/getVerification",
            type: "POST",
            async: false,
            dataType: "json",
            data: {phone: tele},
            success: function (data) {
                if (data == "Success"){
                    result = true;
                    return result;
                }
                else{
                    alert("发送失败！");
                }
            },
            error: function () {
                result = false;
            }
        });

        return result;
    }
    send.onclick=function(){
        // 计时开始
        if(n_valid == 1){
            var result = getVerificationCode(oPhoneNumber.value);
            if(result == true){
                timer = setInterval(djs,1000);
            }
            else{
                alert("发送失败");
            }
        }
        else{
            msg.innerHTML = "<p style='color: #9f9f9f; font-size: small;'>请先输入正确的手机号</p>";
        }
    }
    function djs(){
        send.value = times + "秒后重试";
        send.setAttribute('disabled','disabled');
        //send.style.background='#ccc';
        //send.style.border='1px solid #ccc';
        times--;
        if(times <= 0){
            send.value = "获取验证码";
            send.removeAttribute('disabled');
            //send.style.background= '#ffffff';
            times = 60;
            clearInterval(timer);
        }
    }

    //手机号检测
    /*phone.onfocus = function(){}*/
    phone.onblur=function(){
        // 含有非法字符 ，不能为空 ，长度少于5个字符 ，长度大于25个字符
        var tel = /^1[3|4|5|7|8][0-9]\d{8}$/;
        n_valid = 0;
        if(this.value==""){
            msg.innerHTML = "<p style='color: #9f9f9f; font-size: small;'>手机号不可为空</p>";
        }
        else if(!tel.test(this.value)){
            msg.innerHTML = "<p style='color: #9f9f9f; font-size: small;'>手机号不正确</p>";
        }
        else{
            n_valid = 1;
            oPhoneNumber = this.value;
        }

    }
}

function confirmSetPhoneNumber() {
    var storage = window.localStorage;
    var userID = storage.getItem("user_id");
    //var phone = document.getElementById("phone");
    var verification = document.getElementById("verification");

    if(verification == "" || verification == null){
        document.getElementById("msg_email").innerHTML = "<p style='color: #9f9f9f; font-size: small;'>尚未完成信息输入</p>";
    }
    else{
        $.ajax({
            url: "/userInfo/setPhone",
            type: "POST",
            async: false,
            dataType: "json",
            data: {userId: userID, phone: oPhoneNumber, verification:verification},
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