//填表数据
var storage = window.localStorage;
var loanID = storage.getItem('loan_id');
$.ajax({
    type: "GET",
    url: "/applicant/getChooseInsuranceURL",
    data: {
        loanID: loanID
    },
    success: function (data) {
        document.getElementById("insurance-file").href = data;
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});


$.ajax({
    type: "GET",
    url: "/bank/getInfo",
    data: {
        loanID: loanID
    },
    success: function (data) {
        console.log(data.money);
        document.getElementById("patent").innerHTML = data.patent;
        document.getElementById("holder").innerHTML = data.person;
        document.getElementById("loan-money").innerHTML = data.money;
        document.getElementById("loan-time").innerHTML = data.time;
        document.getElementById("evaluation").innerHTML = data.evaluation;
        $.ajax({
            type: "GET",
            url: "/evaluation/getEvaluation",
            data: {
                patentID: data.patentID
            },
            success: function (data) {
                document.getElementById("evaluation-file").href = data.url;
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

//提交结果
$('#submit').on('click', function () {
    var ifPass = document.getElementById("check-pass").checked;
    console.log(loanID);
    $.ajax({
        type: "GET",
        url: "/insurance/getInsurance",
        data: {
            loanid: loanID
        },
        success: function (insuranceID) {
            //存取是否愿意投保
            $.ajax({
                type: "POST",
                url: "/insurance/ifInsurance",
                data: {
                    id: insuranceID,
                    ifPass: ifPass
                },
                success: function () {
                    infoFile("已将信息反馈给专利持有人");
                    setTimeout(function () {
                        window.location.href = "/ipnet/Insurance-IP-list";
                        //回到保险公司主界面
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
    });
});
