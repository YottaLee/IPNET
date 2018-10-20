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
var p_msg = "";
var n_valid = 0;
var p_valid = 0;
var oUsername = "";
var oPassword = "";
var oRole = "";


function tabRegisterEmail(num){
    var aInput=document.getElementsByTagName('input');  //默认为 num = 0 的情况
    var oName=aInput[0];
    var pwd=aInput[1];

    var aP=document.getElementsByTagName('p');
    var name_msg=aP[0];
    var pwd_msg=aP[0];
    var name_length=0;

    var all_msg = aP[0];
    n_msg = "";
    p_msg = "";
    all_msg.innerHTML="";

    n_valid = 0;
    p_valid = 0;

    //num = 1 和 num = 2 的情况
    if(num == 1){
        oName=aInput[2];
        pwd=aInput[3];

        name_msg=aP[1];
        pwd_msg=aP[1];

        all_msg = aP[1];
    }else if(num == 2){
        oName=aInput[4];
        pwd=aInput[5];

        name_msg=aP[1];
        pwd_msg=aP[1];

        all_msg = aP[1];
    }

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


function registerMsgByEmailEnterprise() {
    //1-调用后端的方法验证（待定）
    //2-若验证为真，则提示注册成功，跳转到登录后的主页；若为假，则提示失败原因（待定）

    //alert("call registerMsgByEmailEnterprise");
    var content = "";
    var isChecked = checkRadioInput();

    if(n_valid == 1 && p_valid == 1 && isChecked == true){
        var emailRegister = {};
        emailRegister.username = oUsername;
        emailRegister.password = oPassword;
        emailRegister.role = oRole;
        //alert(emailRegister.username + ", " + emailRegister.password + ", " + emailRegister.role);

        $.ajax({
            url: "/user/emailRegister",
            type: "POST",
            async: false,
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(emailRegister),
            success: function (data) {
                if (data == "Success"){
                    registerMailParticipant(oUsername,oRole);
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

function registerMsgByEmailPerson() {
    //1-调用后端的方法验证（待定）
    //2-若验证为真，则提示注册成功，跳转到登录后的主页；若为假，则提示失败原因（待定）

    //alert("call registerMsgByEmailPerson");
    var content = "";

    if(n_valid == 1 && p_valid == 1){
        var emailRegister = {};
        emailRegister.username = oUsername;
        emailRegister.password = oPassword;
        emailRegister.role = "PersonalUser";

        $.ajax({
            url: "/user/emailRegister",
            type: "POST",
            async: false,
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(emailRegister),
            success: function (data) {
                if (data == "Success"){
                    registerMailParticipant(id,emailRegister.role);
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
    else{
        content = "注册失败！请重试 . . .";
        TINY.box.show(content,0,0,0,0,3);
    }
}


// ? function : 邮箱已注册时，提示并可选择跳转到登录界面
