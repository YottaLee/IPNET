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
var loanID = storage.getItem('loan_id');
var patentID = storage.getItem('patent_id');
//填表
$.ajax({
    type: "GET",
    url: "/applicant/getChooseBankURL",
    data: {
        loanID: loanID
    },
    success: function (data) {
        document.getElementById("loan-file").href = data;
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});

$.ajax({
    type: "GET",
    url: "/evaluation/getEvaluation",
    data: {
        patentID: patentID
    },
    success: function (data) {
        document.getElementById("evaluation-file").href = data.url;
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});

//提交银行意见
$('#submit').on('click', function () {
     var userId = storage.getItem('user_id');
    $.ajax({
        type: "GET",
        url: "/userInfo/getUser",
        data:{
            userid:userId,
            userType:"Company"
        },
        success:function (user) {
           //不知
        }
    });

    var ifPass = document.getElementById("check-pass").checked;
    var bank = "";//银行名称
    var ifInsurance = document.getElementById("demo-form-checkbox").checked;
    var money = $("#money").val();
    var time = $("#time").val();

    $.ajax({
        type: "POST",
        url: "/bank/submitApplication",
        data: {
            loanID: loanID,
            bank: bank,
            ifPass: ifPass,
            ifInsurance: ifInsurance,
            money: money,
            time: time
        },
        success: function () {
            infoFile("已将信息反馈给专利持有人");
            setTimeout(function () {
                window.location.href = "/ipnet/Bank-IP-list";
            }, 2000);

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }
    });
});

