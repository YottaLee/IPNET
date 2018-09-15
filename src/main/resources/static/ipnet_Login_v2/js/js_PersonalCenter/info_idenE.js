window.onload = function () {
    var storage = window.localStorage;
    var userID = storage.getItem("user_id");
    showCompanyInfo(userID);
}


function showCompanyInfo(userID) {
    $.ajax({
        url: "/userInfo/getUser",
        type: "POST",
        async: false,
        dataType: "json",
        data: {userid: userID},
        success: function (data) {
            var userInfoVo = data;
            if(data != null){
                document.getElementById("name").value = data["name"];   //vo信息不全！？
                document.getElementById("representative").value = data[""];
                document.getElementById("foundation").value = data[""];
                document.getElementById("address").value = data[""];
                document.getElementById("phone").value = data[""];
                document.getElementById("statement").value = data[""];
                document.getElementById("field").value = data[""];
                document.getElementById("type").value = data[""];
                document.getElementById("fund").value = data[""];
                document.getElementById("duration").value = data[""];
                document.getElementById("officialWeb").value = data[""];
                document.getElementById("email").value = data[""];
                document.getElementById("p_id_card").value = data[""];
            }
            else{
                document.getElementById("name").value = "";
                document.getElementById("representative").value = "";
                document.getElementById("foundation").value = "";
                document.getElementById("address").value = "";
                document.getElementById("phone").value = "";
                document.getElementById("statement").value = "";
                document.getElementById("field").value = "";
                document.getElementById("type").value = "";
                document.getElementById("fund").value = "";
                document.getElementById("duration").value = "";
                document.getElementById("officialWeb").value = "";
                document.getElementById("email").value = "";
                document.getElementById("p_id_card").value = "";
            }
        },
        error: function () {
            alert("Error!");
        }
    });
}

function submitCompanyInfo(op_type) {
    var storage = window.localStorage;
    var userID = storage.getItem("user_id");

    var name = document.getElementById("name").value;
    var representative = document.getElementById("representative").value;
    var foundation = document.getElementById("foundation").value;
    var address = document.getElementById("address").value;
    var phone = document.getElementById("phone").value;
    var statement = document.getElementById("statement").value;
    var field = document.getElementById("field").value;
    var type = document.getElementById("type").value;
    var fund = document.getElementById("fund").value;
    var duration = document.getElementById("duration").value;
    var officialWeb = document.getElementById("officialWeb").value;
    var email = document.getElementById("email").value;
    var img_E = document.getElementById("p_id_card").value;

    if(op_type == "Submit" && (name == "" || representative == "" || foundation == "" || address == "" || phone == "" || statement == "" || field == "" ||
            type == "" || fund == "" || duration == "" || officialWeb == "" || email == "" || img_E == "")){
        alert("身份信息不完整，请完善！");
    }
    else{
        var companyUserSaveVo = {
            name: name,
            representative: representative,
            foundation: foundation,
            address: address,
            phone: phone,
            statement: statement,
            field: field,
            type: type,
            fund: fund,
            duration: duration,
            officialWeb: officialWeb,
            email: email,
            IDcard_img: img_E
        };

        //应该还需要userID ？！

        $.ajax({
            url: "/userInfo/modifyCompany",
            type: "POST",
            async: false,
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(companyUserSaveVo),
            success: function (data) {
                if(data == "Success"){
                    window.location.href = "/ipnet/pc_identifiedE";
                }
                else{
                    alert("保存/提交失败！");
                }
            },
            error: function () {
                alert("Error!");
            }
        });

        if(op_type == "Submit"){
            //提醒管理员审核！？？

        }
    }
}
