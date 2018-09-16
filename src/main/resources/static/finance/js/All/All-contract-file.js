var storage = window.localStorage;
var loanID = storage.getItem('loan_id');
var userId = storage.getItem('user_id');
//签署协议待完善
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
        url: "/upload/file", // Set the url
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


    // Setup the buttons for all transfers
    uploadBtn.on('click', function () {
        //Upload all files
        myDropzone.enqueueFiles(myDropzone.getFilesWithStatus(Dropzone.ADDED));
        var file = myDropzone.getFilesWithStatus(Dropzone.ADDED);
        var fileName = document.getElementById("fileName").innerHTML;
        var splitArr = fileName.split(".");
        var suffix = splitArr[splitArr.length - 1].toLowerCase();
        var role = -1;
        //获取用户身份
        $.ajax({
            type: "GET",
            url: "/user/getUserRole",
            data: {
                userId: userId
            },
            success: function (data) {
                role = data;
            },
            error: function () {

            }
        });
        if (suffix == 'jpg' || suffix == 'png' || suffix == 'gif') {
            if (file.length == 1) {
                if (role == 0 || role == 1)
                    alertFile("请上传知识产权局和财政局的签字");
                else {
                    var path = loanID + "/质押贷款保险合同/" + userId + "." + suffix;
                    var formData = new FormData();
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
                        success: function (data) {
                            //存取合同url
                            $.ajax({
                                url: '/all/saveContractURL',
                                type: 'POST',
                                data: {
                                    loanID: loanID,
                                    userid: userId,
                                    gov: -1,
                                    url: data
                                },
                                success: function () {
                                    window.location.href = "/ipnet/All-loan-check";
                                },
                                error: function () {

                                }
                            });
                        },
                        processData: false,
                        contentType: false
                    }).done(function (res) {
                    }).fail(function (res) {
                    });
                }
            }
            else if (file.length != 2) {
                alertFile('请先上传正确数目的文件!');
            } else {
                if (role >= 2)
                    alertFile('请先上传正确数目的文件!');
                else {
                    var firm = [];
                    firm[0] = loanID + "/质押贷款保险合同/知识产权局." + suffix;
                    firm[1] = loanID + "/质押贷款保险合同/财政局." + suffix;
                    for (var i = 0; i < 2; i++) {
                        var formData = new FormData();
                        formData.append('file', file[i]);
                        //上传文件
                        $.ajax({
                            url: '/upload/file',
                            type: 'POST',
                            cache: false,
                            data: {
                                path: firm[i],
                                file: formData[i]
                            },
                            success: function (data) {
                                //存取合同url
                                $.ajax({
                                    url: 'all/saveContractURL',
                                    type: 'POST',
                                    data: {
                                        loanID: loanID,
                                        userid: userId,
                                        gov: i,
                                        url: data
                                    },
                                    success: function () {
                                        window.location.href = "/ipnet/All-loan-check";
                                    },
                                    error: function () {

                                    }
                                });

                            },
                            processData: false,
                            contentType: false
                        }).done(function (res) {
                        }).fail(function (res) {
                        });
                    }
                }
            }
        }
        else {
            alertFile('请上传图片文件!');
            myDropzone.removeAllFiles(true);
            uploadBtn.prop('disabled', true);
            removeBtn.prop('disabled', true);
        }

    });

    removeBtn.on('click', function () {
        myDropzone.removeAllFiles(true);
        uploadBtn.prop('disabled', true);
        removeBtn.prop('disabled', true);

    });

});

function alertFile(str) {

    $.alert({
        title: str,
        content: '',
        confirmButton: '我知道了',
        confirmButtonClass: 'btn-primary',
        icon: 'fa fa-info',
        animation: 'zoom',
        confirm: function () {

        }
    });

}

