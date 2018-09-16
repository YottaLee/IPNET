
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
            success: function (data) {
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

