var storage = window.localStorage;
var patentID = storage.getItem('patent_id');
$.ajax({
    type: "GET",
    url: "/evaluation/getEvaluationApplicationURL",
    data: {
        patentID: patentID
    },
    success: function (data) {
        document.getElementById("apply-file").href = data;
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});
$.ajax({
    type: "GET",
    url: "/Patent/searchPatentByID",
    data: {
        patentID: patentID
    },
    success: function (data) {
        document.getElementById("patentID").innerHTML = data.patent_id;
        document.getElementById("patent").innerHTML = data.patent_name;
        document.getElementById("holder").innerHTML = data.patent_holder;
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});
$("#money").val(20000);
//提交结果
$('#submit').on('click', function () {
    var url = storage.getItem("fileURL");
    if (url == null)
        alertFile("请先上传文件");
    else {
        var rule = $("#rule").val();
        var result = $("#result").val();
        var evaluation = $("#evaluation").val();
        var money = $("#money").val();
        var tech = $("#tech").val();
        //提交评估报告
        $.ajax({
            type: "POST",
            url: "/evaluation/submitReport",
            data: {
                patentID: patentID,
                url: url,
                rule: rule,
                tech: tech,
                evaluation: evaluation,
                result: result,
                money: money
            },
            success: function () {
                infoFile("评估完成");
                setTimeout(function () {
                    window.location.href = "/ipnet/Evaluation-IP-list";
                }, 2000);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
            }
        });
    }
});

function patentValueBlockChain(patentID, value) {
    $.ajax({
        url: "http://120.79.232.126:3000/api/IPEstate",
        type: "GET",
        dataType: "json", //指定服务器返回的数据类型
        data: {
            id: patentID
        },
        success: function (data) {
            $.ajax({
                url: "http://120.79.232.126:3000/api/IPEstate",
                type: "PUT",
                dataType: "json", //指定服务器返回的数据类型
                data: {
                    $class: "org.acme.ipregistry.IPEstate",
                    id: patentID,
                    price: value,
                    ownerID: data.ownerID,
                    agentID: data.agentID,
                    poolID: data.poolID
                },
                success: function (data) {
                    console.log(data);
                },
                error: function (error) {
                    console.log(error);
                }
            });
        },
        error: function (error) {
            console.log(error);
        }
    });
}
