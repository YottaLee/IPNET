document.getElementById("model").href = "http://ipnet10.oss-cn-beijing.aliyuncs.com/%E6%96%87%E6%A1%A3%E6%A8%A1%E7%89%88/%E4%B8%93%E5%88%A9%E6%9D%83%E8%B4%A8%E6%8A%BC%E7%99%BB%E8%AE%B0%E7%94%B3%E8%AF%B7%E8%A1%A8.doc";
$('#submit').on('click', function () {
    var storage = window.localStorage;
    var patentID = storage.getItem('patent_id');
    var userID = storage.getItem('user_id');
    var url = storage.getItem('fileURL');
    if (url == "" || url == null)
        alertFile("请先上传文件");
    else {
        var money = $("#loan-money").val();
        var time = $("#loan-time").val();
        var way = $("#loan-way").val();
        var bank = $("#demo-chosen-select").val();
        console.log(bank);

        //存取质押贷款意向结果
        $.ajax({
            type: "POST",
            url: "/applicant/chooseBank",
            data: {
                patentID: patentID,
                userID:userID,
                url: url,
                money: money,
                time: time,
                way: way,
                bank: bank
            },
            success: function (loanID) {
                infoFile("恭喜您！上传成功");
                setTimeout(function () {
                    window.location.href = "/ipnet/Person-IP-list";
                }, 2000);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
            }
        });


    }
});