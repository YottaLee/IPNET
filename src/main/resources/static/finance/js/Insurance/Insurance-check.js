//填表数据
var storage = window.localStorage;
var loanID = storage.getItem('loan_id');
$.ajax({
    type: "GET",
    url: "applicant/getChooseInsuranceURL",
    dataType: "json",
    data: loanID,
    success: function (data) {
        document.getElementById("insurance-file").href = data;
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});

$.ajax({
    type: "GET",
    url: "evaluation/getEvaluation",
    dataType: "json",
    data: patentID,
    success: function (data) {
        document.getElementById("evaluation-file").href = data.url;
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});


$.ajax({
    type: "GET",
    url: "bank/getInfo",
    dataType: "json",
    data: loanID,
    success: function (data) {
        document.getElementById("patent").innerHTML = data.patent;
        document.getElementById("holder").innerHTML = data.person;
        document.getElementById("loan-money").innerHTML = data.money;
        document.getElementById("loan-time").innerHTML = data.time;
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});

//提交结果
$('#submit').on('click', function () {
    var ifPass = document.getElementById("check-pass").checked;
    //存取是否愿意投保
    $.ajax({
        type: "POST",
        url: "insurance/ifInsurance",
        dataType: "json",
        data: {
            loanID: loanID,
            ifPass: ifPass
        },
        success: function (data) {
            window.location.href = "/ipnet/Insurance-IP-list"
               //回到保险公司主界面
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }
    });
});
