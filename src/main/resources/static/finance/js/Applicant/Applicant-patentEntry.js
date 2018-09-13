$('#submit').on('click', function () {
    if (url == "")
        alert("请先上传文件");
    else {

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
            dataType: "json",
            data: {
                patentID: patentID,
                patent: patent,
                holder: holder,
                url: url,
                applyTime: applyTime,
                type: type,
                district: district,
                profile: profile
            },
            success: function () {

            },
            error: function () {
                // alert("Network warning");
            }
        });
    }

});