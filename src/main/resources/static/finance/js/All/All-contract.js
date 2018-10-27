var storage = window.localStorage;
var loanID = storage.getItem('loan_id');
var userId = storage.getItem('user_id');
var loan;
$(".Bank-money").val(300000); //贷款费用
var userRole = "";
ifPerson();

function ifPerson() {
    $.ajax({
        type: "GET",
        url: "/user/getUserRole",
        async: false,
        data: {
            userID: userId
        },
        success: function (role) {
            userRole = role;
            if (role == "PersonalUser") {

                $(".Bank").attr("disabled", false);
            }
        },
        error: function (error) {
            console.log(error);
        }
    });

}

$.ajax({
    type: 'GET',
    url: '/bank/getInfo',
    async: false,
    data: {
        loanID: loanID
    },
    success: function (data) {
        loan = data;
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
        if (userRole == "PersonalUser") {
            $(".Bank-loan").val(data.money); //贷款金额
            $(".Bank-money").val(data.return_money); //保管费用
            $(".Bank-day").val(data.time);
            $(".Bank-compensation").val(data.compensation); //赔偿
        }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});


$("#submit").on('click', function () {
    document.getElementById("link").click();
    // var loanMoney = $(".Bank-loan").val(); //贷款金额
    // var returnMoreMoney = $(".Bank-money").val(); //保管费用
    // var bankDay = $(".Bank-day").val();
    // var compensation = $(".Bank-compensation").val(); //赔偿
    // if (userRole == "PersonalUser") {
    //     var contract = {
    //         id: loanID,
    //         amount:loanMoney,                     //贷款金额
    //         interestRate:returnMoreMoney,               //贷款利率
    //         debtorID:userId,                   //贷款人
    //         bankID:loan.bankId,                     //贷款银行
    //         ipID:loan.patentID,                       //IP 知识产权
    //         compensation:compensation,               //赔偿
    //         durationInMonths:parseInt(bankDay)          //贷款期限
    //     };
    //     storage.setItem('contract',JSON.stringify(contract));
    //     document.getElementById("link").click();
    // }
    // else {
    //     var saveInfo = {
    //         loanID: loanID,
    //         loanMoney: loanMoney,
    //         returnMoreMoney: returnMoreMoney,
    //         duration: bankDay,
    //         compensation: compensation
    //     };
    //     storage.setItem('save_info',JSON.stringify(saveInfo));
    //
    // }
});