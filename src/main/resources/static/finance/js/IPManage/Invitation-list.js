//获取专利列表
var storage = window.localStorage;
var userId = storage.getItem("user_id");
console.log(userId);
$.ajax({
    type: "GET",
    url: "/Patent/receiveInvitation",
    data: { userId: userId},
    success: function (data) {
        console.log(data);
        var invitationList = "";
        for (var i = 0, len = data.length; i < len; i++) {
            item= data[i].patentId+data[i].patentPoolId;
            invitationList += "<tr > "+
                "                                                    <td >" + data[i].patentPoolId + "</td>\n" +
                "                                                    <td id="+item+"> "+ "<button  class=\"btn btn-success\" onclick=\"accept(this.id)\">接受</button>\n" + "</td>\n" +
                "                                                    <td id="+item+"> "+ "<button  class=\"btn btn-success\" onclick=\"deny(this.id)\">拒绝</button>\n" + "</td>\n" +
                "                                                </tr>";


        }

        $('#invitation_list').append(invitationList);
        // document.getElementById("ipset_info_list").innerHTML = ipsetinfolist;

    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});


function accept(id) {

    patentID = (id + "").substring((patentID + "").indexOf("-") + 1);
    $.ajax({
        // 判断一下该专利是否有评估结果，没有就跳转到评估报告申请界面
        type: "POST",
        url: "/Patent/acceptInvitationFromPool",
        data: {
            patentID: patentID,
            patentPoolId:patentPoolId

        },
        success: function (data) {
            console.log("接受成功")
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }
    });
}

function deny(patentID, patentPoolId) {
    patentID = (patentID + "").substring((patentID + "").indexOf("-") + 1);
    evaluationNoTransfer(patentID);
}