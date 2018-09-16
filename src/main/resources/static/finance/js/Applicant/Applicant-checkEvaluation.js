var storage = window.localStorage;
var patentID = storage.getItem('patent_id');
$.ajax({
    type: "GET",
    url: "evaluation/getEvaluation",
    dataType: "json",
    data: patentID,
    success: function (data) {
       document.getElementById("patent").innerHTML = data.patent;
        document.getElementById("rule").innerHTML = data.rule;
        document.getElementById("tech").innerHTML = data.tech;
        document.getElementById("finance").innerHTML = "¥"+str(data.evaluation);
        document.getElementById("evaluation").innerHTML = "¥"+str(data.evaluation);
        document.getElementById("result").innerHTML = str(data.result);
        document.getElementById("value").href = data.url;
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});

$("#submit").on('click',function () {
    storage.removeItem('patent_id');
    window.location.href = "/ipnet/Person-IP-list";
});