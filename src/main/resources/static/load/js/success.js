var storage = window.localStorage;
var contract = storage.getItem('contract');
console.log(JSON.parse(contract));
setTimeout(function () {
    $.ajax({
        type: "POST",
        url: "http://120.79.232.126:3000/api/AddAssetLoan",
        data: JSON.parse(contract),
        success: function (transaction) {
            var transactionId = transaction.transactionId;
            $.ajax({
                type: "POST",
                url: "/applicant/loanSuccess",
                data: {
                    loanID: storage.getItem('loan_id'),
                    transactionId: transactionId
                },
                success: function () {
                    var number = 6;
                    var code = document.getElementById('code-' + number).innerText;
                    eval(code);
                    setTimeout(function () {
                        window.location.href = "/ipnet/home";
                    }, 3000);
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
}, 3000);

