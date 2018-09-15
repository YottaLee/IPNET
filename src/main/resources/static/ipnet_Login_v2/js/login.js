var n_msg = "";
var p_msg = "";
var d_msg = "";
var n_valid = 0;
var p_valid = 0;
var isVertifySucc = false;  //var d_valid = 0;
var oUsername = "";
var oPassword = "";

window.onload = function () {
    var aInput = document.getElementsByTagName('input');
    var oUser = aInput[0];
    var oPwd = aInput[1];

    var aP = document.getElementsByTagName('p');
    var all_msg = aP[0];
    n_msg = "";
    p_msg = "";
    d_msg = "";
    n_valid = 0;
    p_valid = 0;

    /*var loginCon_btn = document.getElementById("loginConfirm_btn");
    loginCon_btn.disabled = true;  //对于button */

    //用户名检测

    oUser.onfocus = function () {
        oUser.removeAttribute("placeholder");
    }

    oUser.onkeyup = function () {

    }

    oUser.onblur = function () {
        var tel = /1[3|4|5|7|8][0-9]\d{8}$/;
        n_valid = 0;
        if (this.value == "") {
            n_msg = "手机号不可为空";
            all_msg.innerHTML = n_msg + "<br /><br />";
        } else if (!tel.test(this.value)) {
            n_msg = "手机号不正确  ";
            all_msg.innerHTML = n_msg + "<br /><br />";
        } else {
            n_valid = 1;
            oUsername = this.value;
            n_msg = "";
            all_msg.innerHTML = n_msg + "<br /><br />";
        }

        /*if(n_valid == 1 && p_valid == 1){
            loginCon_btn.disabled = false;
        }
        else{
            loginCon_btn.disabled = true;
        }*/
    }

    //密码检测

    oPwd.onfocus = function () {
        if (n_valid == 0) {
            if (oUser.value == "") {
                n_msg = "手机号不可为空";
            }
            all_msg.innerHTML = n_msg + "<br /><br />";
        }
        oPwd.removeAttribute("placeholder");
    }
    oPwd.onblur = function () {
        if (n_valid == 0) {
            all_msg.innerHTML = n_msg + "<br /><br />";
        }
        else if (n_valid == 1 && this.value == "") {
            p_msg = "密码不可为空";
            all_msg.innerHTML = p_msg + "<br /><br />";
        }
        else {
            p_valid = 1;
            oPassword = this.value;
            p_msg = "";
            all_msg.innerHTML = n_msg + p_msg + "<br /><br />";
        }
        //oPwd.setAttribute("placeholder");
        //oPwd.style.placeholder='请输入确认密码';

        /*if(n_valid == 1 && p_valid == 1){
            loginCon_btn.disabled = false;
        }
        else{
            loginCon_btn.disabled = true;
        }*/
    }

    /*var loginCon_btn = document.getElementById("loginConfirm-btn");
    loginCon_btn.addEventListener("click",loginConfirm);
    function loginConfirm() {}*/

    //滑动验证部分
    var dragContainer = document.getElementById("dragContainer");
    var dragBg = document.getElementById("dragBg");
    var dragText = document.getElementById("dragText");
    var dragHandler = document.getElementById("dragHandler");

    //滑块最大偏移量
    var maxHandlerOffset = dragContainer.clientWidth - dragHandler.clientWidth;
    //是否验证成功的标记
    isVertifySucc = false;
    initDrag();

    function initDrag() {
        dragText.textContent = "拖动滑块验证";
        dragHandler.addEventListener("mousedown", onDragHandlerMouseDown);

        dragHandler.addEventListener("touchstart", onDragHandlerMouseDown);
    }

    function onDragHandlerMouseDown(event) {
        document.addEventListener("mousemove", onDragHandlerMouseMove);
        document.addEventListener("mouseup", onDragHandlerMouseUp);

        document.addEventListener("touchmove", onDragHandlerMouseMove);
        document.addEventListener("touchend", onDragHandlerMouseUp);
    }

    function onDragHandlerMouseMove(event) {
        /*
        html元素不存在width属性,只有clientWidth
        offsetX是相对当前元素的,clientX和pageX是相对其父元素的

        touches：表示当前跟踪的触摸操作的touch对象的数组。
        targetTouches：特定于事件目标的Touch对象的数组。
    　　changedTouches：表示自上次触摸以来发生了什么改变的Touch对象的数组。
        */
        var left = (event.clientX || event.changedTouches[0].clientX) - dragHandler.clientWidth / 2;
        if (left < 0) {
            left = 0;
        } else if (left > maxHandlerOffset) {
            left = maxHandlerOffset;
            verifySucc();
        }
        dragHandler.style.left = left + "px";
        dragBg.style.width = dragHandler.style.left;
    }

    function onDragHandlerMouseUp(event) {
        document.removeEventListener("mousemove", onDragHandlerMouseMove);
        document.removeEventListener("mouseup", onDragHandlerMouseUp);

        document.removeEventListener("touchmove", onDragHandlerMouseMove);
        document.removeEventListener("touchend", onDragHandlerMouseUp);

        dragHandler.style.left = 0;
        dragBg.style.width = 0;
    }

    //验证成功
    function verifySucc() {
        /*if(n_valid == 1 && p_valid == 1){ }*/
        isVertifySucc = true;
        dragText.textContent = "验证通过";
        dragText.style.color = "white";
        dragHandler.setAttribute("class", "dragHandlerOkBg");

        dragHandler.removeEventListener("mousedown", onDragHandlerMouseDown);
        document.removeEventListener("mousemove", onDragHandlerMouseMove);
        document.removeEventListener("mouseup", onDragHandlerMouseUp);

        dragHandler.removeEventListener("touchstart", onDragHandlerMouseDown);
        document.removeEventListener("touchmove", onDragHandlerMouseMove);
        document.removeEventListener("touchend", onDragHandlerMouseUp);
    };

}

