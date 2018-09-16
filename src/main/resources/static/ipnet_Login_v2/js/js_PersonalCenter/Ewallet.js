window.onload = function () {
    if(!window.localStorage){
        alert("浏览器不支持localStorage");
        return false;
    } else{   //主逻辑业务
        var storage = window.localStorage;
        var userID = storage.getItem("user_id");
        console.log(userID);
        showBankCardList(userID);
        showPoint(userID);
    }
}

function showPoint(userID) {
    $.ajax({
        url: "/userInfo/getPoint",
        type: "POST",
        async: false,
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

function showBankCardList(userID) {
    $.ajax({
        url: "/userInfo/getCreditCardInfo",
        type: "POST",
        async: false,
        dataType: "json",
        data: {userId: userID},
        success: function (data) {
            if(data != null && data.length != 0){
                //console.log(data);
                var dlist = data;
                var str = "";
                str = "<span class=\"t2\"><table class=\"tb-12\"><tr><td class=\"tb-12-head\">银行卡卡号</td><td class=\"tb-12-head\">所在银行</td><td class=\"tb-12-head\">操作</td></tr><tr>";
                for(var i = 0;i < dlist.length ;i++){
                    str = str + "<td>" + dlist[i].cardId + "</td><td>" + dlist[i].bank + "</td><td>" +
                        "<a href=\"#\" id=\"btnCancel_" + dlist[i].cardId + "_" + dlist[i].bank + "\" onclick=\"cancelBankCard(this.id)\" style=\"color: #38a4f0;border-bottom: 1px solid #38a4f0;\">解绑</a></td>";
                }
                str = str + "</tr></table></span>";
                document.getElementById("Ew_bankCardInfoList").innerHTML = str;
            }
            else{
                document.getElementById("Ew_bankCardInfoList").innerHTML = "<span style=\"font-size: small; color: #9f9f9f\">&nbsp;&nbsp;&nbsp;&nbsp;暂未绑定任何银行卡</span>";
            }
        },
        error: function () {
            alert("Error!");
        }
    });
}

function cancelBankCard(btnID) {
    btnID = (btnID + "").substring((btnID + "").indexOf("-") + 1);
    var userID = (btnID + "").substring(0, (btnID + "").indexOf("-"));
    var cardID = (btnID + "").substring((btnID + "").indexOf("-") + 1);

    $.ajax({
        url: "/userInfo/cancelCreditCard",
        type: "POST",
        async: false,
        dataType: "json",
        data: {userId: userID, card: cardID},
        success: function (data) {      //data为Resultmessage : ???
            if(data == "Success"){
                alert("解除绑定成功！");
                window.location.href = "/ipnet/pc_eWallet";   //相当于刷新？
            }
            else{
                alert("解除绑定失败！");
            }
        }, error: function () {
            alert("Error!");
        }
    });
}

function newBankCard() {
    var storage = window.localStorage;
    var userID = storage.getItem("user_id");
    var cardID = document.getElementById("Ew_newBankCard_ID");
    var cardCode = document.getElementById("Ew_newBankCard_code");
    //var bankName = document.getElementById("Ew_newBankCard_bank");
    var bankName = "未选择";
    var bankNameList = document.getElementsByName("bankNameList");
    for(var i = 0;i < bankNameList.length;i++){
        if(bankNameList[i].selected){
            bankName = bankNameList[i].value;
            break;
        }
    }

    if(bankName == "未选择"){
        alert("尚未选择开户银行！");
    }
    else{
        $.ajax({
            url: "/userInfo/setCreditCard",
            type: "POST",
            async: false,
            dataType: "json",
            data: {userId: userID, card: cardID, card_code: cardCode},
            success: function (data) {
                if(data == "Success"){
                    alert("绑定成功！");
                    window.location.href = "/ipnet/pc_eWallet";
                }
                else{
                    alert("绑定失败！");
                }
            },
            error: function () {
                alert("Error!");
            }
        });
    }
}

function cancelNewBankCard() {

}

/*function showBalance(userID) {
    //alert("call showBalance");

    $.ajax({
        url: "/userInfo/getAccountBalance",
        type: "POST",
        async: false,
        dataType: "json",
        data: {userId: userID},
        success: function (data) {
            document.getElementById("Ew_balance").innerHTML = "<span class=\"t1\"><i><img class=\"imgMid\" src=\"/ipnet_Login_v2/images/balance.png\" alt=\"Balance\" height=\"10%\" width=\"10%\"></i>" + data + "</span>";
        },
        error: function () {
            alert("Error!");
        }
    });
}*/

/*function showBankCardIdList() {
    $.ajax({
        url: "/userInfo/getAllAccountId",
        type: "POST",
        async: false,
        dataType: "json",
        data: {userId: userID},
        success: function (data) {
            if(data != null && data.length != 0){
                //console.log(data);
                var dlist = data;
                var str = "";
                str = "<span class=\"t2\"><table class=\"tb-12\"><tr><td class=\"tb-12-head\">银行卡卡号</td><td class=\"tb-12-head\">所在银行</td><td class=\"tb-12-head\">操作</td></tr><tr>";
                for(var i = 0;i < dlist.length ;i++){
                    str = str + "<td>" + dlist[i].cardId + "</td><td>" + dlist[i].bank + "</td><td>" +
                        "<a href=\"#\" id=\"btnCancel_" + dlist[i].cardId + "_" + dlist[i].bank + "\" onclick=\"cancelBankCard(this.id)\" style=\"color: #38a4f0;border-bottom: 1px solid #38a4f0;\">解绑</a></td>";
                }
                str = str + "</tr></table></span>";
                document.getElementById("Ew_bankCardIdList").innerHTML = str;
            }
            else{
                document.getElementById("Ew_bankCardIdList").innerHTML = "<span style=\"font-size: small; color: #9f9f9f\">&nbsp;&nbsp;&nbsp;&nbsp;暂未绑定任何银行卡</span>";
            }
        },
        error: function () {
            alert("Error!");
        }
    });
}*/
