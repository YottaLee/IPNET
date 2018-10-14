var storage = window.localStorage;
var patentID = storage.getItem('patent_id');
$.ajax({
    type: "GET",
    url: "/evaluation/getEvaluation",
    data: {
        patentID: patentID
    },
    success: function (data) {
        document.getElementById("patent").innerHTML = data.patent;
        document.getElementById("rule").innerHTML = data.rule;
        document.getElementById("tech").innerHTML = data.tech;
        document.getElementById("finance").innerHTML = data.evaluation;
        document.getElementById("evaluation").innerHTML = data.evaluation;
        document.getElementById("result").innerHTML = data.result;
        document.getElementById("value").href = data.url;
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});

$("#submit").on('click', function () {
    storage.removeItem('patent_id');
    window.location.href = "/ipnet/Person-IP-list";
});