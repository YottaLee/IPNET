//获取专利列表
var storage = window.localStorage;
var userId = storage.userId;
$.ajax({
    type: "GET",
    url: "Patent/getPatentList",
    dataType: "json",
    data: userId,
    success: function (data) {
        var patentList = "";
        for (var i = 0, len = data.length; i < len; i++) {
            patentList += "<td><a class=\"btn-link\" href=\"#\">" + data[i].patent_name + "</a></td>\n" +
                "                                <td>" + data[i].patent_id + "</td>\n" +
                "                                <td><span class=\"text-muted\"><i class=\"demo-pli-clock\"></i>" + data[i].patent_type + "</span></td>\n" +
                "                                <td>" + data[i].apply_date + "</td>\n" +
                "                                <td>\n" +
                "                                    <div class=\"label label-table label-success\">" + data[i].state + "</div>\n" +
                "                                </td>\n" +
                "                                <td><i class=\"demo-pli-mine\"></i>"+data[i].pool_id+"</td>\n" +
                "                                <td>\n" +
                "                                    <button data-target=\"#demo-lg-modal\" data-toggle=\"modal\" class=\"btn btn-warning\" onclick=\"toIndex()\">合同详情</button>\n" +
                "                                </td>\n" +
                "                                <td>\n" +
                "                                    <button data-target=\"#demo-lg-modal\" data-toggle=\"modal\" class=\"btn btn-info\" onclick=\"transaction(data[i].patent_id)\">转让/许可</button>\n" +
                "                                </td>\n" +
                "                                <td>\n" +
                "                                    <button data-target=\"#demo-lg-modal\" data-toggle=\"modal\" class=\"btn btn-success\" onclick=\"evaluation(data[i].patent_id)\">查看评估</button>\n" +
                "                                </td>\n" +
                "                                <td>\n" +
                "                                    <button data-target=\"#demo-lg-modal\" data-toggle=\"modal\" class=\"btn btn-primary\" onclick=\"loan(data[i].patent_id)\">合同质押贷款</button>\n" +
                "                                </td>";
        }
        document.getElementById("ip_list").innerHTML = patentList;

    },
    error: function () {
        // alert("Network warning for posting the purpose of the loan")
    }
});

function toIndex() {
    window.location.href = "Permit-Contract.html"
}

function transaction(patentID) {
    //判断一下该专利是否在闲置过程中
    var state = searchIPState(patentID);
    if (state == 0) {
        storage.patentID = patentID;
        //跳转到交易界面
    }
    else {
        alert(stateToText(state));
    }
}

function evaluation(patentID) {
    storage.patentID = patentID;
    $.ajax({
        // 判断一下该专利是否有评估结果，没有就跳转到评估报告申请界面
        type: "GET",
        url: "applicant/ifValue",
        dataType: "json",
        data: patentID,
        success: function (data) {
            if (data) {
                window.location.href = "Applicant-checkEvaluation.html";
            } else {
                window.location.href = "Applicant-evaluation2.html";
            }
        },
        error: function () {
            // alert("Network warning");
        }
    });

}

function loan(patentID) {
    //判断一下该专利是否在质押贷款过程中
    var state = searchIPState(patentID);
    switch (state) {
        case 0:
            storage.patentID = patentID;
            $.ajax({
                // 判断一下该专利是否有评估结果，没有就跳转到评估报告申请界面
                type: "GET",
                url: "applicant/ifValue",
                dataType: "json",
                data: patentID,
                success: function (data) {
                    if (data) {
                        window.location.href = "Applicant-loan2.html";
                    } else {
                        //存取意向信息
                        $.ajax({
                            type: "POST",
                            url: "applicant/applyLoan",
                            dataType: "json",
                            data: patentID,
                            success: function (data) {
                                storage.loanID = data;
                                window.location.href = "Applicant-evaluation2.html";
                            },
                            error: function () {

                            }
                        });
                    }
                },
                error: function () {
                    // alert("Network warning");
                }
            });
            break;
        case 3:
            break;
        default:
            alert(stateToText(state));
    }

}

//查看专利的状态
function searchIPState(patentID) {
    var state = -1;
    $.ajax({
        type: "GET",
        url: "/Patent/searchPatentByID",
        dataType: "json",
        data: patentID,
        success: function (data) {
            state = data.state;
        },
        error: function () {

        }
    });
    return state;
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