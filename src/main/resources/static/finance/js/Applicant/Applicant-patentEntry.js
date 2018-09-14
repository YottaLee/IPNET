var uploadImageURL = "";
$('#submit').on('click', function () {
    // if (url == "")
    //     alertFile("请先上传文件");
    // else {

    var patentID = $("#patentID").val();
    var patent = $("#patent").val();
    var holder = $("#holder").val();
    var applyTime = $("#applyTime").val();
    var type = $("#type").val();
    var district = $("#district").val();
    var profile = $("#profile").val();


    $.ajax({
        type: "POST",
        url: "/Patent/entryPatent",
        data: {
            patentID: patentID,
            patent: patent,
            holder: holder,
            url: uploadImageURL,//专利照片的url
            fileURL: url,
            applyTime: applyTime,
            type: type,
            district: district,
            profile: profile
        },
        success: function (data) {
            if (data == 3) {
                infoFile("已提交");
                setTimeout(function () {
                    window.location.href = "/ipnet/home";
                }, 2000);
            }
            else
                infoFile(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
            // alert("Network warning");
        }
    });
    // }

});

$('#ssi-upload').ssi_uploader({url: 'upload/image', maxFileSize: 1, allowed: ['jpg', 'gif', 'png', 'pdf']});

