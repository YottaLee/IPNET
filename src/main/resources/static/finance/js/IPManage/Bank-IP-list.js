//获取专利列表
var storage = window.localStorage;
var userId = storage.userId;
$.ajax({
    type: "GET",
    url: "all/getPatentList",
    dataType: "json",
    data: userId,
    success: function (data) {
        var patentList = "";
        for (var i = 0, len = data.length; i < len; i++) {
            patentList += "<tr>\n" +
                "                                <td><a class=\"btn-link\" href=\"#\">" + data[i].patentID + "</a></td>\n" +
                "                                <td>" + data[i].patent + "</td>\n" +
                "                                <td><span class=\"text-muted\">" + data[i].person + "</span></td>\n" +
                "                                <td>\n";
            switch (data[i].loan_state) {
                case 3:
                    patentList += "                                    <div class=\"label label-table label-warning\">申请质押中</div>\n";
                    break;
                case 4:
                    patentList += "                                    <div class=\"label label-table label-mint\">质押中</div>\n";
                case 6:
                    patentList += "                                    <div class=\"label label-table label-danger\">逾期</div>\n";
                default:
                    patentList += "                                    <div class=\"label label-table label-success\">闲置</div>\n";
            }
            patentList += "                                </td>\n" +
                "                                <td>\n" +
                "                                    <button data-target=\"#demo-lg-modal\" data-toggle=\"modal\" class=\"btn btn-info\" onclick=\"check(data[i])\">详情</button>\n" +
                "                                </td>\n" +
                "                            </tr>";
        }
        document.getElementById("ip_list").innerHTML = patentList;

    },
    error: function () {
        // alert("Network warning for posting the purpose of the loan")
    }
});


function check(patent) {
    storage.loanID = patent.loanID;
    var state = patent.loan_state;
    switch (state) {
        case 3:
            window.location.href = "/ipnet/Bank-check2";
            break;//审核
        case 5:
            $.ajax({
                type: "GET",
                url: "all/getIfContract",
                dataType: "json",
                data: {
                    loanID: loanID,
                    userid: userId
                },
                success: function (data) {
                    if (data)
                        window.location.href = "/ipnet/All-loan-check";
                    else
                        window.location.href = "/ipnet/All-loan-contract";
                }
            });
            break;
        case 7:
            window.location.href = "/ipnet/Bank-insuranceApplication2";
            break;
        default:
            //专利详情界面
            break;
    }
}

function stateToText(state) {
    switch (state) {
        case 1:
            return "申请许可/转让过程中";
        case 2:
            return "转让过程中";
        case 3:
            return "申请质押贷款过程中";
        case 4:
            return "质押过程中";
        case 5:
            return "待审核状态";
        case 6:
            return "逾期";
        default:
            return "未找到状态";
    }
}