//获取专利列表
var storage = window.localStorage;
var userId = storage.getItem('user_id');
$.ajax({
    type: "GET",
    url: "/all/getPatentList",
    data: {
        userId: userId
    },
    success: function (data) {
        var patentList = "<table class=\"table table-striped\">\n" +
            "                            <thead>\n" +
            "                            <tr>\n" +
            "                                <th>专利号</th>\n" +
            "                                <th>专利名</th>\n" +
            "                                <th>专利持有人</th>\n" +
            "                                <th>状态</th>\n" +
            "                                <th>详情</th>\n" +
            "                            </tr>\n" +
            "                            </thead>\n" +
            "                            <tbody>";
        for (var i = 0, len = data.length; i < len; i++) {
            patentList += "<tr>\n" +
                "                                <td><a class=\"btn-link\" href=\"#\">" + data[i].patentID + "</a></td>\n" +
                "                                <td>" + data[i].patent + "</td>\n" +
                "                                <td><span class=\"text-muted\">" + data[i].person + "</span></td>\n" +
                "                                <td>\n";
            switch (data[i].loan_state) {
                case "loaning":
                    patentList += "                                    <div class=\"label label-table label-mint\">质押中</div>\n";
                    break;
                case "overdue":
                    patentList += "                                    <div class=\"label label-table label-danger\">逾期</div>\n";
                    break;
                case "free":
                    patentList += "                                    <div class=\"label label-table label-success\">闲置</div>\n";
                    break;
                default:
                    patentList += "                                    <div class=\"label label-table label-warning\">申请质押中</div>\n";
                    break;
            }
            patentList += "                                </td>\n" +
                "                                <td>\n" +
                "                                    <button data-target=\"#demo-lg-modal\" data-toggle=\"modal\" class=\"btn btn-info\"  id=\"check-" + data[i].loanID + "\"onclick=\"check(this.id)\">详情</button>\n" +
                "                                </td>\n" +
                "                            </tr>";
        }
        patentList += "   </tbody>\n" +
            "                        </table>";
        document.getElementById("ip_list").innerHTML = patentList;

    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
    }
});


function check(loanID) {
    loanID = (loanID + "").substring((loanID + "").indexOf("-") + 1);
    var storage = window.localStorage;
    storage.setItem('loan_id', loanID);
    $.ajax({
        type: 'GET',
        url: '/bank/getInfo',
        async: false,
        data: {
            loanID: loanID
        },
        success: function (data) {

            var state = data.loan_state;
            switch (state) {
                case "to_be_checked_by_insurance":
                    window.location.href = "/ipnet/Insurance-check2";
                    break;
                case "overdue":
                    $.ajax({
                        type: "GET",
                        url: "/insurance/hasInsurance",
                        data: {
                            loanId: loanID
                        },
                        success: function (result) {
                            if (result == "Success") {
                                infoFile("已进行自动赔付，贵公司已获得该专利所有权，现在将进行拍卖");
                                setTimeout(function () {
                                    window.location.href = "/ipnet/Auction";
                                }, 4000);
                            }

                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
                        }
                    });
                    //赔付界面
                    //window.location.href = "/ipnet/Insurance-checkBank";
                    break;
                default:
                    window.location.href = "/ipnet/loan_detail";
                    //专利详情界面
                    break;
            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }

    });

}


var minute = 60;
var second = 60;
var hour = 24;
var timer1 = setInterval("examine()", 1000);

function examine() {
    //检测是否到达期限
    //每天取出一次时间戳，看是否到达期限
    $.ajax({
        type: 'GET',
        url: '/bank/getInfo',
        async: false,
        data: {
            loanID: "CN201710139269.120181025160817"
        },
        success: function (loan) {
            if (loan.transactionId != null) {
                var transactionId = loan.transactionId;
                console.log(transactionId);
                $.ajax({
                    type: 'GET',
                    url: 'http://120.79.232.126:3000/api/AddAssetLoan',
                    // url: 'http://localhost:3000/api/AddAssetLoan',
                    data: {
                        transactionId: transactionId
                    },
                    success: function (data) {
                        console.log(data);
                        var timestamp = data[0].timestamp;
                        var timeArr = timestamp.split("-");
                        var year = timeArr[0];
                        var month = timeArr[1];
                        var months = data.durationInMonths;
                        month += months;
                        var endYear = year + month / 12;
                        var endMonth = month % 12;
                        var date = new Date();
                        // if (endYear == date.getFullYear() && endMonth == date.getMonth() + 1) {
                            //如果到达，则执行智能合约
                            $.ajax({
                                type:'POST',
                                // url:'http://120.79.232.126:3000/api/CompensatingInsurance',
                                url:'http://localhost:3000/api/CompensatingInsurance',
                                async:false,
                                data:{
                                    $class: "org.acme.ipregistry.CompensatingInsurance",
                                    insuranceID: "20181026-144054"
                                }
                            });
                            //改贷款的状态
                            $.ajax({
                                type: 'POST',
                                url: '/insurance/overdue',
                                async:false,
                                data: {
                                    loanId: "CN201710139269.120181025160817"
                                },
                                success: function () {

                                },
                                error: function (error) {
                                    console.log(error);
                                }

                            })
                            clearInterval(timer1);
                            infoFile("有专利持有人未还款，您已获得该专利所有权，您可以选择拍卖");
                            setTimeout(function () {
                                window.location.href = "/ipnet/Auction";
                            }, 2000);

                            //
                        // }
                    },
                    error: function (error) {
                        console.log(error);
                    }

                })
            }
        },
        error: function (error) {
            console.log(error);
        }
    });


}

// // doSomething();
// var timer1;
// var tmp = 1;
// function doSomething(){
//     //有延迟的事件（示例为10s后改变tmp的值为2）
//     setTimeout("changenum()",10000);
//     //每隔1s检查一次
//     timer1 = setInterval("check()",1000);
// }
// function check(){
//     //检测操作
//     alert("check!");
//     //检测到延迟事件完成后执行操作
//     if(tmp==2){
//         clearInterval(timer1);
//         alert("Done！Do next...");
//     }
// }
// function changenum(){
//     tmp=2;
// }