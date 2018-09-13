//获取专利列表
var storage = window.localStorage;
var userId = storage.userId;
$.ajax({
    type: "GET",
    url: "PatentPool/getIPSETList",
    dataType: "json",
    data: userId,
    success: function (data) {
        var patentPoolList = "";
        for (var i = 0, len = data.length; i < len; i++) {
            patentPoolList += "<td><a class=\"btn-link\" href=\"#\">" + data[i].name + "</a></td>\n" +
                "                                <td>" + data[i].name + "</td>\n" +
                "                                <td><span class=\"text-muted\"><i class=\"demo-pli-clock\"></i>" + data[i].createTime + "</span></td>\n" +
                "                                <td>" + str(data[i].patents.length) + "</td>\n";
        }
        document.getElementById("ipset_list").innerHTML = patentPoolList;

    },
    error: function () {
        // alert("Network warning for posting the purpose of the loan")
    }
});