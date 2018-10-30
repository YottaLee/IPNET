$(document).ready(function () {

    var user_id = localStorage.getItem("user_id");

    var current_pay_card = "";
    var current_receive_card = "";

    var way_para = "";

    var payer_role = "";//付款方角色
    var payee_role = "";//收款方角色

    var pay_order = JSON.parse(localStorage.getItem("pay_order"));
    $("#patent_id").text(pay_order.patentID);
    $("#patent_name").text(pay_order.patent);
    $("#payer").text(pay_order.payer);
    $("#payee").text(pay_order.payee);
    $("#way").text(pay_order.way);
    $("#money").text(pay_order.money);

    console.log(JSON.stringify(pay_order));

    if (pay_order.way == "专利许可") {
        way_para = "license";
    } else if (pay_order.way == "专利转让") {
        way_para = "transfer";
    } else if (pay_order.way == "专利评估") {
        way_para = "evaluation";
    } else if (pay_order.way == "专利贷款") {
        way_para = "loan";
    } else if (pay_order.way == "专利保险") {
        way_para = "insurance";
    } else {
        way_para = "claim";
    }
    console.log(way_para);

    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    $("#time").text(currentdate);

    $.ajax({
        type: 'GET',
        url: '/user/getUserRole',
        data: {userID: pay_order.payer_id},
        async: false,
        success: function (data) {
            payer_role = data;
        },
        error: function (data) {

        }
    });

    $.ajax({
        type: 'GET',
        url: '/user/getUserRole',
        data: {userID: pay_order.payee_id},
        async: false,
        success: function (data) {
            payee_role = data;
        },
        error: function (data) {

        }
    });

    $.ajax({
        type: 'POST',
        url: '/userInfo/getAllAccountId',
        data: {userId: pay_order.payer_id, userType: payer_role},
        async: false,
        success: function (data) {
            console.log("payer_account:" + data.length + "   " + payer_role);
            for (var i = 0; i < data.length; i++) {
                var new_element = "<div class=\"cover atvImg\" >\n" +
                    "                <div class=\"atvImg-layer pay\" data-img=\"https://i.imgur.com/R8xnkBw.png\" data-number='" + data[i] + "'></div>\n" +
                    "            </div>";
                $("#pay_card_list").append(new_element);
            }
        },
        error: function (data) {

        }
    });

    $.ajax({
        type: 'POST',
        url: '/userInfo/getAllAccountId',
        data: {userId: pay_order.payee_id, userType: payee_role},
        async: false,
        success: function (data) {
            console.log("payee_account:" + data.length + "   " + payee_role);
            for (var i = 0; i < data.length; i++) {
                var new_element = "<div class=\"cover atvImg\" >\n" +
                    "                <div class=\"atvImg-layer receive\" data-img=\"https://i.imgur.com/R8xnkBw.png\" data-number='" + data[i] + "'></div>\n" +
                    "            </div>";
                $("#receive_card_list").append(new_element);
            }
        },
        error: function (data) {

        }
    });

    atvImg();

    $(".atvImg").click(function () {
        if ($(this).find(".receive_card_number").attr("data-number") == undefined) {
            current_pay_card = $(this).find(".pay_card_number").attr("data-number");
        } else {
            current_receive_card = $(this).find(".receive_card_number").attr("data-number");
        }
        $(this).find(".checked").show();
        $(this).siblings().find(".checked").hide();
    })

    $('.confirm_pay').on('click', function () {
        $.confirm({
            title: '是否确认付款',
            content: '请确认订单信息合同信息等无误后进行付款 <br> 确认付款后将无法退还付款金额',
            confirmButton: '确认付款',
            cancelButton: '取消',
            confirmButtonClass: 'btn-info',
            icon: 'fa fa-question-circle',
            animation: 'rotate',
            closeAnimation: 'right',
            opacity: 0.5,
            confirm: function () {
                if (current_pay_card == "" || current_receive_card == "") {
                    $.alert({
                        title: '请先选择银行卡!',
                        content: '您还未选择付款或收款的银行卡，请先选中银行卡',
                        confirmButton: '我知道了',
                        confirmButtonClass: 'btn-primary',
                        icon: 'fa fa-info',
                        animation: 'zoom',
                        confirm: function () {

                        }
                    });
                } else {
                    $.ajax({
                        type: 'POST',
                        url: '/MoneyMove',
                        data: {
                            srcAccount: current_pay_card,
                            destAccount: current_receive_card,
                            amount: pay_order.money,
                            paytype: way_para,
                            patentId: pay_order.patentID
                        },
                        async: false,
                        success: function (data) {

                        },
                        error: function () {
                            // alert("fail");
                        }
                    });
                    switch (way_para) {
                        case "evaluation":
                            var storage = window.localStorage;
                            var loanID = storage.getItem('loan_id');
                            console.log(loanID);
                            if (loanID == null || loanID == "") {
                                storage.removeItem("patent_id");
                                window.location.href = "/ipnet/Person-IP-list";
                            }
                            else {
                                $.ajax({
                                    url: '/applicant/changeEvaluationState',
                                    type: 'POST',
                                    data: {
                                        loanID: loanID
                                    },
                                    success: function () {
                                        window.location.href = "/ipnet/Person-IP-list";
                                    },
                                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                                        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
                                    }
                                });
                            }
                            break;
                        case "insurance":
                            var storage = window.localStorage;
                            var loanID = storage.getItem('loan_id');
                            var patentID = storage.getItem('patent_id');
                            console.log(loanID);
                            if (loanID == null || loanID == "") {
                                storage.removeItem("patent_id");
                                window.location.href = "/ipnet/Person-IP-list";
                            }
                            else {
                                $.ajax({
                                    url: '/applicant/successPayForInsurance',
                                    type: 'POST',
                                    data: {
                                        loanID: loanID
                                    },
                                    success: function (insuranceId) {
                                        $.ajax({
                                            url: 'http://120.79.232.126:3000/api/AddAssetInsurance',
                                            type: 'POST',
                                            async: false,
                                            data: {
                                                $class: "org.acme.ipregistry.AddAssetInsurance",
                                                insurance: {
                                                    $class: "org.acme.ipregistry.Insurance",
                                                    id: insuranceId,
                                                    ioanID: loanID,
                                                    ipID: pay_order.patentID,
                                                    insuredID: pay_order.payer,
                                                    insuranceCompanyID: pay_order.payee,
                                                    monthlyCost: 0, //保险费用操作
                                                    durationInMonths: 0
                                                }
                                            },
                                            success:function () {
                                                window.location.href = "/ipnet/Person-IP-list";
                                            },
                                            error:function (error) {
                                                console.log(error);
                                            }
                                        });


                                    },
                                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                                        console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
                                    }
                                });
                            }
                            break;
                    }


                    $.alert({
                        title: '付款成功!',
                        content: '您已成功付款，此次交易成功',
                        confirmButton: '我知道了',
                        confirmButtonClass: 'btn-primary',
                        icon: 'fa fa-info',
                        animation: 'zoom',
                        confirm: function () {

                        }
                    });
                }
            }
        });
    });

    $('.cancel_pay').on('click', function () {
        $.confirm({
            title: '是否确认取消付款',
            content: '如果订单信息有误或合同细则仍令您不满意，您可以选择取消付款 <br> 若是双方均已达成一致的交易请您不要轻易取消',
            confirmButton: '确认付款',
            cancelButton: '取消',
            confirmButtonClass: 'btn-info',
            icon: 'fa fa-question-circle',
            animation: 'rotate',
            closeAnimation: 'right',
            opacity: 0.5,
            confirm: function () {
                $.alert({
                    title: '取消成功!',
                    content: '您已成功取消此次交易',
                    confirmButton: '我知道了',
                    confirmButtonClass: 'btn-primary',
                    icon: 'fa fa-info',
                    animation: 'zoom',
                    confirm: function () {

                    }
                });
            }
        });
    });

})
;

