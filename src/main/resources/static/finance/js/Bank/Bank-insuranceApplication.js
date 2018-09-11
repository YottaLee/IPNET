var storage = window.localStorage;
document.getElementById("model").href = "http://ipnet10.oss-cn-beijing.aliyuncs.com/%E6%96%87%E6%A1%A3%E6%A8%A1%E7%89%88/%E7%90%86%E8%B5%94%E7%94%B3%E8%AF%B7%E4%B9%A6.doc";
//提交结果
$('#submit').on('click', function () {

    if (url == "")
        alert("请先上传文件");
    else {
        var loanID = storage.loanID;
        var person = $("#person").val();
        var address = $("#address").val();
        var time = $("#time").val();
        var reason = $("#reason").val();
        var bank = $("#bank").val();
        var bankName = $("#bank-name").val();
        var bankID = $("#bank-id").val();
        var insuranceID = $("#insurance-id").val();
        var money = $("#money").val();

        //向保险公司发出理赔申请
        $.ajax({
            type: "POST",
            url: "insurance/insuranceApplication",
            dataType: "json",
            data: {
                loanID: loanID,
                url: url,
                person: person,
                address: address,
                time: time,
                reason: reason,
                bank: bank,
                bankName: bankName,
                bankID: bankID,
                insuranceID: insuranceID,
                money: money
            },
            success: function () {
                window.location.href = "Bank-applicationFinish.html";
            },
            error: function () {
                // alert("Network warning for posting the purpose of the loan")
            }
        });
    }
});
