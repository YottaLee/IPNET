window.onload = function () {
    if (!window.localStorage) {
        alert("浏览器不支持localStorage");
        return false;
    } else {   //主逻辑业务
        var storage = window.localStorage;
        var userID = storage.getItem("user_id");
        $.ajax({
            type: "GET",
            url: "/user/getUserRole",
            async: false,
            data: {
                userID: userID
            },
            success: function (userRole) {
                console.log(userRole);
                if(userRole != "PersonalUser")
                    userRole = "CompanyUser";
                showBankCardList(userID, userRole);
                showPoint(userID, userRole);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
            }

        });

    }
}


function showPoint(userID, userRole) {
    $.ajax({
        url: "/userInfo/getPoint",
        type: "POST",
        async: false,
        dataType: "json",
        data: {userId: userID, userType: userRole},
        success: function (data) {
            document.getElementById("Ew_point").innerHTML = "<i><img class=\"imgMid\" src=\"/ipnet_Login_v2/images/icon-gift.jpg\" alt=\"Gift\" height=\"5%\" width=\"5%\" style=\"margin-left: 8px;margin-right: 5px\"></i><span class=\"t1\" style=\"margin-left: 5px;\">" + data + "积分</span>";
        },
        error: function () {
            alert("Error!");
        }
    });
}

