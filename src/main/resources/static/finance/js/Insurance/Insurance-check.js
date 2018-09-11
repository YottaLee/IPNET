//填表数据
var storage = window.localStorage;
var loanID = storage.loanID;
$.ajax({
    type: "GET",
    url: "applicant/getChooseInsuranceURL",
    dataType: "json",
    data: loanID,
    success: function (data) {
        document.getElementById("insurance-file").href = data;
    },
    error: function () {

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
    error: function () {
        // alert("Network warning");
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
    error: function () {

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
               //回到保险公司主界面
        },
        error: function () {
            // alert("Network warning for posting the purpose of the loan")
        }
    });
});
