window.onload = function () {
    var storage = window.localStorage;
    var userID = storage.getItem("user_id");
    $.ajax({
        type: "GET",
        url: "/user/getUserRole",
        async: false,
        data: {
            userID: userID
        },
        success: function (userRole) {
            console.log(userRole);
            showPersonInfo(userID, userRole);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }

    });

}


function showPersonInfo(userID, userRole) {
    $.ajax({
        url: "/userInfo/getUser",
        type: "POST",
        async: false,
        data: {
            userid: userID,
            userType: userRole
        },
        success: function (data) {
            if (data != null) {
                document.getElementById("name").value = data["name"];
                document.getElementById("phone").value = data["phone"];
                document.getElementById("company").value = data["company"];
                document.getElementById("region").value = data["region"];
                document.getElementById("gender").value = data["gender"];
                document.getElementById("profession").value = data["profession"];
                document.getElementById("statement").value = data["statement"];
                document.getElementById("p_id_card").value = data["IDcard_img"];  //显示图片路径或名称！？
            }
            else {
                document.getElementById("name").value = "";
                document.getElementById("phone").value = "";
                document.getElementById("company").value = "";
                document.getElementById("region").value = "";
                document.getElementById("gender").value = "";
                document.getElementById("profession").value = "";
                document.getElementById("statement").value = "";
                //document.getElementById("p_id_card").value = "";
            }
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function submitPersonInfo(op_type) {
    var storage = window.localStorage;
    var userID = storage.getItem("user_id");

    var name = document.getElementById("name").value;
    var phone = document.getElementById("phone").value;
    var company = document.getElementById("company").value;
    var region = document.getElementById("region").value;
    var gender = document.getElementById("gender").value;
    var profession = document.getElementById("profession").value;
    var statement = document.getElementById("statement").value;
    var img_E = document.getElementById("p_id_card").value;  //图片路径或名称！？

    if (op_type == "Submit" && (name == "" || phone == "" || company == "" || region == "" ||
        gender == "" || profession == "" || statement == "" || img_E == "")) {
        alert("身份信息不完整，请完善！");
    }
    else {
        var personalUserSaveVo = {
            id: userID,
            name: name,
            phone: phone,
            company: company,
            region: region,
            gender: gender,
            profession: profession,
            statement: statement,
            IDcard_img: img_E
        };

        //应该还需要userID ？！

        $.ajax({
            url: "/userInfo/modifyPerson",
            type: "POST",
            async: false,
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(personalUserSaveVo),
            success: function (data) {
                if (data == "Success") {
                    registerParticipant(userID,name);
                    alert("保存/提交成功！");
                    window.location.href = "/ipnet/pc_identifiedP";
                }
                else {
                    console.log("保存/提交失败！");
                }
            },
            error: function (error) {
                console.log(error);
            }
        });

        if (op_type == "Submit") {
            //提醒管理员审核！？？

        }
    }
}

function registerParticipant(id,name){
    console.log(id);
    $.ajax({
        url: "http://120.79.232.126:3000/api/PrivateIndividual",
        type: "POST",
        dataType: "json", //指定服务器返回的数据类型
        data: {
            $class: "org.acme.ipregistry.PrivateIndividual",
            id: id,
            name: name,
            balance: 0
        },
        success: function (data) {
            console.log(data);
        },
        error: function (error) {
            console.log(error);
        }
    });
}
