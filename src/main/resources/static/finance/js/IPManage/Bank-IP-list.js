//获取专利列表
var storage = window.localStorage;
var userId = storage.getItem('user_id');
$.ajax({
    type: "GET",
    url: "/all/getPatentList",
    data: {
        userId: userId
    },
    success: function (data) {
        var patentList = "<table class=\"table table-striped\">\n" +
            "                            <thead>\n" +
            "                            <tr>\n" +
            "                                <th>专利号</th>\n" +
            "                                <th>专利名</th>\n" +
            "                                <th>专利持有人</th>\n" +
            "                                <th>状态</th>\n" +
            "                                <th>详情</th>\n" +
            "                            </tr>\n" +
            "                            </thead>\n" +
            "                            <tbody>";
        for (var i = 0, len = data.length; i < len; i++) {
            patentList += "<tr>\n" +
                "                                <td><a class=\"btn-link\" href=\"#\">" + data[i].patentID + "</a></td>\n" +
                "                                <td>" + data[i].patent + "</td>\n" +
                "                                <td><span class=\"text-muted\">" + data[i].person + "</span></td>\n" +
                "                                <td>\n";
            switch (data[i].loan_state) {
                case "to_be_loan_application":
                    patentList += "                                    <div class=\"label label-table label-mint\">质押中</div>\n";
                    break;
                case "overdue":
                    patentList += "                                    <div class=\"label label-table label-danger\">逾期</div>\n";
                    break;
                case "free":
                    patentList += "                                    <div class=\"label label-table label-success\">闲置</div>\n";
                    break;
                default:
                    patentList += "                                    <div class=\"label label-table label-warning\">申请质押中</div>\n";
                    break;
            }
            patentList += "                                </td>\n" +
                "                                <td>\n" +
                "                                    <button data-target=\"#demo-lg-modal\" data-toggle=\"modal\" class=\"btn btn-info\"  id=\"check-" + data[i].loanID + "\"onclick=\"check(this.id)\">详情</button>\n" +
                "                                </td>\n" +
                "                            </tr>";
        }
        patentList += "   </tbody>\n" +
            "                        </table>";
        document.getElementById("ip_list").innerHTML = patentList;

    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});


function check(loanID) {
    loanID = (loanID + "").substring((loanID + "").indexOf("-") + 1);
    var storage = window.localStorage;
    storage.setItem('loan_id', loanID);
    $.ajax({
        type: 'GET',
        url: '/bank/getInfo',
        async: false,
        data: {
            loanID: loanID
        },
        success: function (data) {

            var state = data.loan_state;
            switch (state) {
                case "to_be_checked_by_bank":
                    window.location.href = "/ipnet/Bank-check2";
                    break;//审核
                case "to_be_mid_confirm":
                    window.location.href = "/ipnet/Bank-midConfirm";
                    break;
                case "to_be_contract_by_loan":
                    window.location.href = "/ipnet/Bank-finalConfirm";
                    break;
                // case 7:
                //     window.location.href = "/ipnet/Bank-insuranceApplication2";
                //     break;
                default:
                    window.location.href = "/ipnet/loan_detail";
                    //专利详情界面
                    break;
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }

    });
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