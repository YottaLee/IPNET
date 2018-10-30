var storage = window.localStorage;
var loanID = storage.getItem('loan_id');
var userId = storage.getItem('user_id');
var loan;
$(".Bank-money").val(300000); //贷款费用
var userRole = "";
var timeLong = 0;
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
        $(".date").val(myDate.getFullYear() + "年" + (myDate.getMonth() + 1) + "月" + myDate.getDate() + "日");
        $(".place").val("汉口路");
        timeLong = parseInt(data.time.substr(0, 1));
        $(".duration").val("即日至"
            + (myDate.getFullYear() + timeLong) + "年" + (myDate.getMonth() + 1) + "月" + myDate.getDate() + "日");
        $(".insurance").val(data.insurance);
        $(".evaluation").val(data.evaluation);
        $(".year").val(myDate.getFullYear());
        $(".month").val(myDate.getMonth() + 1);
        $(".currentDay").val(myDate.getDate());
        if (userRole == "PersonalUser") {
            $(".loan").val(data.money); //贷款金额
            $(".Bank-money").val(data.return_money); //保管费用
            $(".day").val(data.time);
            $(".compensation").val(data.compensation); //赔偿
        }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});


$("#submit").on('click', function () {

    var loanMoney = $(".loan").val(); //贷款金额
    var returnMoreMoney = $(".Bank-money").val(); //保管费用
    var bankDay = $(".day").val();
    var compensation = $(".compensation").val(); //赔偿
    if (userRole == "PersonalUser") {
        var contract = {
            $class: "org.acme.ipregistry.AddAssetLoan",
            loan: {
                $class: "org.acme.ipregistry.Loan",
                id: loanID + "00",
                amount: loanMoney,                     //贷款金额
                interestRate: returnMoreMoney,               //贷款利率
                debtorID: userId,                   //贷款人
                bankID: loan.bankId,                     //贷款银行
                ipID: loan.patentID,                       //IP 知识产权
                compensation: compensation,               //赔偿
                durationInMonths: timeLong * 12          //贷款期限
            }
        };
        storage.setItem('contract', JSON.stringify(contract));
        infoFile("即将跳入花旗界面进行身份认证");
        document.getElementById("link").click();
    }
    else {
        var saveInfo = {
            loanID: loanID,
            loanMoney: loanMoney,
            returnMoreMoney: returnMoreMoney,
            duration: bankDay,
            compensation: compensation
        };
        storage.setItem('save_info', JSON.stringify(saveInfo));
        infoFile("即将跳入花旗界面进行身份认证");
        document.getElementById("bankLink").click();
    }
});