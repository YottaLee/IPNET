var n_msg="";
var p_msg="";
var d_msg = "";
var n_valid = 0;
var p_valid = 0;
var isVertifySucc = false;  //var d_valid = 0;
var oUsername = "";
var oPassword = "";

window.onload=function(){
    loginByEmail();
}
function loginByEmail(){
    var aInput=document.getElementsByTagName('input');
    var oUser=aInput[0];
    var oPwd=aInput[1];

    var aP=document.getElementsByTagName('p');
    var all_msg=aP[0];
    n_msg="";
    p_msg="";
    n_valid = 0;
    p_valid = 0;


    //用户名检测
    oUser.onfocus=function(){
        oUser.removeAttribute("placeholder");
    }
    oUser.onkeyup=function(){

    }
    oUser.onblur=function(){
        var tel = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/;
        n_valid = 0;
        if(this.value==""){
            n_msg = "邮箱不可为空";
            all_msg.innerHTML=n_msg + "<br /><br />";
        }else if(!tel.test(this.value)){
            n_msg = "邮箱不正确  ";
            all_msg.innerHTML=n_msg + "<br /><br />";
        }else{
            n_valid = 1;
            oUsername = this.value;
            n_msg = "";
            all_msg.innerHTML=n_msg + "<br /><br />";
        }
    }


    //密码检测
    oPwd.onfocus=function(){
        if(n_valid == 0){
            if(oUser.value == ""){
                n_msg = "邮箱不可为空";
            }
            all_msg.innerHTML = n_msg + "<br /><br />";
        }
        oPwd.removeAttribute("placeholder");
    }
    oPwd.onblur=function(){
        if(n_valid == 0){
            all_msg.innerHTML = n_msg + "<br /><br />";
        }
        else if(n_valid == 1 && this.value==""){
            p_msg = "密码不可为空";
            all_msg.innerHTML = p_msg + "<br /><br />";
        }
        else{
            p_valid = 1;
            oPassword = this.value;
            p_msg = "";
            all_msg.innerHTML=n_msg + p_msg + "<br /><br />";
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

    slidVerification();

}

function slidVerification() {
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
        isVertifySucc = false;
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
        if(left < 0) {
            left = 0;
        } else if(left > maxHandlerOffset) {
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

function loginMsgEmail(){
    //1-调用后端的方法验证（待定）
    //2-若验证为真，则跳转到登录后的主页；若为假，则提示失败原因（待定）

    var content = "";
    if(n_valid == 1 && p_valid == 1 && isVertifySucc == true){
        var loginreq = {
            username: oUsername,
            password: oPassword
        };

        if(!window.localStorage){
            alert("浏览器不支持localStorage");
        } else{
            var storage=window.localStorage;
            storage.setItem("user_id",oUsername);
        }

        $.ajax({
            url: "/user/emailLogin",
            type: "POST",
            async: false,
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(loginreq),
            success: function (data) {
                if (data == "PersonLogin"){
                    content = "登录成功！即将跳转 . . .";
                    TINY.box.show(content,0,0,0,0,2);
                    setTimeout(function () {
                        //跳转到登录后的主页（待定）
                        window.location.href = "/ipnet/home";
                    },2000);
                }
                else if(data == "CompanyLogin"){
                    content = "登录成功！即将跳转 . . .";
                    TINY.box.show(content,0,0,0,0,2);
                    setTimeout(function () {
                        //跳转到登录后的主页（待定）
                        window.location.href = "/ipnet/home";
                    },2000);
                }
                else if(data == "EvaluatorLogin"){
                    content = "登录成功！即将跳转 . . .";
                    TINY.box.show(content,0,0,0,0,2);
                    setTimeout(function () {
                        //跳转到登录后的主页（待定）
                        window.location.href = "/ipnet/Evaluation-IP-list";
                    },2000);
                }
                else if(data == "FinancialLogin"){
                    content = "登录成功！即将跳转 . . .";
                    TINY.box.show(content,0,0,0,0,2);
                    setTimeout(function () {
                        //跳转到登录后的主页（待定）
                        window.location.href = "/ipnet/Bank-IP-list";
                    },2000);
                }
                else if(data == "InsuranceLogin"){
                    content = "登录成功！即将跳转 . . .";
                    TINY.box.show(content,0,0,0,0,2);
                    setTimeout(function () {
                        //跳转到登录后的主页（待定）
                        window.location.href = "/ipnet/Insurance-IP-list";
                    },2000);
                }
                else if(data == "NoUser"){
                    content = "用户不存在！请前往注册 . . .";
                    TINY.box.show(content,0,0,0,0,2);
                    setTimeout(function () {
                        window.location.href = "/ipnet/register_byEmail";
                    },2000);
                }
                else if(data == "PassError"){
                    content = "密码错误！再试一次 . . .";
                    TINY.box.show(content,0,0,0,0,3);
                }
                else if(data == "NotActive"){
                    content = "请前往激活邮箱后再试一次 . . .";
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
        content = "登录失败！再试一次 . . .";
        TINY.box.show(content,0,0,0,0,3);
    }
}
