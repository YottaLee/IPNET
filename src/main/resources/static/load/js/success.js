setTimeout(function () {
    var storage = window.localStorage;
    var contract = storage.getItem('contract');
    console.log(JSON.parse(contract));
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
                url: "http://120.79.232.126:3000/api/AddAssetLoan",
                async: false,
                data: JSON.parse(contract),
                success: function (transaction) {
                    var transactionId = transaction.transactionId;
                    var tooltip = "<div style=\"display: none\" class=\"block-code-full\">\n" +
                        "\t\t\t\t\t<pre><code data-language=\"javascript\" id=\"code-6\">x0p({\n" +
                        "    title: '上链成功',\n" +
                        "    text: '您的交易哈希为" + transactionId.substr(0, 32) + "'\n" +
                        transactionId.substr(32, 32) + ",\n" +
                        "    animationType: 'slideDown',\n" +
                        "    icon: 'ok',\n" +
                        "    buttons: [],\n" +
                        "    autoClose: 3000\n" +
                        "});</code></pre>\n" +
                        "</div>";
                    document.getElementById("success-info").innerHTML = tooltip;
                    var number = 6;
                    var code = document.getElementById('code-' + number).innerText;
                    setTimeout(function () {
                        eval(code);
                    }, 2000);
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

