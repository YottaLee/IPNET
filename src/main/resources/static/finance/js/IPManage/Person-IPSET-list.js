//获取专利列表
var storage = window.localStorage;
var userId = storage.userId;
$.ajax({
    type: "GET",
    url: "PatentPool/getIPSETList",
    dataType: "json",
    data: userId,
    success: function (data) {
        document.getElementById("ipsetNum").innerHTML = str(data.length);
        var ipsetList = "";
        var ipsetinfolist = "";
        for (var i = 0, len = data.length; i < len; i++) {
            ipsetList += "<a href=\"#\" class=\"list-group-item\">\n" +
                "                                            <div class=\"media-left pos-rel\">\n" +
                "                                                     alt=\"Profile Picture\">\n" +
                "                                                <i class=\"badge badge-success badge-stat badge-icon pull-left\"></i>\n" +
                "                                            </div>\n" +
                "                                            <div class=\"media-body\">\n" +
                "                                                <p class=\"mar-no\">" + data[i].name + "</p>\n" +
                "                                                <small class=\"text-muted\">" + data[i].industry + "</small>\n" +
                "                                            </div>\n" +
                "                                        </a>";

            ipsetinfolist += "<tr onclick='checkDetail(data[i].id)'>\n" +
                "                                                    <td><a class=\"btn-link\" href=\"#\">" + data[i].name + "</a></td>\n" +
                "                                                    <td>" + data[i].id + "</td>\n" +
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
        document.getElementById("ipset_info_list").innerHTML = ipsetinfolist;

    },
    error: function () {
        // alert("Network warning for posting the purpose of the loan")
    }
});

$.ajax({
    type: "GET",
    url: "/userInfo/getUser",
    dataType: "json",
    data: userId,
    success: function (data) {
        document.getElementById("user").innerHTML = data.name;
        document.getElementById("personinfo").innerHTML = data.statement;
        document.getElementById("credit").innerHTML = data.credit;
        document.getElementById("profession").innerHTML = data.profession;
        document.getElementById("country").innerHTML = data.region;
        document.getElementById("imgURL").innerHTML = data.IDcard_img;
    },
    error: function () {

    }
});

$("#poolsummit").on('click',function () {
    var name = $('#poolName').val();   //名称
    var creater = $('#poolCreater').val();// 创建者
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

    console.log(nowDate);

});

function checkDetail(patentPoolID){
    storage.patentPoolID = patentPoolID;
    window.location.href = "/ipnet/Person-IP-list";
}