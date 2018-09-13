var storage = window.localStorage;
var loanID = storage.loanID;

// Form-File-Upload.js
// ====================================================================
// This file should not be included in your project.
// This is just a sample how to initialize plugins or components.
//
// - ThemeOn.net -

$(document).ready(function () {

    // DROPZONE.JS
    // =================================================================
    // Require Dropzone
    // http://www.dropzonejs.com/
    // =================================================================
    Dropzone.options.demoDropzone = { // The camelized version of the ID of the form element
        // The configuration we've talked about above
        autoProcessQueue: false,
        //uploadMultiple: true,
        //parallelUploads: 25,
        //maxFiles: 25,

        // The setting up of the dropzone
        init: function () {
            var myDropzone = this;
            //  Here's the change from enyo's tutorial...
            //  $("#submit-all").click(function (e) {
            //  e.preventDefault();
            //  e.stopPropagation();
            //  myDropzone.processQueue();
            //
            //}
            //    );

        }

    }


    // DROPZONE.JS WITH BOOTSTRAP'S THEME
    // =================================================================
    // Require Dropzone
    // http://www.dropzonejs.com/
    // =================================================================
    // Get the template HTML and remove it from the doumenthe template HTML and remove it from the doument
    var previewNode = document.querySelector("#dz-template");
    previewNode.id = "";
    var previewTemplate = previewNode.parentNode.innerHTML;
    previewNode.parentNode.removeChild(previewNode);

    var uploadBtn = $('#dz-upload-btn');
    var removeBtn = $('#dz-remove-btn');
    var myDropzone = new Dropzone(document.body, { // Make the whole body a dropzone
        url: "/target-url", // Set the url
        thumbnailWidth: 50,
        thumbnailHeight: 50,
        parallelUploads: 20,
        previewTemplate: previewTemplate,
        autoQueue: false, // Make sure the files aren't queued until manually added
        previewsContainer: "#dz-previews", // Define the container to display the previews
        clickable: ".fileinput-button" // Define the element that should be used as click trigger to select files.
    });


    myDropzone.on("addedfile", function (file) {
        // Hookup the button
        uploadBtn.prop('disabled', false);
        removeBtn.prop('disabled', false);
    });

    // Update the total progress bar
    myDropzone.on("totaluploadprogress", function (progress) {
        $("#dz-total-progress .progress-bar").css({'width': progress + "%"});
    });

    myDropzone.on("sending", function (file) {
        // Show the total progress bar when upload starts
        document.querySelector("#dz-total-progress").style.opacity = "1";
    });

    // Hide the total progress bar when nothing's uploading anymore
    myDropzone.on("queuecomplete", function (progress) {
        document.querySelector("#dz-total-progress").style.opacity = "0";
    });


    $('#submit').on('click', function () {

        var file = myDropzone.getFilesWithStatus(Dropzone.ADDED);
        var fileName = document.getElementById("fileName").innerHTML;
        var formData = new FormData();
        var splitArr = fileName.split(".");
        var path = patentID + "/专利评估申请." + splitArr[splitArr.length - 1];
        // formData.append('name', patentID + "-专利评估报告");
        formData.append('file', file);


        //上传文件
        $.ajax({
            url: '/upload/file',
            type: 'POST',
            cache: false,
            data: {
                path: path,
                file: formData
            },
            processData: false,
            contentType: false,
            success: function (url) {
                //存取申请意向
                $.ajax({
                    url: '/evaluation/apply',
                    type: 'POST',
                    data: {
                        patentID: patentID,
                        url: url
                    },
                    success: function () {

                    },
                    error: function () {

                    }
                });
                var patent = "";
                var holder = "";
                $.ajax({
                    url: '/Patent/searchPatentByID',
                    type: 'GET',
                    data: {
                        patentID: patentID
                    },
                    success: function (data) {
                        patent = data.patent_name;
                        holder = data.patent_holder;

                    },
                    error: function () {

                    }
                });

                //跳入向评估公司申请的支付界面
                var transaction = {
                    patentID: patentID,
                    patent: patent,
                    holoder: holder,
                    way: "专利评估",
                    amount: money
                };

                window.location.href = "../pay.html";
                //支付成功后判断是否有意向信息
                $.ajax({
                    url: 'applicant/ifBankChosen',
                    type: 'GET',
                    data: loanID,
                    dataType: 'json',
                    success: function (data) {
                        if (data) {
                            window.location.href = "Applicant-loan2.html";
                        }
                        else {
                            storage.removeItem("patentID");
                            window.location.href = "Person-IP-list.html";
                        }

                    },
                    error: function () {

                    }
                })

            },
            error: function () {

            }
        }).done(function (res) {
        }).fail(function (res) {
        });


    });

    removeBtn.on('click', function () {
        myDropzone.removeAllFiles(true);
        uploadBtn.prop('disabled', true);
        removeBtn.prop('disabled', true);

    });

});

