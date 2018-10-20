
var storage = window.localStorage;
var patentPoolID = storage.patentPoolID;
//通过id获取pool的相关信息
$.ajax({
    type: "GET",
    url: "/PatentPool/searchPatentPoolByID",
    async: false,
    data: {patentPoolID: patentPoolID},
    success: function (data) {
        alert(1);
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
    dataType: "json",
    data: patentPoolID,
    success: function (data) {
        var patentList = "";
        for (var i = 0, len = data.length; i < len; i++) {
            patentList += "<tr>\n" +
                "                                                    <td><a class=\"btn-link\" href=\"#\">"+data[i].id +"</a></td>\n" +
                "                                                    <td>"+data[i].name+"</td>\n" +
                "                                                    <td><span class=\"text-muted\"><i class=\"demo-pli-clock\"></i> 2014.01.10</span></td>\n" +
                "                                                    <td>2015.01.10</td>\n" +
                "                                                    <td>\n" +
                "                                                        <div class=\"label label-table label-success\">正常</div>\n" +
                "                                                    </td>\n" +
                "                                                    <td>ln</td>\n" +
                "                                                </tr>";
        }
        document.getElementById("ipset_list").innerHTML = patentPoolList;

    },
    error: function () {
        // alert("Network warning for posting the purpose of the loan")
    }
});




$.ajax({
    type: "GET",
    url: "Patent/searchRelatedPatents",
    dataType: "json",
    success: function (data) {
        var patentList = "";
        for (var i = 0, len = data.length; i < len; i++) {
            patentList += "<a href=\"#\" class=\"list-group-item\">\n" +
                "                                            <div class=\"media-left pos-rel\">\n" +
                "                                                <img class=\"img-circle img-xs\" src=\"img/profile-photos/2.png\" alt=\"Profile Picture\">\n" +
                "                                                <i class=\"badge badge-success badge-stat badge-icon pull-left\"></i>\n" +
                "                                            </div>\n" +
                "                                            <div class=\"media-body\">\n" +
                "                                                <p class=\"mar-no\">"+data[i].patent_name+"</p>\n" +
                "                                                <small class=\"text-muted\">"+data[i].patent_type+"</small>\n" +
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

$('.btn-mint').click(function(){
    var ip =$('#invitedIP').val();
    var ippool =$('#IPPool').val();

    $.ajax({
        type: "POST",
        url: "Patent/sendInvitationFromPool",
        dataType: "json",
        data:{
            patentId:ip,
            patentPoolId:ippool
        },
        success: function (data) {
            console.log("happy");
        },
        error: function () {
            // alert("Network warning for posting the purpose of the loan")
        }
    });
});