function atvImg() {

    var d = document,
        de = d.documentElement,
        bd = d.getElementsByTagName('body')[0],
        htm = d.getElementsByTagName('html')[0],
        win = window,
        imgs = d.querySelectorAll('.atvImg'),
        totalImgs = imgs.length,
        supportsTouch = 'ontouchstart' in win || navigator.msMaxTouchPoints;

    if (totalImgs <= 0) {
        return;
    }

    for (var l = 0; l < totalImgs; l++) {

        var thisImg = imgs[l],
            layerElems = thisImg.querySelectorAll('.atvImg-layer'),
            totalLayerElems = layerElems.length;

        if (totalLayerElems <= 0) {
            continue;
        }

        while (thisImg.firstChild) {
            thisImg.removeChild(thisImg.firstChild);
        }

        var containerHTML = d.createElement('div'),
            shineHTML = d.createElement('div'),
            shadowHTML = d.createElement('div'),
            layersHTML = d.createElement('div'),
            layers = [];

        thisImg.id = 'atvImg__' + l;
        containerHTML.className = 'atvImg-container';
        shineHTML.className = 'atvImg-shine';
        shadowHTML.className = 'atvImg-shadow';
        layersHTML.className = 'atvImg-layers';

        for (var i = 0; i < totalLayerElems; i++) {

            var layer = d.createElement('div'),
                imgSrc = layerElems[i].getAttribute('data-img');

            layer.className = 'atvImg-rendered-layer';
            layer.setAttribute('data-layer', i + "");
            layer.style.backgroundImage = 'url(' + imgSrc + ')';

            var nums1 = d.createElement('span');
            nums1.setAttribute("style", "color: black;padding-left: 30px");
            nums1.innerText = "****";

            var nums2 = d.createElement('span');
            nums2.setAttribute("style", "color: black;padding-left: 40px");
            nums2.innerText = "****";

            var nums3 = d.createElement('span');
            nums3.setAttribute("style", "color: black;padding-left: 40px");
            nums3.innerText = "****";

            var nums4 = d.createElement('span');
            nums4.setAttribute("style", "color: black;padding-left: 40px");
            nums4.innerText = layerElems[i].getAttribute("data-number").substring(12);

            var checked = d.createElement('span');
            checked.setAttribute("style", "display:none;top: 10px;left: 280px;position:absolute");
            checked.setAttribute("class", "checked");
            checked.innerHTML = "<i class=\"fas fa-check-circle\"></i>";

            var letter_area = d.createElement('div');
            if (layerElems[i].className == "atvImg-layer pay") {
                letter_area.setAttribute("class", "pay_card_number");
            } else {
                letter_area.setAttribute("class", "receive_card_number");
            }
            letter_area.setAttribute("style", "margin-top: 40px");
            letter_area.setAttribute("data-number", layerElems[i].getAttribute("data-number"));
            letter_area.appendChild(nums1);
            letter_area.appendChild(nums2);
            letter_area.appendChild(nums3);
            letter_area.appendChild(nums4);

            layer.appendChild(checked);
            layer.appendChild(letter_area);

            layersHTML.appendChild(layer);

            layers.push(layer);
        }

        containerHTML.appendChild(shadowHTML);
        containerHTML.appendChild(layersHTML);
        containerHTML.appendChild(shineHTML);
        thisImg.appendChild(containerHTML);

        var w = thisImg.clientWidth || thisImg.offsetWidth || thisImg.scrollWidth;
        thisImg.style.transform = 'perspective(' + w * 3 + 'px)';

        if (supportsTouch) {
            win.preventScroll = false;

            (function (_thisImg, _layers, _totalLayers, _shine) {
                thisImg.addEventListener('touchmove', function (e) {
                    if (win.preventScroll) {
                        e.preventDefault();
                    }
                    processMovement(e, true, _thisImg, _layers, _totalLayers, _shine);
                });
                thisImg.addEventListener('touchstart', function (e) {
                    win.preventScroll = true;
                    processEnter(e, _thisImg);
                });
                thisImg.addEventListener('touchend', function (e) {
                    win.preventScroll = false;
                    processExit(e, _thisImg, _layers, _totalLayers, _shine);
                });
            })(thisImg, layers, totalLayerElems, shineHTML);
        } else {
            (function (_thisImg, _layers, _totalLayers, _shine) {
                thisImg.addEventListener('mousemove', function (e) {
                    processMovement(e, false, _thisImg, _layers, _totalLayers, _shine);
                });
                thisImg.addEventListener('mouseenter', function (e) {
                    processEnter(e, _thisImg);
                });
                thisImg.addEventListener('mouseleave', function (e) {
                    processExit(e, _thisImg, _layers, _totalLayers, _shine);
                });
            })(thisImg, layers, totalLayerElems, shineHTML);
        }
    }

    function processMovement(e, touchEnabled, elem, layers, totalLayers, shine) {

        var bdst = bd.scrollTop || htm.scrollTop,
            bdsl = bd.scrollLeft,
            pageX = (touchEnabled) ? e.touches[0].pageX : e.pageX,
            pageY = (touchEnabled) ? e.touches[0].pageY : e.pageY,
            offsets = elem.getBoundingClientRect(),
            w = elem.clientWidth || elem.offsetWidth || elem.scrollWidth,
            h = elem.clientHeight || elem.offsetHeight || elem.scrollHeight,
            wMultiple = 320 / w,
            offsetX = 0.52 - (pageX - offsets.left - bdsl) / w,
            offsetY = 0.52 - (pageY - offsets.top - bdst) / h,
            dy = (pageY - offsets.top - bdst) - h / 2,
            dx = (pageX - offsets.left - bdsl) - w / 2,
            yRotate = (offsetX - dx) * (0.07 * wMultiple),
            xRotate = (dy - offsetY) * (0.1 * wMultiple),
            imgCSS = 'rotateX(' + xRotate + 'deg) rotateY(' + yRotate + 'deg)',
            arad = Math.atan2(dy, dx),
            angle = arad * 180 / Math.PI - 90;

        if (angle < 0) {
            angle = angle + 360;
        }

        if (elem.firstChild.className.indexOf(' over') != -1) {
            imgCSS += ' scale3d(1.07,1.07,1.07)';
        }
        elem.firstChild.style.transform = imgCSS;

        shine.style.background = 'linear-gradient(' + angle + 'deg, rgba(255,255,255,' + (pageY - offsets.top - bdst) / h * 0.4 + ') 0%,rgba(255,255,255,0) 80%)';
        shine.style.transform = 'translateX(' + (offsetX * totalLayers) - 0.1 + 'px) translateY(' + (offsetY * totalLayers) - 0.1 + 'px)';

        var revNum = totalLayers;
        for (var ly = 0; ly < totalLayers; ly++) {
            layers[ly].style.transform = 'translateX(' + (offsetX * revNum) * ((ly * 2.5) / wMultiple) + 'px) translateY(' + (offsetY * totalLayers) * ((ly * 2.5) / wMultiple) + 'px)';
            revNum--;
        }
    }

    function processEnter(e, elem) {
        elem.firstChild.className += ' over';
    }

    function processExit(e, elem, layers, totalLayers, shine) {

        var container = elem.firstChild;

        container.className = container.className.replace(' over', '');
        container.style.transform = '';
        shine.style.cssText = '';

        for (var ly = 0; ly < totalLayers; ly++) {
            layers[ly].style.transform = '';
        }

    }

}