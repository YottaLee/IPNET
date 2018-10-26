var storage = window.localStorage;
var loanID = storage.getItem('loan_id');
var userId = storage.getItem('user_id');
$(".Bank-money").val(300000); //贷款费用
var userRole = "";
$.ajax({
    type: 'GET',
    url: '/bank/getInfo',
    async: false,
    data: {
        loanID: loanID
    },
    success: function (data) {
        var myDate = new Date();
        $(".System").attr("disabled", false);
        $(".patent").val(data.patent);
        $(".evaluation").val(data.evaluation);
        $(".patentHolder").val(data.person);
        $(".address").val("中山北路");
        $(".bank-name").val(data.bank);
        $(".bank-address").val("汉口路");
        $(".date").val(myDate.getFullYear() + "年" + myDate.getMonth() + 1 + "月" + myDate.getDate() + "日");
        $(".place").val("汉口路");
        $(".duration").val(data.time);
        $(".insurance").val(data.insurance);
        $(".evaluation").val(data.evaluation);
        $(".year").val(myDate.getFullYear());
        $(".month").val(myDate.getMonth() + 1);
        $(".date").val(myDate.getDate());
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});

ifPerson();

function ifPerson() {
    $.ajax({
        type: "GET",
        url: "/user/getUserRole",
        data: {
            userID: userId
        },
        success: function (role) {
            userRole = role;
            if (role == "PersonalUser")
                $(".Bank").attr("disabled", false);
        },
        error: function (error) {
            console.log(error);
        }
    });

}

$("#confirm").on('click', function () {
    var loanMoney = $(".Bank-loan").val(); //贷款金额
    var returnMoreMoney = $(".Bank-money").val(); //保管费用
    var bankDay = $(".Bank-day").val();
    var compensation = $(".Bank-compensation").val(); //赔偿
    var isSuccess = false;
    if (userRole == "PersonalUser") {
        $.ajax({
            type: "POST",
            url: "/bank/hasContract",
            data: {
                loanID: loanID,
                loanMoney: loanMoney,
                returnMoreMoney: returnMoreMoney,
                duration: bankDay,
                compensation: compensation
            },
            success: function () {
               isSuccess = true;
            },
            error: function (error) {
                console.log(error);
            }
        });
    } else {

    }
});