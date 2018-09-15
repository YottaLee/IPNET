var patentID = $("#patentID").val();
var url = "";

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
        autoProcessQueue: true,
        uploadMultiple: true,
        parallelUploads: 25,
        maxFiles: 1,

        // The setting up of the dropzone
        init: function () {
            var myDropzone = this;
            //  Here's the change from enyo's tutorial...
            //  $("#dz-upload-btn").click(function (e) {
            //  e.preventDefault();
            //  e.stopPropagation();
            //  myDropzone.processQueue();
            //
            // }
            //    );

        }

    }


    // DROPZONE.JS WITH BOOTSTRAP'S THEME
    // =================================================================
    // Require Dropzone
    // http://www.dropzonejs.com/
    // =================================================================
    // Get the template HTML and remove it from the doumenthe template HTML and remove it from the doument
    var previewNode = document.querySelector("#dz-image-template");
    previewNode.id = "";
    var previewTemplate = previewNode.parentNode.innerHTML;
    previewNode.parentNode.removeChild(previewNode);

    var uploadBtn = $('#dz-upload-image-btn');
    var removeBtn = $('#dz-remove-image-btn');
    var myDropzone = new Dropzone(document.body, { // Make the whole body a dropzone
        url: "/upload/image", // Set the url
        thumbnailWidth: 50,
        thumbnailHeight: 50,
        parallelUploads: 20,
        previewTemplate: previewTemplate,
        acceptedFiles: ".gif,.jpg,.png",
        autoQueue: true, // Make sure the files aren't queued until manually added
        previewsContainer: "#dz-image-previews", // Define the container to display the previews
        clickable: ".fileinput-image-button" // Define the element that should be used as click trigger to select files.
    });


    myDropzone.on("addedfile", function (file) {
        // Hookup the button

        uploadBtn.prop('disabled', false);
        removeBtn.prop('disabled', false);
    });


// Form-File-Upload.js
// ====================================================================
// This file should not be included in your project.
// This is just a sample how to initialize plugins or components.
//
// - ThemeOn.net -


    // Update the total progress bar
    myDropzone.on("totaluploadprogress", function (progress) {
        $("#dz-total-progress .progress-bar").css({'width': progress + "%"});
    });

    myDropzone.on("sending", function (data, xhr, formData) {
        // Show the total progress bar when upload starts
        document.querySelector("#dz-total-image-progress").style.opacity = "1";

    });

    // Hide the total progress bar when nothing's uploading anymore
    myDropzone.on("queuecomplete", function (progress) {
        document.querySelector("#dz-total-image-progress").style.opacity = "0";
    });

    myDropzone.on("success", function (file) {
        //上传成功触发的事件
        var storage = window.localStorage;
        var imageURL = "http://" + file.xhr.response;
        storage.setItem('uploadImageURL', imageURL);
    });
    myDropzone.on("error", function (file, data) {
        //上传失败触发的事件
        console.log('fail');
        var message = '';
        //lavarel框架有一个表单验证，
        //对于ajax请求，JSON 响应会发送一个 422 HTTP 状态码，
        //对应file.accepted的值是false，在这里捕捉表单验证的错误提示
        if (file.accepted) {
            $.each(data, function (key, val) {
                message = message + val[0] + ';';
            });
            //控制器层面的错误提示，file.accepted = true的时候；
            alertFile(message);
        }
    });


    // Setup the buttons for all transfers
    uploadBtn.on('click', function () {
        //Upload all files
        // var file = myDropzone.getFilesWithStatus(Dropzone.ADDED);
        //  var fileName = document.getElementById("fileName").innerHTML;
        //  var formData = new FormData();
        //  var splitArr = fileName.split(".");
        //  var path = patentID + "/专利录入." + splitArr[splitArr.length - 1];
        //
        //  formData.append('file', file);
        //  // formData.set('name', path);
        //
        //  //上传文件
        //  $.ajax({
        //      url: '/upload/file',
        //      type: 'POST',
        //      cache: false,
        //      data:  formData,
        //      processData: false,
        //      contentType: false,
        //      success: function (url) {
        //          //存取申请意向
        //          console.log(url);
        //
        //      },
        //      error: function () {
        //
        //      }
        //  }).done(function (res) {
        //  }).fail(function (res) {
        //  });
        //  myDropzone.processQueue();


    });

    removeBtn.on('click', function () {
        myDropzone.removeAllFiles(true);
        uploadBtn.prop('disabled', true);
        removeBtn.prop('disabled', true);

    });

});



