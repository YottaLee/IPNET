var storage = window.localStorage;
var patentID = storage.patentID;
$.ajax({
    type: "GET",
    url: "evaluation/getEvaluation",
    dataType: "json",
    data: patentID,
    success: function (data) {
        document.getElementById("patentID").innerHTML = patentID;
        document.getElementById("pur-evaluation").innerHTML = data.evaluation;
        document.getElementById("value").href = data.url;
    },
    error: function () {

    }
});

$("#submit").on('click',function () {
    storage.removeItem('patentID');
    window.location.href = "Person-IP-list.html";
});