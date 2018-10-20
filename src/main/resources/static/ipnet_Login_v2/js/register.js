function getLength(str){
    return str.replace(/[^\x00-xff]/g,"xx").length;  //\x00-xff 此区间是单子节 ，除了此区间都是双字节。
}
function findStr(str,n){
    var tmp=0;
    for(var i=0;i<str.length;i++){
        if(str.charAt(i)==n){
            tmp++;
        }
    }
    return tmp;
}

var n_msg = "";
var d_msg = "";
var p_msg = "";
var n_valid = 0;
var d_valid = 0;
var p_valid = 0;
var reqMap = {};

var oUsername = "";
var oPassword = "";
var oRole = "";

window.onload= function(){ tabRegisterPerson(0); }

function tabRegisterPerson(num){
    oUsername = "";
    oPassword = "";
    oRole = "";

    var aInput=document.getElementsByTagName('input');
    var oName=aInput[0];  //这里用户名为手机号
    var dx=aInput[2];     //aInput[1]为显示“获取验证码”的位置
    var pwd=aInput[3];

    var aP=document.getElementsByTagName('p');
    var name_msg=aP[0];
    var dx_msg=aP[0];
    var pwd_msg=aP[0];
    //var pwd2_msg=aP[0];
    var name_length=0;
    var send=document.getElementById('send');

    var times = 60;
    var timer = null;

    var all_msg = aP[0];
    n_msg = "";
    d_msg = "";
    p_msg = "";
    all_msg.innerHTML="";

    n_valid = 0;
    d_valid = 0;
    p_valid = 0;
    reqMap = {};

    function getVerificationCode(tele){
        var result = false;
        $.ajax({
            url: "/user/getCode",
            type: "POST",
            async: false,
            dataType: "json",
            data: {telephone: tele},
            success: function (data) {
                if (data != null){
                    result = true;
                    reqMap["time"] = data["time"];
                    reqMap["hash"] = data["hash"];
                    return result;
                }
                else{
                    //alert("data is null");
                }
            },
            error: function () {
                result = false;
                //alert("post error");
            }
        });

        return result;
    }
    send.onclick=function(){
        // 计时开始
        if(n_valid == 1){
            var result = getVerificationCode(oName.value);
            if(result == true){
                timer = setInterval(djs,1000);
            }
            else{
                alert("发送失败");
            }
        }
        else{
            all_msg.innerHTML = "请先输入正确的手机号" + "<br /><br />";
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


    //用户名检测

    oName.onfocus=function(){
        oName.removeAttribute("placeholder");
    }
    oName.onblur=function(){
        // 含有非法字符 ，不能为空 ，长度少于5个字符 ，长度大于25个字符
        var tel = /^1[3|4|5|7|8][0-9]\d{8}$/;
        n_valid = 0;
        if(this.value==""){
            n_msg = "手机号不可为空";
            all_msg.innerHTML=n_msg + "<br /><br />";
        }
        else if(!tel.test(this.value)){
            n_msg = "手机号不正确";
            all_msg.innerHTML=n_msg + "<br /><br />";
        }
        else{
            //oName.style.border='1px solid #fff';
            n_valid = 1;
            reqMap["phoneNum"] = this.value;
            n_msg = "";
            all_msg.innerHTML=n_msg + d_msg + p_msg + "<br /><br />";
        }

    }

    //短信验证码检测（暂不作检测）
    dx.onfocus=function(){
        //dx_msg.style.display='block';
        dx.removeAttribute("placeholder");
        //dx.style.border='1px solid #fff';

        d_valid = 0;
        d_msg = "";

    }
    dx.onblur=function(){
        d_valid = 1;
        reqMap["code"] = this.value;
    }

    //密码检测
    pwd.onfocus=function(){
        if(n_valid == 0){
            if(oName.value == ""){
                n_msg = "手机号不可为空";
            }
            all_msg.innerHTML = n_msg + "<br /><br />";
        }
        pwd.removeAttribute("placeholder");
    }
    pwd.onblur=function(){
        var m=findStr(pwd.value,pwd.value[0]);
        var re_n=/[^\d]/g;
        var re_t=/[^a-zA-Z]/g;
        p_valid = 0;
        if(n_valid == 0){
            all_msg.innerHTML = n_msg + "<br /><br />";
        }else if(this.value==""){
            p_msg = "密码不可为空";
            all_msg.innerHTML=n_msg + d_msg + p_msg + "<br /><br />";
        }/*else if(m==this.value.length){
            pwd_msg.innerHTML="密码不可使用相同的字符" + "<br /><br /><br />";
            //pwd.style.border='1px solid red';
        }*/else if(this.value.length<6 || this.value.length>16){
            p_msg = "密码长度应为6到16个字符";
            all_msg.innerHTML=n_msg + d_msg + p_msg + "<br /><br />";
        }else if(!re_n.test(this.value)){
            p_msg = "密码不能全为数字";
            all_msg.innerHTML=n_msg + d_msg + p_msg + "<br /><br />";
        }else if(!re_t.test(this.value)){
            p_msg = "密码不能全为字母";
            all_msg.innerHTML=n_msg + d_msg + p_msg + "<br /><br />";
        }else{
            p_valid = 1;
            reqMap["pass"] = this.value;
            p_msg = "";
            all_msg.innerHTML=n_msg + d_msg + p_msg + "<br /><br />";
        }

    }

    //确认密码
    /*pwd2.onblur=function(){
        if(this.value!=pwd.value){
            pwd2_msg.innerHTML='<i></i>两次密码输入到不一致';
            pwd.style.border='1px solid red';
        }else if(this.value==""){
            pwd2_msg.innerHTML='<i></i>请输入密码';
            pwd.style.border='1px solid red';
        }else{
            pwd2.style.border='1px solid #fff';
        }
    }*/

}


function registerMsg() {
    //1-调用后端的方法验证（待定）
    //2-若验证为真，则提示注册成功，跳转到登录后的主页；若为假，则提示失败原因（待定）

    //alert("call registerMsg");
    var content = "";
    if(n_valid == 1 && d_valid == 1 && p_valid == 1){
        //alert("all valid");
        //alert("req[code]" + reqMap["code"]);

        $.ajax({
            url: "/user/verify",
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(reqMap),
            success: function (data) {
                if (data == "Success"){
                    content = "注册成功！即将跳转 . . .";
                    TINY.box.show(content,0,0,0,0,2);
                    setTimeout(function () {
                        window.location.href = "/ipnet/login";  //跳转到登录界面
                    },2000);
                }
                else if(data == "Timeout"){
                    content = "超时！再试一次 . . .";
                    TINY.box.show(content,0,0,0,0,3);
                }
                else if(data == "CodeError"){
                    content = "验证码错误！再试一次 . . .";
                    TINY.box.show(content,0,0,0,0,3);
                }
                else {
                    content = "未知错误！再试一次 . . .";
                    TINY.box.show(content,0,0,0,0,3);
                }
            },
            error: function () {
                content = "请求失败！再试一次 . . .";
                TINY.box.show(content,0,0,0,0,3);
            }
        });

    }
    else{
        alert("sth invalid");
        content = "注册失败！请重试 . . .";
        TINY.box.show(content,0,0,0,0,3);
    }
}

// ? function : 手机号已注册时，提示并可选择跳转到登录界面


function tabRegisterEnterprise() {
    n_msg = "";
    d_msg = "";
    p_msg = "";
    n_valid = 0;
    d_valid = 0;
    p_valid = 0;
    reqMap = {};

    var aInput=document.getElementsByTagName('input');
    var oName=aInput[4];
    var pwd=aInput[5];

    var aP=document.getElementsByTagName('p');
    var name_msg=aP[1];
    var pwd_msg=aP[1];
    var name_length=0;

    var all_msg = aP[1];
    n_msg = "";
    p_msg = "";
    all_msg.innerHTML="";

    n_valid = 0;
    p_valid = 0;

    //用户名检测
    oName.onfocus=function(){
        oName.removeAttribute("placeholder");
    }

    oName.onblur=function(){
        var tel = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/;
        n_valid = 0;
        if(this.value==""){
            n_msg = "邮箱不可为空";
            all_msg.innerHTML=n_msg + "<br /><br />";
        }
        else if(!tel.test(this.value)){
            n_msg = "邮箱不正确";
            all_msg.innerHTML=n_msg + "<br /><br />";
        }
        else{
            n_valid = 1;
            oUsername = this.value;
            n_msg = "";
            all_msg.innerHTML=n_msg + p_msg + "<br /><br />";
        }

    }

    //密码检测
    pwd.onfocus=function(){
        if(n_valid == 0){
            if(oName.value == ""){
                n_msg = "邮箱不可为空";
            }
            all_msg.innerHTML = n_msg + "<br /><br />";
        }
        pwd.removeAttribute("placeholder");
    }
    pwd.onblur=function(){
        var m=findStr(pwd.value,pwd.value[0]);
        var re_n=/[^\d]/g;
        var re_t=/[^a-zA-Z]/g;
        p_valid = 0;
        if(n_valid == 0){
            all_msg.innerHTML = n_msg + "<br /><br />";
        }else if(this.value==""){
            p_msg = "密码不可为空";
            all_msg.innerHTML=n_msg + p_msg + "<br /><br />";
        }/*else if(m==this.value.length){
            pwd_msg.innerHTML="密码不可使用相同的字符" + "<br /><br /><br />";
            //pwd.style.border='1px solid red';
        }*/else if(this.value.length<6 || this.value.length>16){
            p_msg = "密码长度应为6到16个字符";
            all_msg.innerHTML=n_msg + p_msg + "<br /><br />";
        }else if(!re_n.test(this.value)){
            p_msg = "密码不能全为数字";
            all_msg.innerHTML=n_msg + p_msg + "<br /><br />";
        }else if(!re_t.test(this.value)){
            p_msg = "密码不能全为字母";
            all_msg.innerHTML=n_msg + p_msg + "<br /><br />";
        }else{
            p_valid = 1;
            oPassword = this.value;
            p_msg = "";
            all_msg.innerHTML=n_msg + p_msg + "<br /><br />";
        }

    }
}

function checkRadioInput() {
    var type = document.getElementsByName("enterpriseType");
    for (var i = 0; i < type.length; i++) {
        if (type[i].checked) {
            //出现选中项
            oRole = type[i].value;
            return true;
        }
    }

    return false;
}


function registerMsgEnterprise() {
    //1-调用后端的方法验证（待定）
    //2-若验证为真，则提示注册成功，跳转到登录后的主页；若为假，则提示失败原因（待定）

    var content = "";
    var isChecked = checkRadioInput();

    if(n_valid == 1 && p_valid == 1 && isChecked == true){
        var emailRegister = {};
        emailRegister.username = oUsername;
        emailRegister.password = oPassword;
        emailRegister.role = oRole;

        $.ajax({
            url: "/user/emailRegister",
            type: "POST",
            async: false,
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(emailRegister),
            success: function (data) {
                if (data == "Success"){

                    content = "发送链接成功，请前往邮箱确认！即将跳转 . . .";
                    TINY.box.show(content,0,0,0,0,2);
                    setTimeout(function () {
                        window.location.href = "/ipnet/login_byEmail";  //跳转到登录界面
                    },2000);

                    //确认激活 ...

                }
                else if(data == "Exist"){
                    content = "链接已存在，请前往邮箱确认！即将跳转 . . .";
                    TINY.box.show(content,0,0,0,0,2);
                    setTimeout(function () {
                        window.location.href = "/ipnet/login_byEmail";  //跳转到登录界面
                    },2000);
                }
                else {
                    content = "未知错误！再试一次 . . .";
                    TINY.box.show(content,0,0,0,0,3);
                }
            },
            error: function () {
                content = "请求失败！再试一次 . . .";
                TINY.box.show(content,0,0,0,0,3);
            }
        });

    }
    else if(n_valid == 1 && p_valid == 1 && isChecked == false){
        content = "请选择注册类型！";
        TINY.box.show(content,0,0,0,0,3);
    }
    else{
        content = "注册失败！请重试 . . .";
        TINY.box.show(content,0,0,0,0,3);
    }
}