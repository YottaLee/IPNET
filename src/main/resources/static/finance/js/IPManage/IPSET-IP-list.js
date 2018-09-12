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
            patentPoolList += "<tr>\n" +
                "                                                    <td><a class=\"btn-link\" href=\"#\">"+data[i].name+"</a></td>\n" +
                "                                                    <td>"+data[i].id+"</td>\n" +
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