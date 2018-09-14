//填表数据
var storage = window.localStorage;
var loanID = storage.loanID;
$.ajax({
    type: "GET",
    url: "insurance/getInsurance",
    dataType: "json",
    data: loanID,
    success: function (data) {
        document.getElementById("patentID").innerHTML = data.patentID;
        document.getElementById("person").innerHTML = data.person;
        document.getElementById("address").innerHTML = data.address;
        document.getElementById("time").innerHTML = data.time;
        document.getElementById("reason").innerHTML = data.reason;
        document.getElementById("bank").innerHTML = data.bank;
        document.getElementById("bank-id").innerHTML = data.bankID;
        document.getElementById("bank-name").innerHTML = data.bankName;
        document.getElementById("insurance-id").innerHTML = data.insuranceID;
        document.getElementById("money").innerHTML = data.money;
        document.getElementById("fileURL").href = data.url;
    },
    error: function () {

    }
});

//提交结果
$('#submit').on('click', function () {
    var insuranceID = document.getElementById("insurance-id").innerHTML;
    var ifPass = document.getElementById("check-pass").checked;

    //存取保险公司是否同意理赔
    $.ajax({
        type: "POST",
        url: "insurance/ifCompensate",
        dataType: "json",
        data: {
            loanID: loanID,
            insuranceID: insuranceID,
            ifPass: ifPass
        },
        success: function (data) {
            window.location.href = "/ipnet/Insurance-checkFinish";
        },
        error: function () {
            // alert("Network warning for posting the purpose of the loan")
        }
    });
});
