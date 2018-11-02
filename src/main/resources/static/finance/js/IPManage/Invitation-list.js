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
            str = data[i].patentId;
            item=str+"-"+ data[i].patentPoolId ;
            // alert(item);
            invitationList += "<tr id="+item+"> "+
                "                                                    <td >" + data[i].patentId + "</td>\n" +
                "                                                    <td >" + data[i].patentPoolId + "</td>\n" +
                "                                                    <td > "+ "<button id=\"accept-"+item +"\"  class=\"btn btn-success\" onclick=\"accept(this.id)\">接受</button>\n" + "</td>\n" +
                "                                                    <td > "+ "<button id=\"deny-"+ item +"\" class=\"btn btn-success\" onclick=\"deny(this.id)\">拒绝</button>\n" + "</td>\n" +
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
    console.log(id);
    // alert(id);
    patentID = (id + "").split("-")[1];
    patentPoolID = (id + "").split("-")[2];
    $.ajax({
        type: "POST",
        url: "/Patent/acceptInvitationFromPool",
        data: {
            patentId: patentID,
            patentPoolId:patentPoolID

        },
        async: false,
        success: function (data) {
            console.log("接受成功")
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }
    });
}

function deny(id) {
    patentID = (id + "").split("-")[1];
    patentPoolID = (id + "").split("-")[2];
    $.ajax({
        type: "POST",
        url: "/Patent/denyInvitationFromPool",
        data: {
            patentId: patentID,
            patentPoolId:patentPoolID

        },
        async: false,
        success: function (data) {
            console.log("拒绝成功")
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }
    });
}