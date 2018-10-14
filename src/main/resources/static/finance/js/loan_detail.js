var storage = window.localStorage;
var loanID = storage.getItem('loan_id');
$.ajax({
    type: 'GET',
    url: '/bank/getInfo',
    data: {
        loanID: loanID
    },
    success: function (data) {
        document.getElementById("patentID").innerHTML = data.patentID;
        document.getElementById("patent").innerHTML = data.patent;
        document.getElementById("holder").innerHTML = data.person;
        document.getElementById("bank").innerHTML = data.bank;
        document.getElementById("time").innerHTML = data.time;
        document.getElementById("evaluation").innerHTML = data.evaluation;
        document.getElementById("state").innerHTML = data.loan_state;
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});

$("#exit").on('click', function () {
    storage.removeItem('loan_id');
    var userId = storage.getItem('user_id');
    console.log(userId);
    $.ajax({
        type: "GET",
        url: "/user/getUserRole",
        data: {
            userID: userId
        },
        success: function (data) {
            switch (data) {
                case "CompanyUser":
                case "PersonalUser":
                    window.location.href = "/ipnet/Person-IP-list";
                    break;
                case "Evaluator":
                    window.location.href = "/ipnet/Evaluation-IP-list";
                    break;//评估机构界面
                case "Financial":
                    window.location.href = "/ipnet/Bank-IP-list";
                    break;//金融机构界面
                case "Insurance":
                    window.location.href = "/ipnet/Insurance-IP-list";
                    break;//保险公司界面
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }

    });
});