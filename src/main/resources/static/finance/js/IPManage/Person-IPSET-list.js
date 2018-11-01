//获取专利列表
var storage = window.localStorage;
var userId = storage.getItem("user_id");
$.ajax({
    type: "GET",
    url: "/PatentPool/getIPSETList",
    data: { userId: userId},
    success: function (data) {
        console.log(data);
        document.getElementById("ipsetNum").innerHTML = data.length;
        var ipsetList = "";
        var ipsetinfolist = "";
        for (var i = 0, len = data.length; i < len; i++) {
            ipsetList += "<a href=\"#\" class=\"list-group-item\">\n" +
                "                                            <div class=\"media-left pos-rel\">\n" +
                "                                                     <img class=\"img-circle img-xs\" src=\"/finance/img/profile-photos/2.png\" alt=\"Profile Picture\">\n" +
                "                                                <i class=\"badge badge-success badge-stat badge-icon pull-left\"></i>\n" +
                "                                            </div>\n" +
                "                                            <div class=\"media-body\">\n" +
                "                                                <p class=\"mar-no\">" + data[i].name + "</p>\n" +
                "                                                <small class=\"text-muted\">" + data[i].industry + "</small>\n" +
                "                                            </div>\n" +
                "                                        </a>";
            ipsetinfolist += "<tr  id=\"ipset-" + data[i].id+"\"onclick=\"checkDetail(this.id)\">\n" +
                "                                                    <td><a class=\"btn-link\" >" + data[i].id + "</a></td>\n" +
                "                                                    <td>" + data[i].name + "</td>\n" +
                "                                                    <td><span class=\"text-muted\"><i class=\"demo-pli-clock\"></i>" + data[i].createTime + "</span>\n" +
                "                                                    </td>\n" +
                // "                                                    <td>2015.01.01</td>\n" +
                "                                                    <td>" + data[i].patents.length + "</td>\n" +
                // "                                                    <td>\n" +
                // "                                                        <div class=\"label label-table label-success\">正常</div>\n" +
                // "                                                    </td>\n" +
                "\n" +
                "                                                </tr>";

        }
        document.getElementById("ipset_list").innerHTML = ipsetList;
        $('#ipset_info_list').append(ipsetinfolist);
        // document.getElementById("ipset_info_list").innerHTML = ipsetinfolist;

    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});
$.ajax({
    type: 'GET',
    url: '/user/getUserRole',
    data: {userID: userId},
    async: false,
    success: function (userType) {
        $.ajax({
            type: "GET",
            url: "/userInfo/getUser",
            data: {
                userid: userId,
                userType:userType
            },
            success: function (data) {
                document.getElementById("user").innerHTML = data.name;
                document.getElementById("personinfo").innerHTML = data.statement;
                document.getElementById("credit").innerHTML = data.credit;
                document.getElementById("profession").innerHTML = data.profession;
                document.getElementById("country").innerHTML = data.region;
                document.getElementById("imgURL").innerHTML = data.IDcard_img;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
            }
        });
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});



$("#poolsummit").on('click',function () {
    var name = $('#poolName').val();   //名称
    var creater = userId// 创建者
    var industry = $('#industry').val();  // 相关领域
    var intro = $('#brief-intro').val();  //简单介绍
    var date = new Date();
    var nowMonth = date.getMonth() + 1;
    var strDate = date.getDate();
    var seperator = "-";
    if (nowMonth >= 1 && nowMonth <= 9) {
        nowMonth = "0" + nowMonth;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var nowDate = date.getFullYear() + seperator + nowMonth + seperator + strDate; // 日期
    var invitedIP = $("#invitedIP").val();
    var IPPool = $("#IPPool").val();
    console.log(invitedIP,IPPool);

    var postData = {
        "poolName":name,
        "holderId":creater,
        "region":industry,
        "profile":intro,
        "date":nowDate
    };

    $.ajax({
        url: "/PatentPool/createPatentPool",
        type: "POST",
        data : postData,
        async: false,
        success: function (data, status) {
            console.log("我成功了");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("传输失败");
        }
    });
    console.log(nowDate);

});

function checkDetail(patentPoolID){
    patentPoolID = (patentPoolID + "").substring((patentPoolID + "").indexOf("-") + 1);
    storage.setItem('patentPoolID', patentPoolID);
    window.location.href = "/ipnet/IPSET-IP-list";
}
