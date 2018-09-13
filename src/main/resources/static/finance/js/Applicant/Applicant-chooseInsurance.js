var storage = window.localStorage;
var loanID = storage.loanID;
document.getElementById("model").href = "http://ipnet10.oss-cn-beijing.aliyuncs.com/%E6%96%87%E6%A1%A3%E6%A8%A1%E7%89%88/%E4%BF%9D%E8%AF%81%E4%BF%9D%E9%99%A9%E6%8A%95%E4%BF%9D%E5%8D%95%E3%80%81%E4%BF%9D%E5%8D%95.xlsx";
$('#submit').on('click', function () {

    if (url == "")
        alert("请先上传文件");
    else {

        var insurance = $("#demo-chosen-select").val();

        $.ajax({
            type: "POST",
            url: "applicant/chooseInsurance",
            dataType: "json",
            data: {
                loanID: loanID,
                url: url,
                insurance: insurance
            },
            success: function (data) {
                if (data == 3)
                    window.location.href = "/ipnet/Applicant-chooseFinish";
                else
                    alert(data);
            },
            error: function () {
                // alert("Network warning");
            }
        })
    }


});

