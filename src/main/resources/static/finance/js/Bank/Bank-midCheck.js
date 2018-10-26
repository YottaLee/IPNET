/**
 * 界面的隐藏结构
 */

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

/**
 * 数据传输
 */
var storage = window.localStorage;
var loanID = storage.getItem('loan_id');
var patentID = storage.getItem('patent_id');
//填表
$.ajax({
    type: "GET",
    url: "/bank/getApplication",
    data: {
        loanID: loanID
    },
    success: function (data) {
        document.getElementById("patent").innerHTML = data.patent;
        document.getElementById("holder").innerHTML = data.person;
        document.getElementById("pur-money").innerHTML = data.money;
        document.getElementById("pur-time").innerHTML = data.time;
        document.getElementById("pur-way").innerHTML = data.way;
        $.ajax({
            type: "GET",
            url: "/evaluation/getEvaluation",
            data: {
                patentID: data.patentID
            },
            success: function (evaluationURL) {
                document.getElementById("evaluation-file").href = evaluationURL.url;
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

//提交银行意见
$('#submit').on('click', function () {

    //  if(document.getElementById("check-pass").checked)
    var ifPass = document.getElementById("check-pass").checked;
    var ifInsurance = document.getElementById("demo-form-checkbox").checked;
    // console.log(ifPass);
    $.ajax({
        type: "POST",
        url: "/bank/submitMidApplication",
        data: {
            loanID: loanID,
            ifPass: ifPass,
            ifInsurance: ifInsurance
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

