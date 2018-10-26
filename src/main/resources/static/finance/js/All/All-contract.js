var storage = window.localStorage;
var loanID = storage.getItem('loan_id');

$(".Bank-money").val(300000); //贷款费用

$.ajax({
    type: 'GET',
    url: '/bank/getInfo',
    async:false,
    data: {
        loanID: loanID
    },
    success: function (data) {
        var address = ""
        $.ajax({
            type: 'GET',
            url: '/userInfo/getUser',
            async: false,
            data: {
                userid: data.userId,
                userType: "PersonalUser"
            },
            success: function (person) {
                 address = person.company;
            },
            error: function (error) {
                console.log(error);
            }
        });
        document.getElementsByClassName("System-patentHolder").innerHTML = data.person;
        document.getElementsByClassName("System-address").innerHTML = address;
        document.getElementsByClassName("System-bank").innerHTML = data.bank;
        document.getElementsByClassName("System-bank-address").innerHTML = data.bank;
        document.getElementsByClassName("System-date").innerHTML = data.date;
        document.getElementsByClassName("System-place").innerHTML = data.bank;
        document.getElementsByClassName("System-duration").innerHTML = data.time;
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});

$.ajax({
    type: 'GET',
    url: '/bank/getInfo',//有待修改
    async:false,
    data: {
        loanID: loanID
    },
    success: function (data) {
        var address = ""
        $.ajax({
            type: 'GET',
            url: '/userInfo/getUser',
            async: false,
            data: {
                userid: data.userId,
                userType: "PersonalUser"
            },
            success: function (person) {
                address = person.company;
            },
            error: function (error) {
                console.log(error);
            }
        });
        document.getElementsByClassName("Bank-loan").innerHTML = data.person; //放贷金额
        document.getElementsByClassName("Bank-money").innerHTML = address; //贷款费用
        document.getElementsByClassName("Bank-day").innerHTML = data.bank;
        document.getElementsByClassName("System-bank-address").innerHTML = data.bank;
        document.getElementsByClassName("System-date").innerHTML = data.date;
        document.getElementsByClassName("System-place").innerHTML = data.bank;
        document.getElementsByClassName("System-duration").innerHTML = data.time;
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});