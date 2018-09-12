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
        var money = $("#money").val();

        //提交评估报告
        $.ajax({
            type: "POST",
            url: "evaluation/submitReport",
            dataType: "json",
            data: {
                patentID: patentID,
                url: url,
                evaluation: evaluation,
                money: money
            },
            success: function () {
                window.location.href = "Evaluation-reportFinish.html";
            },
            error: function () {
                // alert("Network warning for posting the purpose of the loan")
            }
        });
    }
});
