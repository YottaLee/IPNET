/**
 * 界面的隐藏结构
 */
$('#checkDetail').on('click', function () {
    if (document.getElementById("ifHide").style.display == "block")
        reportHidden();
    else
        reportShow();
});

function reportShow() {
    document.getElementById("ifHide").style.display = "block";
    document.getElementById("checkDetail").innerHTML = "缩起";
}

function reportHidden() {
    document.getElementById("ifHide").style.display = "none";
    document.getElementById("checkDetail").innerHTML = "详情";
}

divHidden();

function agree(checkbox) {
    if (checkbox.checked == true)
        divShow();
    else
        divHidden();
}

function divShow() {
    document.getElementById("loan").style.display = "block";
}

function divHidden() {
    document.getElementById("loan").style.display = "none";
}

function sure(checkbox) {
    if (checkbox.checked == true)
        document.getElementById("ifInsurance").innerHTML = "该信息已经反馈给专利持有人、保险公司、政府以及评估机构，以待下一步的合同确认";
    else
        document.getElementById("ifInsurance").innerHTML = "该信息已经反馈给专利持有人，以待下一步的合同确认";
}

/**
 * 数据传输
 */
var storage = window.localStorage;
var loanID = storage.loanID;
var patentID = storage.patentID;
//填表
$.ajax({
    type: "GET",
    url: "applicant/getChooseBankURL",
    dataType: "json",
    data: loanID,
    success: function (data) {
        document.getElementById("loan-file").href = data;
    },
    error: function () {
        // alert("Network warning");
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

//提交银行意见
$('#submit').on('click', function () {

    var ifPass = document.getElementById("check-pass").checked;
    var bank = "";//银行名称
    var ifInsurance = document.getElementById("demo-form-checkbox").checked;
    var money = $("#money").val();
    var time = $("#time").val();

    $.ajax({
        type: "POST",
        url: "bank/submitApplication",
        dataType: "json",
        data: {
            loanID: loanID,
            bank: bank,
            ifPass: ifPass,
            ifInsurance: ifInsurance,
            money:money,
            time: time
        },

        success: function (data) {

        },
        error: function () {
            // alert("Network warning");
        }
    });
});