function loginMsg() {
    //1-调用后端的方法验证（待定）
    //2-若验证为真，则跳转到登录后的主页；若为假，则提示失败原因（待定）

    var content = "";
    if (n_valid == 1 && p_valid == 1 && isVertifySucc == true) {
        /*loginreq.username = oUsername;
        loginreq.password = oPassword;*/
        var loginreq = {
            username: oUsername,
            password: oPassword
        };

        if(!window.localStorage){
            alert("浏览器不支持localStorage");
        } else{
            var storage=window.localStorage;

            //写入a字段 storage["a"]=1;  console.log(typeof storage["a"]);
            //写入b字段 storage.b=1;  console.log(typeof storage["b"]);
            //写入c字段 storage.setItem("c",3);  console.log(typeof storage["c"]);
            //第一种方法读取 var a=storage.a;  console.log(a);
            //第二种方法读取 var b=storage["b"];  console.log(b);
            //第三种方法读取 var c=storage.getItem("c");  console.log(c);
            storage.setItem("user_id",oUsername);
        }

        $.ajax({
            url: "/user/phoneLogin",
            type: "POST",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(loginreq),
            success: function (data) {
                if (data == "PersonLogin") {
                    content = "登录成功！即将跳转 . . .";
                    TINY.box.show(content, 0, 0, 0, 0, 2);
                    setTimeout(function () {
                        //跳转到登录后的主页（待定）
                        window.location.href = "/ipnet/home";
                    }, 2000);
                }
                else if (data == "NoUser") {
                    content = "用户不存在！再试一次 . . .";
                    TINY.box.show(content, 0, 0, 0, 0, 3);
                }
                else if (data == "PassError") {
                    content = "密码错误！再试一次 . . .";
                    TINY.box.show(content, 0, 0, 0, 0, 3);
                }
                else {
                    content = "未知错误！再试一次 . . .";
                    TINY.box.show(content, 0, 0, 0, 0, 3);
                }
            },
            error: function () {
                content = "请求失败！再试一次 . . .";
                TINY.box.show(content, 0, 0, 0, 0, 3);
            }
        });

    }
    else {
        content = "登录失败！再试一次 . . .";
        TINY.box.show(content, 0, 0, 0, 0, 3);
    }
}

//跳转界面
/*function jump(userId) {
    $.ajax({
        url: "user/getUserRole",
        dataType: 'json',
        type: 'GET',
        data: {
            userID: userId
        },
        success: function (role) {
            switch (role) {
                case 0:
                    window.location.href = "/ipnet/home";//企业
                    break;
                case 1:
                    window.location.href = "/ipnet/home";//个人
                    break;
                case 2:
                    window.location.href = "/ipnet/Evaluation-IP-list";//评估机构
                    break;
                case 3:
                    window.location.href = "/ipnet/Bank-IP-list";//金融机构
                    break;
                case 4:
                    window.location.href = "/ipnet/Insurance-IP-lsit";//保险公司
                    break;
                default:
                    window.location.href = "/ipnet/home";//默认
                    break;
            }
        },
        error: function () {

        }
    });
}*/
