$('#submit').on('click', function () {
    var storage = window.localStorage;
    // var url = storage.getItem('fileURL');
    var uploadImageURL = storage.getItem('uploadImageURL');
    if (uploadImageURL == null)
        alertFile("请先上传文件");
    else {

        var patentID = $("#patentID").val();
        var patent = $("#patent").val();
        var holder = $("#holder").val();
        var applyTime = $("#applyTime").val();
        var type = $("#demo-chosen-select").val();
        var district = $("#district").val();
        var profile = $("#profile").val();
        var userId = storage.getItem('user_id');
        console.log(userId);
        // console.log(type);


        $.ajax({
            type: "POST",
            url: "/Patent/entryPatent",
            data: {
                patentID: patentID,
                patent: patent,
                userId: userId,
                holder: holder,
                url: uploadImageURL,//专利照片的url
                fileURL: url,
                applyTime: applyTime,
                type: type,
                district: district,
                profile: profile
            },
            success: function () {
                patentBlockChain(patentID,userId);
                storage.removeItem('fileURL');
                storage.removeItem('uploadImageURL');
                infoFile("已提交");
                setTimeout(function () {
                    window.location.href = "/ipnet/home";
                }, 2000);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
            }
        });
    }

});

$('#ssi-upload').ssi_uploader({url: '/upload/image', maxFileSize: 5, allowed: ['jpg', 'gif', 'png', 'pdf']});

function patentBlockChain(patentID,userId) {
    $.ajax({
        url: "http://120.79.232.126:3000/api/IPEstate",
        type: "POST",
        dataType: "json", //指定服务器返回的数据类型
        async: false,
        data: {
            $class: "org.acme.ipregistry.IPEstate",
            id: patentID,
            price: 0,
            ownerID: userId,
            agentID: "null",
            poolID: "null"
        },
        success: function (data) {
            console.log(data);
        },
        error: function (error) {
            console.log(error);
        }
    });
}