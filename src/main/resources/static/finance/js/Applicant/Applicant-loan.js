var storage = window.localStorage;
document.getElementById("model").href = "http://ipnet10.oss-cn-beijing.aliyuncs.com/%E6%96%87%E6%A1%A3%E6%A8%A1%E7%89%88/%E4%B8%93%E5%88%A9%E6%9D%83%E8%B4%A8%E6%8A%BC%E7%99%BB%E8%AE%B0%E7%94%B3%E8%AF%B7%E8%A1%A8.doc";
$('#submit').on('click', function () {
    if(url == "")
        alertFile("请先上传文件");
    else {

        var patentID = storage.patentID;
        var money = $("#loan-money").val();
        var time = $("#loan-time").val();
        var bank = $("#demo-chosen-select").val();

        $.ajax({
            // 判断一下该专利是否有评估结果，没有就跳转到评估报告申请界面
            type: "GET",
            url: "applicant/ifValue",
            dataType: "json",
            data: patentID,
            success: function (data) {
                //存取质押贷款意向结果
                $.ajax({
                    type: "POST",
                    url: "applicant/chooseBank",
                    dataType: "json",
                    data: {
                        patentID: patentID,
                        url: url,
                        money: money,
                        time: time,
                        bank: bank
                    },
                    success: function (data) {

                    },
                    error: function () {
                        // alert("Network warning for posting the purpose of the loan")
                    }
                });

                if (data) {
                    window.location.href = "/ipnet/Applicant-applicationFinish";
                } else {
                    //跳转到申请评估的界面
                    window.location.href = "/ipnet/Applicant-evaluation2";
                }
            },
            error: function () {
                // alert("Network warning");
            }
        });
    }

});

function alertFile(str) {

    $.alert({
        title: str,
        content: '',
        confirmButton: '我知道了',
        confirmButtonClass: 'btn-primary',
        icon: 'fa fa-info',
        animation: 'zoom',
        confirm: function () {

        }
    });

}
