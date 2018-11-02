//获取专利列表

var storage = window.localStorage;
var userId = storage.getItem('user_id');
console.log(userId);
$.ajax({
    type: "GET",
    url: "/Patent/getPatentList",
    data: {
        userId: userId
    },
    success: function (data) {
        console.log(data);
        var patentList = "<table class=\"table table-striped\">\n" +
            "                            <thead>\n" +
            "                            <tr>\n" +
            "                                <th>专利名</th>\n" +
            "                                <th>专利号</th>\n" +
            "                                <th>专利类型</th>\n" +
            "                                <th>专利申请时间</th>\n" +
            "                                <th>状态</th>\n" +
            // "                                <th>专利池号</th>\n" +
            // "                                <th>转让/许可</th>\n" +
            // "                                <th>申请评估</th>\n" +
            "                                <th>申请质押贷款</th>\n" +
            "                            </tr>\n" +
            "                            </thead>\n" +
            "                            <tbody>";
        for (var i = 0, len = data.length; i < len; i++) {
            patentList += "<tr><td><a class=\"btn-link\" href=\"#\">" + data[i].patent_name + "</a></td>\n" +
                "                                <td>" + data[i].patent_id + "</td>\n" +
                "                                <td><span class=\"text-muted\"><i class=\"demo-pli-clock\"></i>" + data[i].patent_type + "</span></td>\n" +
                "                                <td>" + data[i].apply_date + "</td>\n" +
                "                                <td>\n";
            var displayState = "";
            var displayOperation = "申请质押贷款";
            var labelColor = "label-warning";
            switch (data[i].state) {
                case "free":
                    displayState = "闲置状态";
                    labelColor = "label-success";
                    break;
                case "to_be_transfer":
                    displayState = "申请转让中";
                    break;
                case "transfering":
                    displayState = "转让过程中";
                    break;
                case "to_be_loan":
                    displayState = "申请贷款中";
                    displayOperation = "查看贷款详情";
                    break;
                case "loaning":
                    displayState = "质押过程中";
                    labelColor = "label-info";
                    displayOperation = "查看贷款详情";
                    break;
                case "to_be_check":
                    displayState = "待审核状态";
                    break;
                case "overdue":
                    displayState = "逾期";
                    labelColor = "label-important";
                    displayOperation = "查看贷款详情";
                    break;
                default:
                    displayState = "未找到状态";
                    labelColor = "label-important";
                    break;
            }
            patentList += "                                    <div class=\"label label-table " + labelColor + "\">" + displayState + "</div>\n";
            patentList += "                                </td>\n" +
                // "                                <td><i class=\"demo-pli-mine\"></i>" + data[i].pool_id + "</td>\n" +
                // "                                <td>\n" +
                // "                                    <button data-target=\"#demo-lg-modal\" data-toggle=\"modal\" class=\"btn btn-info\"  id=\"transaction-" + data[i].patent_id + "\"onclick=\"transaction(this.id)\">转让/许可</button>\n" +
                // "                                </td>\n" +
                // "                                <td>\n" +
                // "                                    <button data-target=\"#demo-lg-modal\" data-toggle=\"modal\" class=\"btn btn-success\" id=\"evaluation-" + data[i].patent_id + "\"onclick=\"evaluation(this.id)\">查看评估</button>\n" +
                // "                                </td>\n" +
                "                                <td>\n" +
                "                                    <button data-target=\"#demo-lg-modal\" data-toggle=\"modal\" class=\"btn btn-primary\" id=\"loan-" + data[i].patent_id + "\"onclick=\"loan(this.id)\">"+displayOperation+"</button>\n" +
                "                                </td></tr>";
        }
        patentList += "   </tbody>\n" +
            "                        </table>";
        document.getElementById("ip_list").innerHTML = patentList;

    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});

function toIndex() {
    window.location.href = "/ipnet/Permit-Contract";
}

function transaction(patentID) {
    patentID = (patentID + "").substring((patentID + "").indexOf("-") + 1);
    //判断一下该专利是否在闲置过程中F
    if (state == "free") {
        storage.setItem('patent_id', patentID);
        //跳转到交易界面
    }
    else {
        alertFile(stateToText(state));
    }
}

function evaluation(patentID) {
    patentID = (patentID + "").substring((patentID + "").indexOf("-") + 1);
    evaluationNoTransfer(patentID);
}

function evaluationNoTransfer(patentID) {
    storage.setItem('patent_id', patentID);
    $.ajax({
        // 判断一下该专利是否有评估结果，没有就跳转到评估报告申请界面
        type: "GET",
        url: "/applicant/ifValue",
        data: {
            patentID: patentID
        },
        success: function (data) {
            if (data) {
                window.location.href = "/ipnet/Applicant-checkEvaluation";
            } else {
                window.location.href = "/ipnet/Applicant-evaluation2";
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }
    });

}

function loan(patentID) {
    //判断一下该专利是否在质押贷款过程中
    patentID = (patentID + "").substring((patentID + "").indexOf("-") + 1);
    console.log(patentID);
    var state = "";
    $.ajax({
        type: "GET",
        async: false,
        url: "/Patent/searchPatentByID",
        data: {
            patentID: patentID
        },
        success: function (data) {
            console.log(data.state);
            state = data.state;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }
    });

    storage.setItem('patent_id', patentID);
    //  是否有最新的贷款
    $.ajax({
        type: "GET",
        url: "/all/getLatestLoan",
        data: {
            patentID: patentID
        },
        success: function (loanID) {
            if (loanID != null && loanID != "") {
                storage.setItem('loan_id', loanID);
                $.ajax({
                    type: "GET",
                    url: "/bank/getInfo",
                    data: {
                        loanID: loanID
                    },
                    success: function (loan) {
                        var state = loan.loan_state;
                        console.log(state);
                        switch (state) {
                            case "to_be_loan_application":
                                window.location.href = "/ipnet/Applicant-loan2";//申请贷款
                                break;
                            case "to_be_checked_by_bank":
                                window.location.href = "/ipnet/loan_detail";//贷款详情
                                break;
                            case "to_be_value":
                                window.location.href = "/ipnet/Applicant-evaluation2";//申请评估
                                break;
                            case "to_be_pay_value":
                                payForEvaluation(patentID);
                                break;
                            case "to_be_evaluation":
                                window.location.href = "/ipnet/loan_detail";//贷款详情
                                break;
                            case "to_be_mid_confirm":
                                window.location.href = "/ipnet/Applicant-checkEvaluation";//贷款详情
                                break;
                            case "to_be_choose_insurance":
                                window.location.href = "/ipnet/Applicant-chooseInsurance2";//选择保险公司
                                break;
                            case "to_be_checked_by_insurance":
                                window.location.href = "/ipnet/loan_detail";//贷款详情
                                break;
                            case "to_be_contract_by_insurance":
                                window.location.href = "/ipnet/loan_detail";//贷款详情
                                break;
                            case "to_be_buy_insurance":
                                payForInsurance(loanID);
                                break;
                            case "to_be_final_confirm":
                                window.location.href = "/ipnet/loan_detail";//贷款详情
                                break;
                            case "to_be_contract_by_loan":
                                window.location.href = "/ipnet/All-loan-check";
                                break;
                            case "loaning":
                                window.location.href = "/ipnet/loan_detail";
                            case "overdue":
                                alertFile("由于您未及时还款，您的专利资产将交由银行处置");
                            case "to_be_compensation":
                                window.location.href = "/ipnet/loan_detail";//贷款详情
                                break;
                            case "free":
                                break;
                            default:
                                break;


                        }
                    }
                });
            }
            else {
                storage.setItem('loan_id', loanID);
                window.location.href = "/ipnet/Applicant-loan2";
            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }
    });


}

//查看专利的状态
function searchIPState(patentID) {
    $.ajax({
        type: "GET",
        async: false,
        url: "/Patent/searchPatentByID",
        data: {
            patentID: patentID
        },
        success: function (data) {
            console.log(data.state);
            state = data.state;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }
    });

}

function stateToText(state) {
    switch (state) {
        case "free":
            return "闲置状态";
        case "to_be_transfer":
            return "申请许可/转让过程中";
        case "transfering":
            return "转让过程中";
        case "to_be_loan":
            return "申请质押贷款过程中";
        case "loaning":
            return "质押过程中";
        case "to_be_check":
            return "待审核状态";
        case "overdue":
            return "逾期";
        default:
            return "未找到状态";
    }
}

//向评估机构支付费用
function payForEvaluation(patentID) {

    $.ajax({
            url: '/Patent/searchPatentByID',
            type: 'GET',
            async: false,
            data: {
                patentID: patentID
            },
            success: function (data) {
                var patent = data.patent_name;
                var holder = data.patent_holder;
                var money = 20000;//评估费用
                //跳入向评估公司申请的支付界面
                $.ajax({
                    type: "GET",
                    url: "/evaluation/getEvaluationId",
                    success: function (evaluationFirm) {
                        console.log(evaluationFirm);
                        console.log(evaluationFirm.id);
                        console.log(evaluationFirm.name);
                        var transaction = {
                            patentID: patentID,
                            patent: patent,
                            payer_id: data.userId,//付款方
                            payee_id: evaluationFirm.id,//收款方
                            payer: holder,
                            payee: evaluationFirm.name,
                            way: "专利评估",//评估机构只有一个
                            money: money//评估费用
                        };

                        storage.setItem('pay_order', JSON.stringify(transaction));

                        //支付评估费用
                        infoFile("该专利尚未支付评估费用，即将跳入支付界面");
                        setTimeout(function () {
                            window.location.href = "/ipnet/pay";
                        }, 2000);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
                    }
                });
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
            }
        }
    );


}

function payForInsurance(loanID) {
    $.ajax({
        url: "/bank/getInfo",
        type: 'GET',
        data: {
            loanID: loanID
        },
        success: function (data) {
            var transaction = {
                patentID: data.patentID,
                patent: data.patent,
                payer_id: userId,//差支付人id
                payee_id: data.insuranceId,//差收款方id
                payer: data.person,
                payee: data.insurance,
                way: "专利保险",
                money: 4000
            };
            storage.setItem('pay_order', JSON.stringify(transaction));
            infoFile("该专利需要购买专利质押融资贷款保证保险，即将跳入支付界面");
            setTimeout(function () {
                window.location.href = "/ipnet/pay";
            }, 2000);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }
    });
}
