divHidden();

function sure(checkbox) {
    if (checkbox.checked == true)
        divShow();
    else
        divHidden();
}

function divShow() {
    document.getElementById("ifHide").style.display = "block";
}

function divHidden() {
    document.getElementById("ifHide").style.display = "none";
}

var storage = window.localStorage;
var loanID = storage.loanID;
//获取该合同其他公司信息
//0-专利持有人 1-金融机构 2-保险公司 3-评估机构 4-政府
$.ajax({
    type: "POST",
    url: "all/getContract",
    dataType: "json",
    data: {
        loanID: loanID
    },
    success: function (data) {
        var gov = document.getElementsByClassName("gov");
        for (var i = 0, len = gov.length; i < len; i++)
            gov[i] = data[4];
        var bank = document.getElementsByClassName("bank");
        for (var i = 0, len = bank.length; i < len; i++)
            bank[i] = data[1];
        var insurance = document.getElementsByClassName("insurance");
        for (var i = 0, len = insurance.length; i < len; i++)
            insurance[i] = data[2];
        var value = document.getElementsByClassName("value");
        for (var i = 0, len = value.length; i < len; i++)
            value[i] = data[3];
    },
    error: function () {
        // alert("Network warning");
    }
});
$('#checkInfo').on('click',function () {
    window.location.href = "All-loan-check.html";
});

// var patentID = document.getElementById("patentID").innerHTML;
$('#submit').on('click', function () {

    var userid = storage.userId;//用户名
    var ifPass = document.getElementById("demo-form-checkbox").checked;

    $.ajax({
        type: "POST",
        url: "all/ifContract",
        dataType: "json",
        data: {
            loanID: loanID,
            userid: userid,
            ifPass: ifPass
        },
        success: function (data) {
            // storage.removeItem("loanID");
            // switch (data){
            //     case 0: window.location.href = ""
            //     case 2:
            //     case 3:
            //     case 4:
            // }
            window.location.href = "All-loan-check.html";
        },
        error: function () {
            // alert("Network warning for posting the purpose of the loan")
        }
    });


});