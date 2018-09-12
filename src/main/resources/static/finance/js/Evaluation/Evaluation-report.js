var storage = window.localStorage;
var patentID = storage.patentID;
$.ajax({
    type: "GET",
    url: "evaluation/getEvaluationApplicationURL",
    dataType: "json",
    data: {
        patentID: patentID
    },
    success: function (data) {
        document.getElementById("apply-file").href = data;
    },
    error: function () {
        // alert("Network warning for posting the purpose of the loan")
    }
});

//提交结果
$('#submit').on('click', function () {

    if(url == "")
        alert("请先上传文件");
    else {

        var evaluation = $("#evaluation").val();

        //提交评估报告
        $.ajax({
            type: "POST",
            url: "evaluation/submitReport",
            dataType: "json",
            data: {
                patentID: patentID,
                url: url,
                evaluation: evaluation
            },
            success: function () {
                var patent = "";
                var holder = "";
                $.ajax({
                    url: '/Patent/searchPatentByID',
                    type: 'GET',
                    data: {
                        patentID: patentID
                    },
                    success: function (data) {
                        patent = data.patent_name;
                        holder = data.patent_holder;

                    },
                    error: function () {

                    }
                });

                //跳入向评估公司申请的支付界面
                var transaction = {
                    patentID: patentID,
                    patent: patent,
                    holoder: holder,
                    way: "专利评估",
                    amount: money
                };

                window.location.href = "../pay.html";
                window.location.href = "Evaluation-reportFinish.html";
            },
            error: function () {
                // alert("Network warning for posting the purpose of the loan")
            }
        });
    }
});
