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

function infoFile(str) {

    $.dialog({
        title: str,
        content: '',
        icon: 'fa fa-info',
        animation: 'zoom',
        confirm: function () {

        }
    });

}

function confirmFile(){
    $.confirm({
        title: '恭喜您！上传成功',
        content: '请选择您的隐私权限',
        confirmButton: '确认付款',
        cancelButton: '取消',
        confirmButtonClass: 'btn-info',
        icon: 'fa fa-question-circle',
        animation: 'rotate',
        closeAnimation: 'right',
        opacity: 0.5,
        confirm: function () {

        }
    });
}