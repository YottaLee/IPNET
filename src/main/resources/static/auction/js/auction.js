var storage = window.localStorage;
var loanID = storage.getItem('loan_id');
// $.ajax({
//     type: 'GET',
//     url: '/bank/getInfo',
//     async: false,
//     data: {
//         loanID: loanID
//     },
//     success: function () {
//         document.getElementById("patentID").innerHTML = loan.patentID;
//         document.getElementById("patent").innerHTML = loan.patent;
//     },
//     error: function (error) {
//         console.log(error);
//     }
// });

setTimeout(function () {
    document.getElementById("user4").click();
    setTimeout(function () {
        document.getElementById("user6").click();
        setTimeout(function () {
            document.getElementById("user3").click();
            setTimeout(function () {
                var number = 6;
                var code = document.getElementById('code-' + number).innerText;
                eval(code);
                setTimeout(function () {
                    window.location.href = "/ipnet/home"
                    // if (AC_FL_RunContent == 0) {
                    //     alert("此页需要 AC_RunActiveContent.js");
                    // } else {
                    //     AC_FL_RunContent(
                    //         'codebase', 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0',
                    //         'width', '450',
                    //         'height', '300',
                    //         'src', 'hammar',
                    //         'quality', 'high',
                    //         'pluginspage', 'http://www.macromedia.com/go/getflashplayer',
                    //         'align', 'middle',
                    //         'play', 'true',
                    //         'loop', 'true',
                    //         'scale', 'showall',
                    //         'wmode', 'transparent',
                    //         'devicefont', 'false',
                    //         'id', 'hammar',
                    //         'bgcolor', 'rgba(',
                    //         'name', 'hammar',
                    //         'menu', 'true',
                    //         'allowFullScreen', 'false',
                    //         'allowScriptAccess', 'sameDomain',
                    //         'movie', '/auction/hammar/hammar',
                    //         'salign', ''
                    //     ); //end AC code
                    // }
                    // setTimeout(function () {
                    //     window.location.href = "/ipnet/Insurance-IP-list"
                    // },10000);

                }, 3000);
            }, 3000);
        }, 3000);
    }, 3000);
}, 3000);







