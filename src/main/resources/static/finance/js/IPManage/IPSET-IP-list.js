
var storage = window.localStorage;
var patentPoolID = storage.getItem("patentPoolID");
//通过id获取pool的相关信息
$.ajax({
    type: "GET",
    url: "/PatentPool/searchPatentPoolByID",
    async: false,
    data: {patentPoolID: patentPoolID},
    success: function (data) {
        document.getElementById("poolName").innerHTML = data.name;
        document.getElementById("poolId").innerHTML = data.id;

    },
    error: function (error) {
         alert(error);
         console.log(error);
    }
});

//获取专利列表
$.ajax({
    type: "GET",
    url: "/Patent/searchPatentByPool",
    async: false,
    data: {poolId: patentPoolID},
    success: function (data) {
        document.getElementById("IPSum").innerHTML = data.length;
        var patentList = "";
        for (var i = 0, len = data.length; i < len; i++) {
            patentList += "<tr>\n" +
                "                                                    <td><a class=\"btn-link\" href=\"#\">"+data[i].patent_id +"</a></td>\n" +
                "                                                    <td>"+data[i].patent_name+"</td>\n" +
                "                                                    <td><span class=\"text-muted\"><i class=\"demo-pli-clock\"></i> 2014.01.10</span></td>\n" +
                "                                                    <td>2015.01.10</td>\n" +
                "                                                    <td>\n" +
                "                                                        <div class=\"label label-table label-success\">正常</div>\n" +
                "                                                    </td>\n" +
                "                                                    <td>"+data[i].userId+"</td>\n" +
                "                                                     <td>\n" +
                "                                                        <button class= \"btn btn-warning btn-rounded \">投入市场</button>\n" +
                "                                                      </td>\n" +
                "                                                </tr>";
        }
        $('#ipset_ip').append(patentList);
        //document.getElementById("ipset_list").innerHTML = patentPoolList;

    },
    error: function () {
         alert("Network warning for posting the purpose of the loan")
    }
});




$.ajax({
    type: "GET",
    url: "/Patent/searchRelatedPatents",
    async: false,
    success: function (data) {
        var patentList = "";
        for (var i = 0, len = data.length; i < len; i++) {
            patentList += "<a href=\"#\" class=\" list-group-item\">\n" +
                "                                            <div class=\"media-left pos-rel\">\n" +
                "                                                <img class=\"img-circle img-xs\" src=\"/finance/img/profile-photos/2.png\" alt=\"Profile Picture\">\n" +
                "                                                <i class=\"badge badge-success badge-stat badge-icon pull-left\"></i>\n" +
                "                                            </div>\n" +
                "                                            <div class=\"media-body\">\n" +
                "                                                <p  id=\"patent-" + data[i].patent_id + "\"onclick=\"invite(this.id)\"class=\"mar-no\">"+data[i].patent_id+"</p>\n" +
                "                                                <small class=\"text-muted\">"+data[i].patent_name+"</small>\n" +
                "                                            </div>\n" +
                "                                        </a>";//enum
        }
        document.getElementById("related-ip-list").innerHTML = patentList;

    },
    error: function () {
        // alert("Network warning for posting the purpose of the loan")
    }
});

$('.btn-rounded').click(function(){
    //提交请求
    console.log("测试button")
});

$("#send").on('click',function () {
    var invitedIP = $("#invitedIP").val();
    var postData = {
        "patentId":invitedIP,
        "patentPoolId":patentPoolID
    };

    $.ajax({
        url: "/Patent/sendInvitationFromPool",
        type: "POST",
        data : postData,
        async: false,
        success: function (data, status) {

            alertFile("已向该专利持有人发送邀请！");

            console.log("我成功了");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("传输失败");
        }
    });
});

function invite(patentID) {
    console.log("success");
    patentID = (patentID + "").substring((patentID + "").indexOf("-") + 1);
    $("#invitedIP").val(patentID);
}

