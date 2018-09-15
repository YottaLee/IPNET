var storage = window.localStorage;
var loanID = storage.getItem('loan_id');
var patentID = storage.getItem('patent_id');

// Form-File-Upload.js
// ====================================================================
// This file should not be included in your project.
// This is just a sample how to initialize plugins or components.
//
// - ThemeOn.net -
$.ajax({
    type: 'GET',
    url: '/Patent/searchPatentByID',
    data: {
        patentID: patentID
    },
    success: function (data) {
        $("#patent").val(data.patent_name);
        $("#holder").val(data.patent_holder);
        $("#patentID").val(patentID);
    }
});

$(document).ready(function () {

    // DROPZONE.JS
    // =================================================================
    // Require Dropzone
    // http://www.dropzonejs.com/
    // =================================================================
    Dropzone.options.demoDropzone = { // The camelized version of the ID of the form element
        // The configuration we've talked about above
        autoProcessQueue: true,
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

    myDropzone.on("success", function (file) {
        //上传成功触发的事件
        var fileURL = "http://" + file.xhr.response;
        var storage = window.localStorage;
        storage.setItem('fileURL', fileURL);

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

    uploadBtn.on('click', function () {
        console.log("click upload button");
        myDropzone.processQueue();


    });

    //
    // $('#submit').on('click', function () {
    //     myDropzone.processQueue();
    //     var patentID = storage.getItem('patent_id');
    //
    //     var file = myDropzone.getFilesWithStatus(Dropzone.ADDED);
    //     var fileName = document.getElementById("fileName").innerHTML;
    //     var formData = new FormData();
    //     var splitArr = fileName.split(".");
    //     // var path = patentID + "/专利评估申请." + splitArr[splitArr.length - 1];
    //     // formData.append('name', patentID + "-专利评估报告");
    //     formData.append('file', file);
    //
    //
    //     //上传文件
    //
    //
    // });

    removeBtn.on('click', function () {
        myDropzone.removeAllFiles(true);
        uploadBtn.prop('disabled', true);
        removeBtn.prop('disabled', true);

    });

});


//获取评估机构的用户ID
function getEvaluationId() {
    $.ajax({
        type: "GET",
        url: "evaluation/getEvaluationId",
        success: function (data) {
            return data;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }
    });
}

$("#submit").on('click', function () {
    //存取申请意向
    var storage = window.localStorage;
    var fileURL = storage.getItem('fileURL');
    $.ajax({
        url: '/evaluation/apply',
        type: 'POST',
        data: {
            patentID: patentID,
            url: fileURL
        },
        success: function () {

            $.ajax({
                url: '/Patent/searchPatentByID',
                type: 'GET',
                data: {
                    patentID: patentID
                },
                success: function (data) {
                    var patent = data.patent_name;
                    var holder = data.patent_holder;
                    var money = 20000;//评估费用为20000
                    //跳入向评估公司申请的支付界面
                    var transaction = {
                        patentID: patentID,
                        patent: patent,
                        payer: holder,//付款方
                        payee: getEvaluationId(),//收款方
                        way: "专利评估",//评估机构只有一个
                        money: money//评估费用
                    };

                    storage.setItem('transaction', transaction);

                    //支付评估费用

                    window.location.href = "/ipnet/pay";
                    var storage = window.localStorage;
                    var loanID = storage.getItem('loan_id');
                    if(loanID == null||loanID == ""){
                        storage.removeItem("patent_id");
                        window.location.href = "/ipnet/Person-IP-list";
                    }
                    else{
                        $.ajax({
                            url: '/applicant/ifBankChosen',
                            type: 'GET',
                            data: {
                                loanID: loanID
                            },
                            success: function (data) {
                                if (data) {
                                    window.location.href = "/ipnet/Applicant-loan2";
                                }
                                else {
                                    $.ajax({
                                        url: '/applicant/ifBankChosen',
                                        type: 'GET',
                                        data: {
                                            loanID: loanID
                                        },
                                        success: function (ifChosen) {
                                            if (ifChosen) {
                                                $.ajax({
                                                    url: '/applicant/changeEvaluationState',
                                                    type: 'POST',
                                                    data:{
                                                        loanID: loanID
                                                    },
                                                    success:f
                                                });
                                                window.location.href = "/ipnet/Applicant-loan2";
                                            }
                                            else {
                                                storage.removeItem("patent_id");
                                                storage.removeItem("loan_id");
                                                window.location.href = "/ipnet/Person-IP-list";
                                            }

                                        },
                                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                                            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
                                        }
                                    })
                                }

                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
                            }
                        })
                    }


                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
                }
            });

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(XMLHttpRequest.status + ":" + XMLHttpRequest.statusText);
        }
    });

});

