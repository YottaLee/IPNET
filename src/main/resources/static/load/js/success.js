var storage = window.localStorage;
var contract = storage.getItem('contract');
$.ajax({
    type: "POST",
    url: "/applicant/loanSuccess",
    async: false,
    data: {
        loanID: storage.getItem('loan_id')
    },
    success: function () {
        $.ajax({
            type: "POST",
            url: "http://120.79.232.126:3000/AddAssetLoan",
            async: false,
            dataType: "application/json",
            data: contract,
            success: function (transaction) {
                var tooltip = "<div style=\"display: none\" class=\"block-code-full\">\n" +
                    "\t\t\t\t\t<pre><code data-language=\"javascript\" id=\"code-6\">x0p({\n" +
                    "    title: '上链成功',\n" +
                    "    text: '您的交易哈希为" + transaction.transactionId + "',\n" +
                    "    animationType: 'slideDown',\n" +
                    "    icon: 'ok',\n" +
                    "    buttons: [],\n" +
                    "    autoClose: 3000\n" +
                    "});</code></pre>\n" +
                    "</div>";
                document.getElementById("success-info").innerHTML = tooltip;
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


