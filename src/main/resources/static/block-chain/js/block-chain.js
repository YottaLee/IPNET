// showChain();
// test();

function test() {
    $.ajax({
        url: "http://120.79.232.126:3000/api/Bank",
        type: "POST",
        dataType: "json", //指定服务器返回的数据类型
        data: {
            $class: "org.acme.ipregistry.Bank",
            id: "222@qq.com",
            name: "a银行",
            balance: 23331.0
        },
        success: function (data) {
            console.log(data);
        },
        error: function () {
            console.log("Fail!!!!!!!!");
        }
    });
}

function showChain() {
    var storage = window.localStorage;
    var patentID = storage.getItem('patent_id');
    var ipChain = "";


    $.ajax({
        type: "GET",
        url: "/Patent/searchPatentByID",
        data: {
            patentID: patentID
        },
        success: function (patent) {
            var year = apply_date.substr(0, 4);
            var date = apply_date.substr(4, 4);
            ipChain += "<div class=\"row\">\n" +
                "                <article class=\"card\" style=\"position: relative;left: 12%;top: 220px;\">\n" +
                "                    <header class=\"card__thumb\">\n" +
                "                        <a href=\"#\"><img style=\"height: 245px;width: 270px;\" src=\"https://ipnet10.oss-cn-beijing.aliyuncs.com/%E5%9B%BE%E7%89%87/%E9%A6%96%E9%A1%B5%E6%8E%A8%E8%8D%90%E6%98%BE%E7%A4%BA%E7%94%A8%E5%9B%BE%E7%89%87/111123092452.gif\"/></a>\n" +
                "                    </header>\n" +
                "                    <date class=\"card__date\">\n" +
                "                        <span class=\"card__date__day\">" + year + "</span>\n" +
                "                        <br/>\n" +
                "                        <span class=\"card__date__month\">" + date.substr(0, 2) + "." + date.substr(2, 2) + "</span>\n" +
                "                    </date>\n" +
                "                    <div class=\"card__body\">\n" +
                "                        <div class=\"card__category\"><a href=\"#\">专利信息</a></div>\n" +
                "                        <h2 class=\"card__title\"><a href=\"#\">" + patent.patent_name + "</a></h2>\n" +
                "                        <div class=\"card__subtitle\">CN201282323</div>\n" +
                "                        <p class=\"card__description\">属性：23<br/>高度：0<br/>大小：1.01M<br/>时间：2018.10.2<br/>播报方；王春华<br/>块哈希：sdhiawdhioahwhdaodhawhdiadhaowdhiadiadadhaodhad</p>\n" +
                "                    </div>\n" +
                "                    <footer class=\"card__footer\">\n" +
                "                        <span class=\"icon ion-clock\"></span> 10分钟前\n" +
                "                        <span class=\"icon\"></span><i class=\"fas fa-cube\"></i> 3.1M</a>\n" +
                "                    </footer>\n" +
                "                </article>\n" +
                "                <article class=\"card\" style=\"position: absolute;left: 36%;top: 57.5%;\">\n" +
                "                    <header class=\"card__thumb\">\n" +
                "                        <a href=\"#\"><img style=\"height: 245px;width: 270px;\" src=\"https://ipnet10.oss-cn-beijing.aliyuncs.com/%E5%9B%BE%E7%89%87/%E9%A6%96%E9%A1%B5%E6%8E%A8%E8%8D%90%E6%98%BE%E7%A4%BA%E7%94%A8%E5%9B%BE%E7%89%87/111123092452.gif\"/></a>\n" +
                "                    </header>\n" +
                "                    <date class=\"card__date\">\n" +
                "                        <span class=\"card__date__day\">2018</span>\n" +
                "                        <br/>\n" +
                "                        <span class=\"card__date__month\">10.19</span>\n" +
                "                    </date>\n" +
                "                    <div class=\"card__body\">\n" +
                "                        <div class=\"card__category\"><a href=\"#\">专利信息</a></div>\n" +
                "                        <h2 class=\"card__title\"><a href=\"#\">新型感应鼠标</a></h2>\n" +
                "                        <div class=\"card__subtitle\">CN201282323</div>\n" +
                "                        <p class=\"card__description\">属性：23<br/>高度：0<br/>大小：1.01M<br/>时间：2018.10.2<br/>播报方；王春华<br/>块哈希：sdhiawdhioahwhdaodhawhdiadhaowdhiadiadadhaodhad</p>\n" +
                "                    </div>\n" +
                "                    <footer class=\"card__footer\">\n" +
                "                        <span class=\"icon ion-clock\"></span> 10分钟前\n" +
                "                        <span class=\"icon\"></span><i class=\"fas fa-cube\"></i> 3.1M</a>\n" +
                "                    </footer>\n" +
                "                </article>\n" +
                "                <article class=\"card\" style=\"position: absolute;left: 60%;top: 57.5%;\">\n" +
                "                    <header class=\"card__thumb\">\n" +
                "                        <a href=\"#\"><img style=\"height: 245px;width: 270px;\" src=\"https://ipnet10.oss-cn-beijing.aliyuncs.com/%E5%9B%BE%E7%89%87/%E9%A6%96%E9%A1%B5%E6%8E%A8%E8%8D%90%E6%98%BE%E7%A4%BA%E7%94%A8%E5%9B%BE%E7%89%87/111123092452.gif\"/></a>\n" +
                "                    </header>\n" +
                "                    <date class=\"card__date\">\n" +
                "                        <span class=\"card__date__day\">2018</span>\n" +
                "                        <br/>\n" +
                "                        <span class=\"card__date__month\">10.19</span>\n" +
                "                    </date>\n" +
                "                    <div class=\"card__body\">\n" +
                "                        <div class=\"card__category\"><a href=\"#\">专利信息</a></div>\n" +
                "                        <h2 class=\"card__title\"><a href=\"#\">新型感应鼠标</a></h2>\n" +
                "                        <div class=\"card__subtitle\">CN201282323</div>\n" +
                "                        <p class=\"card__description\">属性：23<br/>高度：0<br/>大小：1.01M<br/>时间：2018.10.2<br/>播报方；王春华<br/>块哈希：sdhiawdhioahwhdaodhawhdiadhaowdhiadiadadhaodhad</p>\n" +
                "                    </div>\n" +
                "                    <footer class=\"card__footer\">\n" +
                "                        <span class=\"icon ion-clock\"></span> 10分钟前\n" +
                "                        <span class=\"icon\"></span><i class=\"fas fa-cube\"></i> 3.1M</a>\n" +
                "                    </footer>\n" +
                "                </article>\n" +
                "                <article class=\"card\" style=\"position: absolute;left: 84%;top: 57.5%;\">\n" +
                "                    <header class=\"card__thumb\">\n" +
                "                        <a href=\"#\"><img style=\"height: 245px;width: 270px;\" src=\"https://ipnet10.oss-cn-beijing.aliyuncs.com/%E5%9B%BE%E7%89%87/%E9%A6%96%E9%A1%B5%E6%8E%A8%E8%8D%90%E6%98%BE%E7%A4%BA%E7%94%A8%E5%9B%BE%E7%89%87/111123092452.gif\"/></a>\n" +
                "                    </header>\n" +
                "                    <date class=\"card__date\">\n" +
                "                        <span class=\"card__date__day\">2018</span>\n" +
                "                        <br/>\n" +
                "                        <span class=\"card__date__month\">10.19</span>\n" +
                "                    </date>\n" +
                "                    <div class=\"card__body\">\n" +
                "                        <div class=\"card__category\"><a href=\"#\">专利信息</a></div>\n" +
                "                        <h2 class=\"card__title\"><a href=\"#\">新型感应鼠标</a></h2>\n" +
                "                        <div class=\"card__subtitle\">CN201282323</div>\n" +
                "                        <p class=\"card__description\">属性：23<br/>高度：0<br/>大小：1.01M<br/>时间：2018.10.2<br/>播报方；王春华<br/>块哈希：sdhiawdhioahwhdaodhawhdiadhaowdhiadiadadhaodhad</p>\n" +
                "                    </div>\n" +
                "                    <footer class=\"card__footer\">\n" +
                "                        <span class=\"icon ion-clock\"></span> 10分钟前\n" +
                "                        <span class=\"icon\"></span><i class=\"fas fa-cube\"></i> 3.1M</a>\n" +
                "                    </footer>\n" +
                "                </article>\n" +
                "            </div>";
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }
    });
    $.ajax({
        type: "GET",
        url: "/evaluation/ifValue",
        data: {
            patentID: patentID
        },
        success: function (ifValue) {

            if (ifValue) {
                $.ajax({
                    type: "GET",
                    url: "/evaluation/getEvaluation",
                    data: {
                        patentID: patentID
                    },
                    success: function (evaluation) {

                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
                    }
                });
            }
            else {

            }
            document.getElementById("ip_chain").innerHTML = ipChain;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }
    });


}