function showBankCardList(userID, userRole) {

    $.ajax({
        url: "/userInfo/getCreditCardInfo",
        type: "POST",
        async: false,
        dataType: "json",
        data: {userId: userID, userType: userRole},
        success: function (data) {
            if (data != null && data.length != 0) {
                //console.log(data);
                var dlist = data;
                var str = "";
                str = "<span class=\"t2\"><table class=\"tb-12\"><tr><td class=\"tb-12-head\">银行卡卡号</td><td class=\"tb-12-head\">所在银行</td><td class=\"tb-12-head\">操作</td></tr><tr>";
                for (var i = 0; i < dlist.length; i++) {
                    str = str + "<td>" + dlist[i].cardId + "</td><td>" + dlist[i].bank + "</td><td>" +
                        "<a href=\"#\" id=\"btnCancel_" + dlist[i].cardId + "_" + dlist[i].bank + "\" onclick=\"cancelBankCard(this.id)\" style=\"color: #38a4f0;border-bottom: 1px solid #38a4f0;\">解绑</a></td>";
                }
                str = str + "</tr></table></span>";
                document.getElementById("Ew_bankCardInfoList").innerHTML = str;
            }
            else {
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
            if (data == "Success") {
                alert("解除绑定成功！");
                window.location.href = "/ipnet/pc_eWallet";   //相当于刷新？
            }
            else {
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
    var cardID = $("#Ew_newBankCard_ID").val();
    console.log(cardID);
    var cardCode = $("#Ew_newBankCard_code").val();
    //var bankName = document.getElementById("Ew_newBankCard_bank");
    var bankName = $("#bankNameList").val();
    console.log("bank_name: "+bankName);
    // var bankNameList = document.getElementsByName("bankNameList");
    // for(var i = 0;i < bankNameList.length;i++){
    //     if(bankNameList[i].selected){
    //         bankName = bankNameList[i].value;
    //         break;
    //     }
    // }
    $.ajax({
        type: "GET",
        url: "/user/getUserRole",
        async: false,
        data: {
            userID: userID
        },
        success: function (userRole) {
            if (bankName == "未选择") {
                alert("尚未选择开户银行！");
            }
            else {
                console.log(userRole);
                if(userRole != "PersonalUser")
                    userRole = "CompanyUser";
                $.ajax({
                    url: "/userInfo/setCreditCard",
                    type: "POST",
                    async: false,
                    dataType: "json",
                    data: {userId: userID, card: cardID, card_code: cardCode, bank: bankName, userType: userRole},
                    success: function (data) {
                        if (data == "Success") {
                            walletInBlockChain(userID);
                            alert("绑定成功！");
                            window.location.href = "/ipnet/pc_eWallet";
                        }
                        else {
                            alert("绑定失败！");
                        }
                    },
                    error: function () {
                        alert("Error!");
                    }
                });
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }

    });


}

function cancelNewBankCard() {

}

function walletInBlockChain(id) {
    var amount = 2000000000;
    var type = "";
    $.ajax({
        type: "GET",
        url: "/user/getUserRole",
        async: false,
        data: {
            userID: id
        },
        success: function (userRole) {
            console.log(userRole);
            type = userRole;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }

    });
    switch (type) {
        case "Financial":
            $.ajax({
                url: "http://120.79.232.126:3000/api/Bank",
                type: "GET",
                dataType: "json", //指定服务器返回的数据类型
                data: {
                   id:id
                },
                success: function (data) {
                    $.ajax({
                        url: "http://120.79.232.126:3000/api/Bank",
                        type: "PUT",
                        dataType: "json", //指定服务器返回的数据类型
                        data: {
                            $class: "org.acme.ipregistry.Bank",
                            id: id,
                            name: data.name,
                            balance: amount
                        },
                        success: function (data) {
                            console.log(data);
                        },
                        error: function () {
                            console.log("Fail!!!!!!!!");
                        }
                    });
                },
                error: function () {
                    console.log("Fail!!!!!!!!");
                }
            });
            break;
        case "Insurance":
            $.ajax({
                url: "http://120.79.232.126:3000/api/InsuranceCompany",
                type: "GET",
                dataType: "json", //指定服务器返回的数据类型
                data: {
                    id:id
                },
                success: function (data) {
                    $.ajax({
                        url: "http://120.79.232.126:3000/api/InsuranceCompany",
                        type: "PUT",
                        dataType: "json", //指定服务器返回的数据类型
                        data: {
                            $class: "org.acme.ipregistry.InsuranceCompany",
                            id: id,
                            name: data.name,
                            balance: amount
                        },
                        success: function (data) {
                            console.log(data);
                        },
                        error: function () {
                            console.log("Fail!!!!!!!!");
                        }
                    });
                },
                error: function () {
                    console.log("Fail!!!!!!!!");
                }
            });
            break;
        case "Evaluator":
            $.ajax({
                url: "http://120.79.232.126:3000/api/Notary",
                type: "GET",
                dataType: "json", //指定服务器返回的数据类型
                data: {
                    id:id
                },
                success: function (data) {
                    $.ajax({
                        url: "http://120.79.232.126:3000/api/Notary",
                        type: "PUT",
                        dataType: "json", //指定服务器返回的数据类型
                        data: {
                            $class: "org.acme.ipregistry.Notary",
                            id: id,
                            name: data.name,
                            balance: amount
                        },
                        success: function (data) {
                            console.log(data);
                        },
                        error: function () {
                            console.log("Fail!!!!!!!!");
                        }
                    });
                },
                error: function () {
                    console.log("Fail!!!!!!!!");
                }
            });
            break;
        case "CompanyUser":
            $.ajax({
                url: "http://120.79.232.126:3000/api/IPEstateAgent",
                type: "GET",
                dataType: "json", //指定服务器返回的数据类型
                data: {
                    id:id
                },
                success: function (data) {
                    $.ajax({
                        url: "http://120.79.232.126:3000/api/IPEstateAgent",
                        type: "PUT",
                        dataType: "json", //指定服务器返回的数据类型
                        data: {
                            
                            $class: "org.acme.ipregistry.IPEstateAgent",
                            id: id,
                            name: data.name,
                            balance: amount
                        },
                        success: function (data) {
                            console.log(data);
                        },
                        error: function () {
                            console.log("Fail!!!!!!!!");
                        }
                    });
                },
                error: function () {
                    console.log("Fail!!!!!!!!");
                }
            });
            break;
        case "PersonalUser":
            $.ajax({
                url: "http://120.79.232.126:3000/api/PrivateIndividual",
                type: "GET",
                dataType: "json", //指定服务器返回的数据类型
                data: {
                    id:id
                },
                success: function (data) {
                    $.ajax({
                        url: "http://120.79.232.126:3000/api/PrivateIndividual",
                        type: "PUT",
                        dataType: "json", //指定服务器返回的数据类型
                        data: {
                            $class: "org.acme.ipregistry.PrivateIndividual",
                            id: id,
                            name: data.name,
                            balance: amount
                        },
                        success: function (data) {
                            console.log(data);
                        },
                        error: function () {
                            console.log("Fail!!!!!!!!");
                        }
                    });
                },
                error: function () {
                    console.log("Fail!!!!!!!!");
                }
            });
            break;
    }

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
